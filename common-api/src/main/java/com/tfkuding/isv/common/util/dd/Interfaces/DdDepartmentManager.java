package com.tfkuding.isv.common.util.dd.Interfaces;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.taobao.api.ApiException;
import com.tfkuding.isv.common.constant.dd.url.DdDepartmentConstant;
import com.tfkuding.isv.common.enums.HttpMethod;
import com.tfkuding.isv.common.util.LogUtil;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 11:37
 *
 * 钉钉部门管理类
 */
public class DdDepartmentManager {

    /**
     * 获取部门列表
     * @param accessToken 调用接口凭证
     * @param departmentId  部门id
     * @return  返回部门列表
     */
    public static List<OapiDepartmentListResponse.Department> getDepartmentList (String accessToken, String departmentId) {
        DingTalkClient client = new DefaultDingTalkClient(DdDepartmentConstant.DEPARTMENT_LIST);
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(departmentId);
        request.setHttpMethod(HttpMethod.GET.method);
        try {
            OapiDepartmentListResponse response = client.execute(request, accessToken);
            if (ProcessDDResponse.processResponse(response)) {
                return response.getDepartment();
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }

    /**
     * 获取部门详情
     * @param accessToken   调用接口凭证
     * @param departmentId  部门Id
     * @return  部门详情信息
     */
    public static OapiDepartmentGetResponse getDepartment (String accessToken, String departmentId) {
        DingTalkClient client = new DefaultDingTalkClient(DdDepartmentConstant.DEPARTMENT);
        OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
        request.setId(departmentId);
        request.setHttpMethod(HttpMethod.GET.method);
        try {
            OapiDepartmentGetResponse response = client.execute(request, accessToken);
            if (ProcessDDResponse.processResponse(response)) {
                return response;
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }
}
