package com.tfkuding.isv.common.service.dd;

import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.util.Response;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 14:41
 *
 * 钉钉部门Service
 */
public interface DdDepartmentService {
    /**
     * 返回所有部门信息
     * @return List<DdDepartment>
     */
    List<DdDepartment> findAll();

    /**
     * 保存多个部门
     * @param ddDepartments 部门
     * @return  Response
     */
    Response saveAll (List<DdDepartment> ddDepartments);

    /**
     * 根据部门Id获取部门信息
     * @param id    部门Id
     * @return  Response
     */
    Response findById(Long id);
}
