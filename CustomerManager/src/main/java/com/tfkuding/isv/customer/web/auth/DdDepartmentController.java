package com.tfkuding.isv.customer.web.auth;

import com.tfkuding.isv.common.constant.DepartmentTableField;
import com.tfkuding.isv.common.pojo.dd.DdDepartment;
import com.tfkuding.isv.common.service.dd.DdDepartmentService;
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
 * @date 2019/12/30 14:50
 *
 * 部门Controller
 */
@Api(tags = "部门管理")
@RestController
@RequestMapping("/department")
public class DdDepartmentController {

    @Resource
    private DdDepartmentService ddDepartmentService;

    @ApiOperation(value = "批量新增部门，一个或多个均可")
    @PostMapping("/saveAll")
    public Response add (@ApiParam(value = "List<部门>信息", required = true) @RequestBody List<DdDepartment> departments) {
        return ddDepartmentService.saveAll(departments);
    }

    @ApiOperation(value = "获取部门信息")
    @GetMapping("/{id}")
    public Response add (@ApiParam(value = "部门Id", required = true) @PathVariable(DepartmentTableField.ID) Long id) {
        return ddDepartmentService.findById(id);
    }

}
