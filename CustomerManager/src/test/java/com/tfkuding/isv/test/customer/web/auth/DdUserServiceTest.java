package com.tfkuding.isv.test.customer.web.auth;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.dd.DdUserService;
import com.tfkuding.isv.customer.CustomerManagerEntry;
import org.junit.After;
import org.junit.Before;
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
 * @date 2019/12/31 14:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CustomerManagerEntry.class)
@EnableAutoConfiguration
public class DdUserServiceTest {
    @Resource
    private DdUserService ddUserService;

    @Before
    public void saveAll () {
        List<DdUser> ddUsers = new ArrayList<>();
        DdUser ddUser = new DdUser();
        ddUser.setId(1L);
        ddUser.setName("hsy");
        ddUsers.add(ddUser);
        ddUserService.saveAll(ddUsers);
    }

    @Test
    public void get () {
        ddUserService.findById(1L);
    }


    @After
    public void delete () {
        ddUserService.delete(1L);
    }
}
