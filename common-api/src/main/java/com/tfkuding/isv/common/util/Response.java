package com.tfkuding.isv.common.util;

import com.tfkuding.isv.common.enums.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/28 17:39
 *
 * 返回给前端的响应体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 调用接口返回描述信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public Response() {
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 功能描述
     *  不带返回体的成功返回
     * @param <T>   泛型
     * @return  Response
     */
    public static <T> Response<T> succeed() {
        return succeed(null);
    }

    /**
     * 功能描述
     *  带返回体的成功返回
     * @param <T>   泛型
     * @return  Response
     */
    public static <T> Response<T> succeed(T data) {
        return new Response<>(ResponseCode.OK.code, ResponseCode.OK.msg, data);
    }

    /**
     * 功能描述
     *  不带返回体的失败返回——业务级别失败
     * @param <T>   泛型
     * @return  Response
     */
    public static <T> Response<T> failureBusinessLevel () {
        return failure(ResponseCode.BUSINESS_LEVEL_ERROR.code, ResponseCode.BUSINESS_LEVEL_ERROR.msg, null);
    }
    /**
     * 功能描述
     *  不带返回体的失败返回——系统级别失败
     * @param <T>   泛型
     * @return  Response
     */
    public static <T> Response<T> failureSystemLevel () {
        return failure(ResponseCode.INTERNAL_SERVER_ERROR.code, ResponseCode.INTERNAL_SERVER_ERROR.msg, null);
    }

    public static <T> Response<T> failureMicroInvocation () {
        return failure(ResponseCode.MICRO_INVOCATION_ERROR.code, ResponseCode.MICRO_INVOCATION_ERROR.msg, null);
    }

    public static <T> Response<T> failure (Integer code, String msg, T data) {
        return new Response<>(code, msg, data);
    }
}

