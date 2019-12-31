package com.tfkuding.isv.common.util.dd.Interfaces;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoResponse;
import com.tfkuding.isv.common.constant.dd.url.AuthenticationConstant;
import com.tfkuding.isv.common.enums.HttpMethod;
import com.tfkuding.isv.common.enums.StatusCode;
import com.tfkuding.isv.common.util.LogUtil;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 21:42
 *
 * 钉钉服务端API——身份验证API
 */
public class Authentication {

    /**
     * 根据appkey和appSecret获取access_token
     * @param appKey    应用的唯一标识key
     * @param appSecret 应用的秘钥
     * @return  返回access_token
     */
    public static String getAccessToken (String appKey, String appSecret) {
        DefaultDingTalkClient client = new DefaultDingTalkClient(AuthenticationConstant.ACCESS_TOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(HttpMethod.GET.method);
        try {
            OapiGettokenResponse response = client.execute(request);
            if (ProcessDDResponse.processResponse(response)) {
                return response.getAccessToken();
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }

    /**
     * 根据调用接口凭证和免登授权码获取用户userId
     * @param code  免登授权码
     * @param accessToken   调用接口凭证
     * @return  用户userId
     */
    public static String getUserId (String code, String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient(AuthenticationConstant.GET_USER_ID);
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod(HttpMethod.GET.method);
        OapiUserGetuserinfoResponse response = null;
        try {
            response = client.execute(request, accessToken);
            if (ProcessDDResponse.processResponse(response)) {
                return response.getUserid();
            }
        } catch (ApiException e) {
            LogUtil.error(e);
            return null;
        }
        return null;
    }
}
