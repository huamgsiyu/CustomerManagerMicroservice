package com.tfkuding.isv.common.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/28 17:39
 *
 * 日志打印工具
 * 1、debug级别
 * 2、info级别
 * 3、error级别
 */
@Slf4j
public class LogUtil {

    private final static String OBJ_JSON = "obj -> json：";
    private final static String OBJ = "obj：";

    private final static String PRINT_START =
            "<------------------------------< 日志打印开始 >------------------------------>";
    private final static String PRINT_END =
            "<------------------------------< 日志打印结束 >------------------------------>";

    /**
     * debug级别日志，打印输出一个对象，以及该对象转JSON之后的数据
     * @param o obj
     */
    public static void debug(Object o) {
        log.debug(PRINT_START);
        log.debug(OBJ + o);
        log.debug(OBJ_JSON + JSONObject.toJSONString(o));
        log.debug(PRINT_END);
        log.debug("\n");
    }

    /**
     * info级别日志，打印输出一个对象，以及该对象转JSON之后的数据
     * @param o obj
     */
    public static void info(Object o) {
        log.info(PRINT_START);
        log.info(OBJ + o);
        log.info(OBJ_JSON + JSONObject.toJSONString(o));
        log.info(PRINT_END);
        log.info("\n");
    }

    /**
     * error级别日志，打印输出一个对象，以及该对象转JSON之后的数据
     * @param o obj
     */
    public static void error(Object o) {
        log.error(PRINT_START);
        log.error(OBJ + o);
        log.error(OBJ_JSON + JSONObject.toJSONString(o));
        log.error(PRINT_END);
        log.error("\n");
    }
}
