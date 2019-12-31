package com.tfkuding.isv.common.service.hystrix;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.hystrix.fallback.DdUserClientFallbackFactory;
import com.tfkuding.isv.common.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:31
 *
 * 钉钉用户Service 服务降级
 */
@FeignClient(value = "CUSTOMER-MANAGER", fallbackFactory = DdUserClientFallbackFactory.class)
public interface DdUserClientService {
    @PostMapping("/ddUser")
    Response save (DdUser ddUser);

    @PostMapping("/ddUser/saveAll")
    Response saveAll (List<DdUser> ddUsers);

    @DeleteMapping("/ddUser/{id}")
    Response delete (Long id);

    @DeleteMapping("/ddUser/")
    Response deleteAll ();
}
