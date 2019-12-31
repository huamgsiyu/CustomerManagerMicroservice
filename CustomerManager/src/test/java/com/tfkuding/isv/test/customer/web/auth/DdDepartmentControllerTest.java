package com.tfkuding.isv.test.customer.web.auth;

import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.service.dd.DdDepartmentService;
import com.tfkuding.isv.customer.CustomerManagerEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 16:27
 *
 * 部门Controller测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerManagerEntry.class)
@EnableAutoConfiguration
public class DdDepartmentControllerTest {
    @Resource
    private DdDepartmentService ddDepartmentService;

    @Test
    public void saveAll () {
        List<DdDepartment> departments = new ArrayList<>();

        DdDepartment ddDepartment = new DdDepartment();
        ddDepartment.setId(1L);
        ddDepartment.setName("大人不");
        departments.add(ddDepartment);
        ddDepartmentService.saveAll(departments);
    }

    @Test
    public void findById () {
        ddDepartmentService.findById(1L);
    }

    @Test
    public void findAll () {
        List<DdDepartment> ddDepartments = ddDepartmentService.findAll();
    }
}
