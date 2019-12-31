package com.tfkuding.isv.common.service.dd.impl;

import com.tfkuding.isv.common.dao.dd.DdDepartmentDao;
import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.service.dd.DdDepartmentService;
import com.tfkuding.isv.common.util.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 14:42
 *
 * 钉钉部门Service实现类
 */
@Service
public class DdDepartmentServiceImpl implements DdDepartmentService {
    @Resource
    private DdDepartmentDao ddDepartmentDao;

    @Override
    public List<DdDepartment> findAll() {
        return ddDepartmentDao.findAll();
    }

    @Override
    public Response saveAll (List<DdDepartment> ddDepartments) {
         ddDepartmentDao.saveAll(ddDepartments);
         return Response.succeed();
    }

    @Override
    public Response findById(Long id) {
        return Response.succeed(ddDepartmentDao.getOne(id));
    }
}
