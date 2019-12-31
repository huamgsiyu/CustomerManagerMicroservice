package com.tfkuding.isv.common.constant;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/29 22:53
 *
 * ISV分布式请求秘钥——头部拥有该秘钥，可以访问ISV微服务所有的接口
 */
public class DistributedSecretKey {
    /*
        定义头部FBS_MY_KEY
     */
    public static final String FBS_MY_KEY = "fbsMy";

    /*
        分布式请求秘钥
     */
    public final static String FBS_MY_VALUE = "a1c83248d4423cb58099c007325fa4b6";
}
