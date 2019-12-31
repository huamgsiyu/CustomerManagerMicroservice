package com.tfkuding.isv.customer.ddsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 9:42
 *
 * 同步天峰酷钉ISV直属销售部门信息
 * 1、部门信息
 * 2、员工信息
 * 3、角色信息
 */

@SpringBootApplication(scanBasePackages = {
        "com.tfkuding.isv.common.service",
        "com.tfkuding.isv.customer.common.service",
        "com.tfkuding.isv.customer.ddsync"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.tfkuding.isv.common.service.hystrix"})
@EnableCircuitBreaker
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.tfkuding.isv.common.dao"})
@EntityScan(basePackages = {"com.tfkuding.isv.common.pojo"})
public class SyncSalesmanStart {
    public static void main(String[] args) {
        SpringApplication.run(SyncSalesmanStart.class, args);
    }
}
