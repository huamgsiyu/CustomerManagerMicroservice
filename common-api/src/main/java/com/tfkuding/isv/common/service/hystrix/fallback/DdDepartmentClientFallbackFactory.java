package com.tfkuding.isv.common.service.hystrix.fallback;

import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.service.hystrix.DdDepartmentClientService;
import com.tfkuding.isv.common.util.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 16:39
 */
@Component
public class DdDepartmentClientFallbackFactory implements FallbackFactory<DdDepartmentClientService> {
    @Override
    public DdDepartmentClientService create(Throwable cause) {
        return new DdDepartmentClientService() {
            @Override
            public Response saveAll(List<DdDepartment> departments) {
                return Response.failureMicroInvocation();
            }

            @Override
            public Response get(Long id) {
                return Response.failureMicroInvocation();
            }
        };
    }
}
