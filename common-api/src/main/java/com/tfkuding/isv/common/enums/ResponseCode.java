package com.tfkuding.isv.common.enums;

import java.util.Arrays;
import java.util.Optional;
/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 22:15
 *
 * 接口响应状态码以及描述信息
 */
public enum ResponseCode {
    /*
        成功执行请求
     */
    OK(200, "Success"),
    /*
        请求参数不正确
     */
    BAD_REQUEST(400, "Bad Request"),
    /*
        没有权限
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /*
        禁止访问
     */
    FORBIDDEN(403, "Forbidden"),
    /*
        没有找到资源
     */
    NOT_FOUND(404, "Not Found"),
    /*
        接收到请求，但是服务器出错
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /*
        接收到请求，但是请求执行出错
     */
    BUSINESS_LEVEL_ERROR(10000, "Business level error"),
    /*
        微服务调用出错
     */
    MICRO_INVOCATION_ERROR(10001, "Micro Invocation Error");;

    public Integer code;

    public String msg;

    ResponseCode() {
    }

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 功能描述
     *   根据msg信息获取对应的状态码
     * @param msg   描述信息
     * @return  Integer 状态码
     */
    public static Integer getCode (String msg) {
        if (msg != null && msg.length() > 0) {
            Optional<ResponseCode> responseCode = Arrays.stream(ResponseCode.values())
                    .filter(o -> o.msg.toLowerCase().trim().equals(msg.toLowerCase().trim()))
                    .findFirst();
            return responseCode.map(o -> o.code).orElse(null);
        }
        return null;
    }

    /**
     * 功能描述
     *   根据状态码获取描述信息
     * @param code 状态码
     * @return  描述信息
     */
    public static String getMsg (Integer code) {
        Optional<ResponseCode> responseCode = Arrays.stream(ResponseCode.values())
                .filter(o -> o.code.equals(code))
                .findFirst();
        return responseCode.map(o -> o.msg).orElse(null);
    }
}
