package com.tfkuding.isv.common.service.hystrix;

import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.service.hystrix.fallback.DdDepartmentClientFallbackFactory;
import com.tfkuding.isv.common.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 16:33
 *
 * 客户管理系统——部门服务类
 */
@FeignClient(value = "CUSTOMER-MANAGER", fallbackFactory = DdDepartmentClientFallbackFactory.class)
public interface DdDepartmentClientService {
    @PostMapping("/department/saveAll")
    Response saveAll(List<DdDepartment> departments);

    @GetMapping("/department/{id}")
    Response get(Long id);
}
