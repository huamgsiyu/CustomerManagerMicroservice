package com.tfkuding.isv.customer.ddsync.synchronizetion;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.tfkuding.isv.common.enums.ResponseCode;
import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.hystrix.DdDepartmentClientService;
import com.tfkuding.isv.common.service.hystrix.DdUserClientService;
import com.tfkuding.isv.common.util.LogUtil;
import com.tfkuding.isv.common.util.Response;
import com.tfkuding.isv.common.util.dd.Interfaces.Authentication;
import com.tfkuding.isv.common.util.dd.Interfaces.DdDepartmentManager;
import com.tfkuding.isv.common.util.dd.Interfaces.DdUserManager;
import com.tfkuding.isv.customer.common.constant.CustomerApplicationMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 11:31
 *
 * 同步天峰酷钉的组织架构
 */
@Component
public class EnterpriseOrganizations {

    @Resource
    private DdDepartmentClientService ddDepartmentClientService;

    @Resource
    private DdUserClientService ddUserClientService;

    @Scheduled(cron = "0/1 * * * * ? ")
    public void synchronizedOrganization() {
        String accessToken = createAccessToken();
        String departmentId = "1";
        List<String> ddUserIds = new ArrayList<>();
        List<DdDepartment> ddDepartments = synchronizedDepartment(accessToken, departmentId, ddUserIds);
        Response responseDdDepartment = ddDepartmentClientService.saveAll(ddDepartments);
        if (responseDdDepartment.getCode().equals(ResponseCode.OK.code)) {
            LogUtil.info("保存部门信息成功");
        }
        List<DdUser> ddUsers = synchronizedUser(accessToken, ddUserIds);
        Response responseDdUser = ddUserClientService.saveAll(ddUsers);
        if (responseDdUser.getCode().equals(ResponseCode.OK.code)) {
            LogUtil.info("保存用户信息成功");
        }
    }

    /**
     * 获取accessToken
     * @return  accessToken
     */
    private String createAccessToken () {
        return Authentication.getAccessToken(CustomerApplicationMessage.APP_KEY,
                CustomerApplicationMessage.APP_SECRET);
    }

    /**
     * 获取企业每个部门的信息
     * @param accessToken   调用接口凭证
     * @param departmentId  部门Id
     * @return  List<Department>
     */
    private List<DdDepartment> synchronizedDepartment (String accessToken,
                                                      String departmentId,
                                                      List<String> ddUsers) {
        List<DdDepartment> departments = new ArrayList<>();

        List<OapiDepartmentListResponse.Department> departmentList =
                DdDepartmentManager.getDepartmentList(accessToken, departmentId);
        if (departmentList != null && !departmentList.isEmpty()) {
            DdDepartment departmentVo = null;
            for(OapiDepartmentListResponse.Department department : departmentList) {
                /*
                    1 获取部门Id
                 */
                departmentVo = new DdDepartment();
                Long id = department.getId();

                /*
                    2 获取部门下所有用户的userId
                 */
                Response<List<String>> userIdInDepartment =
                        DdUserManager.getUserIdInDepartment(accessToken, String.valueOf(id));
                if (userIdInDepartment != null) {
                    List<String> userIds = userIdInDepartment.getData();
                    ddUsers.addAll(userIds);
                }

                /*
                    3 获取企业所有部门信息
                 */
                OapiDepartmentGetResponse departmentGetResponse =
                        DdDepartmentManager.getDepartment(accessToken, String.valueOf(id));
                if (departmentGetResponse == null) {
                    continue;
                }
                BeanUtils.copyProperties(departmentGetResponse, departmentVo);
                departments.add(departmentVo);
            }
        }
        return departments;
    }

    /**
     * 获取所有用户信息
     * @param accessToken 调用接口凭证
     * @param userIds   用户id列表
     * @return  List<DdUser>
     */
    private List<DdUser> synchronizedUser (String accessToken, List<String> userIds) {
        List<DdUser> ddUsers = new ArrayList<>();
        if (userIds != null && !userIds.isEmpty()) {
            for (String userId : userIds) {
                Response<OapiUserGetResponse> userResponse = DdUserManager.getUser(accessToken, userId);
                if (userResponse == null) {
                    continue;
                }
                OapiUserGetResponse user = userResponse.getData();
                DdUser ddUser = new DdUser();
                List<Long> department = user.getDepartment();
                ddUser.setDepartment(JSONObject.toJSONString(department));
                BeanUtils.copyProperties(user, ddUser);
                /*
                    设置角色
                 */

                ddUsers.add(ddUser);
            }
        }
        return ddUsers;
    }

    private static String analysisData (String str) {
        Map<String, String> leaderDepts = new HashMap<>();
        Map<Long, Boolean> mapClass = new HashMap<>();
        Map<Long, Boolean> map = JSONObject.parseObject(str, mapClass.getClass());
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<Long, Boolean> obj : map.entrySet()) {
                leaderDepts.put(String.valueOf(obj.getKey()), String.valueOf(obj.getValue()));
            }
        }
        return JSONObject.toJSONString(leaderDepts);
    }
}
