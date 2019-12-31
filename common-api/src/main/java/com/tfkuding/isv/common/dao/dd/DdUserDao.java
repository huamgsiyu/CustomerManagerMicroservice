package com.tfkuding.isv.common.dao.dd;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:08
 *
 * 钉钉用户
 */
public interface DdUserDao extends JpaRepository<DdUser, Long> {
}
