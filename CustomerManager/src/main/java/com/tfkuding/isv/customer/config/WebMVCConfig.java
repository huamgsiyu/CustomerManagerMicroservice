package com.tfkuding.isv.customer.config;

import com.tfkuding.isv.customer.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 10:14
 */
public class WebMVCConfig extends WebMvcConfigurationSupport {
    @Resource
    private LoginInterceptor loginInterceptor;

    /* 添加控制器 */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-resources/**", //swagger文档
                        "/v2/**",//swagger文档
                        "/webjars/**",//swagger文档
                        "/*.html",//放行html
                        "/**/*.html",//放行html
                        "**/img/**",//放行img
                        "/indexController/**",//放行测试
                        "/druid/**",//放行druid
                        "/homeController/getUserInformation",//放行登陆
                        "/LogCountController/**",//放行登陆信息统计
                        "/homeController/queenStageFree"//放行后台登陆
                );
    }
}
