package com.tfkuding.isv.common.service.dd;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.util.Response;

import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:09
 *
 * 钉钉用户Service
 */
public interface DdUserService {

    Response save (DdUser ddUser);

    Response saveAll (List<DdUser> ddUsers);

    Response findById (Long id);

    Response delete (Long id);

    Response deleteAll ();
}
