package com.tfkuding.isv.common.service.hystrix.fallback;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.hystrix.DdUserClientService;
import com.tfkuding.isv.common.util.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:31
 */
@Component
public class DdUserClientFallbackFactory implements FallbackFactory<DdUserClientService> {
    @Override
    public DdUserClientService create(Throwable cause) {
        return new DdUserClientService() {
            @Override
            public Response save(DdUser ddUser) {
                return Response.failureMicroInvocation();
            }

            @Override
            public Response saveAll(List<DdUser> ddUsers) {
                return Response.failureMicroInvocation();
            }

            @Override
            public Response delete(Long id) {
                return Response.failureMicroInvocation();
            }

            @Override
            public Response deleteAll() {
                return Response.failureMicroInvocation();
            }
        };
    }
}
