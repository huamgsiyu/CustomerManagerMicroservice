package com.tfkuding.isv.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 22:15
 *
 * 钉钉服务端全局错误码
 */
public enum StatusCode {
    SYSTEM_IS_BUSY(-1, "系统繁忙"),
    SUCCESS(0, "请求成功"),
    AUTHENTICATION_ANOMALIES(88, "鉴权异常"),
    REQUEST_ADDRESS_NOT_FOUND(404, "请求的URI地址不存在"),
    INVALID_ENTERPRISE_ID(33001, "无效的企业ID"),
    INVALID_MICRO_APPLICATION_NAME(33002, "无效的微应用的名称"),
    INVALID_MICRO_APPLICATION_DESCRIPTION(33003, "无效的微应用的描述"),
    INVALID_MICRO_APPLICATION_ICON(33004, "无效的微应用的ICON"),
    INVALID_MICRO_APPLICATION_MOBILE_HOME_PAGE(33004, "无效的微应用的移动端主页")
    ;


    public Integer code;
    public String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据描述信息返回对应的code
     * @param msg   描述信息
     * @return  code错误码
     */
    public static Integer getCode (String msg) {
        if (msg != null && msg.length() > 0) {
            Optional<StatusCode> responseCode = Arrays.stream(StatusCode.values())
                    .filter(o -> o.msg.equals(msg.trim()))
                    .findFirst();
            return responseCode.map(o -> o.code).orElse(null);
        }
        return null;
    }

    /**
     * 根据code返回对应的描述信息
     * @param code  错误码
     * @return msg描述信息
     */
    public static String getMsg (Integer code) {
        Optional<StatusCode> responseCode = Arrays.stream(StatusCode.values())
                .filter(o -> o.code.equals(code))
                .findFirst();
        return responseCode.map(o -> o.msg).orElse(null);
    }
}