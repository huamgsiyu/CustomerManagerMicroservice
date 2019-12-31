package com.tfkuding.isv.customer.aop;

import com.tfkuding.isv.common.constant.Common;
import com.tfkuding.isv.common.constant.DistributedSecretKey;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 14:54
 *
 * 解析Controller层头部信息
 */
@Component
@Aspect
@Slf4j
public class AnalysisRequestHeader {

    @Resource
    private HttpServletRequest request;

    /**
     * 切面
     */
    @Pointcut("execution(* com.tfkuding.isv.customer.web.auth.*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知
     * @param point 切点
     */
    @Before("webLog()")
    public void before(JoinPoint point) {
//        logPrintCommon(point, REQUEST_START);
    }

    /**
     * 后置通知
     * @param point 切点
     */
    @After("webLog()")
    public void after(JoinPoint point) {
//        logPrintCommon(point, REQUEST_END);
    }

    /**
     *  返回前的通知
     * @param res 响应体
     */
    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfterReturning(Object res) {

    }

    /**
     * 通知体
     * @param point 切点
     * @param title 标题
     */
    private boolean logPrintCommon(JoinPoint point, String title) {
        String fbsMyKey = request.getHeader(DistributedSecretKey.FBS_MY_KEY);
        if (fbsMyKey.equals(DistributedSecretKey.FBS_MY_VALUE)) {
            return true;
        }
        String corpId = request.getHeader(Common.CORP_ID);
        return false;
    }
}
