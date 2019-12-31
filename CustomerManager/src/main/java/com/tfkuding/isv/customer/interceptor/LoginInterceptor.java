package com.tfkuding.isv.customer.interceptor;

import com.tfkuding.isv.common.constant.Common;
import com.tfkuding.isv.common.constant.DistributedSecretKey;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 10:16
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String fbsMyKey = request.getHeader(DistributedSecretKey.FBS_MY_KEY);
        // isv部门子项目权限，直接放行
        if (fbsMyKey.equals(DistributedSecretKey.FBS_MY_VALUE))
            return true;
        String access_Token = request.getHeader(Common.ACCESS_TOKEN);
        String corpId = request.getHeader(Common.CORP_ID);
        return false;
    }
}
