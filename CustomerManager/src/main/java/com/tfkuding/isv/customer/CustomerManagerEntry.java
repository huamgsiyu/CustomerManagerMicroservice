package com.tfkuding.isv.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/26 11:43
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.tfkuding.isv.customer", "com.tfkuding.isv.common.service"})
@EnableJpaRepositories(basePackages = {"com.tfkuding.isv.customer", "com.tfkuding.isv.common.dao"})
@EntityScan(basePackages = {"com.tfkuding.isv.common.pojo"})
public class CustomerManagerEntry {
    public static void main(String[] args) {
        SpringApplication.run(CustomerManagerEntry.class, args);
    }
}
