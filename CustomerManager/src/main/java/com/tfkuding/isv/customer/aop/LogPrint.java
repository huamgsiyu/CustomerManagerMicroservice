package com.tfkuding.isv.customer.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/26 15:02
 */
@Component
@Aspect
@Slf4j
public class LogPrint {
    private final static String REQUEST_START = "请求开始";
    private final static String REQUEST_END = "请求结束";

    private final static String PRINT_LEFT = "<------------------------------< ";
    private final static String PRINT_RIGHT = " >------------------------------>";


    private final static String REQUEST_RESPONSE =
            "<------------------------------< 响应 >------------------------------>";
    private final static String REQUEST_RESPONSE_NULL = "返回值为null";
    private final static String REQUEST_RESPONSE_BODY = "响应体：";

    private final static String TARGET_START = "<--- 目标为：";
    private final static String TARGET_END = " --->";
    private final static String REQUEST_INTERFACE = "  请求接口：";
    private final static String REQUEST_ADDRESS= "  请求地址：";
    private final static String REQUEST_TYPE = "  请求类型：";
    private final static String REQUEST_METHOD = "  请求方法：";
    private final static String REQUEST_PARAM = "  请求参数：";
    private final static String NULL = "";

    @Resource
    private HttpServletRequest request;

    /**
     * 切面
     */
    @Pointcut("execution(* com.tfkuding.isv.customer.web..*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知
     * @param point 切点
     */
    @Before("webLog()")
    public void before(JoinPoint point) {
        logPrintCommon(point, REQUEST_START);
    }

    /**
     * 后置通知
     * @param point 切点
     */
    @After("webLog()")
    public void after(JoinPoint point) {
        logPrintCommon(point, REQUEST_END);
    }

    /**
     *  返回前的通知
     * @param res 响应体
     */
    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfterReturning(Object res) {
        log.info(REQUEST_RESPONSE);
        String result = res != null ? JSONObject.toJSONString(res) : REQUEST_RESPONSE_NULL;
        log.info(REQUEST_RESPONSE_BODY + result);
        log.info(NULL);
    }

    /**
     * 通知体
     * @param point 切点
     * @param title 标题
     */
    private void logPrintCommon(JoinPoint point, String title) {
        log.info(PRINT_LEFT + title + PRINT_RIGHT);
        log.info(TARGET_START + point.getSignature().getDeclaringTypeName() + TARGET_END);
        log.info(REQUEST_INTERFACE + request.getRequestURL().toString());
        log.info(REQUEST_ADDRESS + request.getRemoteAddr());
        log.info(REQUEST_TYPE + request.getMethod());
        log.info(REQUEST_METHOD + point.getSignature().getName());
        log.info(REQUEST_PARAM + Arrays.toString(point.getArgs()));
        log.info(NULL);
    }
}
