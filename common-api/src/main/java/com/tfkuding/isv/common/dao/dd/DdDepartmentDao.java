package com.tfkuding.isv.common.dao.dd;

import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 0:04
 *
 * 钉钉部门Dao
 */
@Component
public interface DdDepartmentDao extends JpaRepository<DdDepartment, Long> {
}
