package com.tfkuding.isv.customer.web.auth;

import com.tfkuding.isv.common.pojo.dd.DdUser;
import com.tfkuding.isv.common.service.dd.DdUserService;
import com.tfkuding.isv.common.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:11
 *
 * 钉钉用户管理类
 */
@Api(tags = "钉钉用户管理")
@RequestMapping("/ddUser")
@RestController
public class DdUserController {
    @Resource
    private DdUserService ddUserService;

    @ApiOperation(value = "新增一个钉钉用户")
    @PostMapping()
    public Response save (@ApiParam(name = "钉钉用户信息", required = true) @RequestBody DdUser ddUser) {
        return ddUserService.save(ddUser);
    }

    @ApiOperation(value = "新增多个钉钉用户")
    @PostMapping("/saveAll")
    public Response saveAll (@ApiParam(name = "钉钉用户信息", required = true) @RequestBody List<DdUser> ddUsers) {
        return ddUserService.saveAll(ddUsers);
    }

    @ApiOperation(value = "通过id删除钉钉用户")
    @DeleteMapping("/{id}")
    public Response delete (@ApiParam(name = "钉钉用户信息", required = true) @PathVariable("id") Long id) {
        return ddUserService.delete(id);
    }

    @ApiOperation(value = "删除所有钉钉用户")
    @DeleteMapping()
    public Response deleteAll () {
        return ddUserService.deleteAll();
    }
}
