package com.tfkuding.isv.common.util.dd.Interfaces;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetDeptMemberRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.response.OapiUserGetDeptMemberResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.taobao.api.ApiException;
import com.tfkuding.isv.common.constant.dd.url.DdUserConstant;
import com.tfkuding.isv.common.enums.HttpMethod;
import com.tfkuding.isv.common.util.LogUtil;
import com.tfkuding.isv.common.util.Response;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:49
 * 
 * 钉钉用户管理类
 */
public class DdUserManager {
    
    /**
     * 获取部门下所有userId
     * @param accessToken   调用接口凭证
     * @param deptId    部门id
     * @return  Response<List<String>>
     */
    public static Response<List<String>> getUserIdInDepartment (String accessToken, String deptId) {
        DingTalkClient client = new DefaultDingTalkClient(DdUserConstant.GET_DEPARTMENT_USER_ID);
        OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
        req.setDeptId(deptId);
        req.setHttpMethod(HttpMethod.GET.method);
        try {
            OapiUserGetDeptMemberResponse response = client.execute(req, accessToken);
            if (ProcessDDResponse.processResponse(response)) {
                return Response.succeed(response.getUserIds());
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }

    /**
     * 获取用户详情
     * @param accessToken   调用接口凭证
     * @param userId    用户Id
     * @return  Response<OapiUserGetResponse>
     */
    public static Response<OapiUserGetResponse> getUser (String accessToken, String userId) {
        DingTalkClient client = new DefaultDingTalkClient(DdUserConstant.GET_USER);
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod(HttpMethod.GET.method);
        try {
            OapiUserGetResponse response = client.execute(request, accessToken);
            if (ProcessDDResponse.processResponse(response)) {
                return Response.succeed(response);
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }
}
