package com.tfkuding.isv.common.util.dd.Interfaces;

import com.taobao.api.TaobaoResponse;
import com.tfkuding.isv.common.enums.StatusCode;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 11:56
 *
 * 处理钉钉返回体
 */
public class ProcessDDResponse {
    /**
     * 判断接口返回是否正常
     * @param response  响应
     * @return  true为正常，false为出错
     */
    public static boolean processResponse (TaobaoResponse response) {
        if (response != null) {
            return response.getErrorCode().equals(StatusCode.SUCCESS.code.toString());
        }
        return false;
    }
}
