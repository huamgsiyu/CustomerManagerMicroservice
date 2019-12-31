package com.tfkuding.isv.common.enums;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 21:51
 *
 * HTTP常见的请求方式
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    public String method;

    HttpMethod(String method) {
        this.method = method;
    }
}
