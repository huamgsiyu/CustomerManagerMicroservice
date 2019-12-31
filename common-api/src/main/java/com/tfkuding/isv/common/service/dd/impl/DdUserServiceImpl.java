package com.tfkuding.isv.common.service.dd.impl;

import com.tfkuding.isv.common.dao.dd.DdUserDao;
import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.dd.DdUserService;
import com.tfkuding.isv.common.util.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:09
 *
 * 钉钉用户实现类
 */
@Service
public class DdUserServiceImpl implements DdUserService {
    @Resource
    private DdUserDao ddUserDao;

    @Override
    public Response save(DdUser ddUser) {
        return Response.succeed(ddUserDao.save(ddUser));
    }

    @Override
    public Response saveAll(List<DdUser> ddUsers) {
        ddUserDao.saveAll(ddUsers);
        return Response.succeed();
    }

    @Override
    public Response findById(Long id) {
        Optional<DdUser> ddUser = ddUserDao.findById(id);
        return Response.succeed(ddUser);
    }

    @Override
    public Response delete(Long id) {
        ddUserDao.deleteById(id);
        return Response.succeed();
    }

    @Override
    public Response deleteAll() {
        ddUserDao.deleteAll();
        return Response.succeed();
    }
}
