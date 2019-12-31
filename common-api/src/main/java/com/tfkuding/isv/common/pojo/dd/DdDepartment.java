package com.tfkuding.isv.common.pojo.dd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/30 13:30
 * <p>
 * 钉钉部门实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity(name = "dd_department")
public class DdDepartment implements Serializable {

    @Id
    @ApiModelProperty(name = "部门id")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(name = "部门名称")
    private String name;

    @Column(name = "parentid")
    @ApiModelProperty(name = "父部门id，根部门为1")
    private Long parentid;

    @Column(name = "[order]")
    @ApiModelProperty(name = "当前部门在父部门下的所有子部门中的排序值")
    private Long order;

    @Column(name = "createDeptGroup")
    @ApiModelProperty(name = "是否同步创建一个关联此部门的企业群，true表示是，false表示不是")
    private Boolean createDeptGroup;

    @Column(name = "autoAddUser")
    @ApiModelProperty(name = "当部门群已经创建后，是否有新人加入部门会自动加入该群，true表示是，false表示不是")
    private Boolean autoAddUser;

    @Column(name = "deptHiding")
    @ApiModelProperty(name = "是否隐藏部门，true表示隐藏，false表示显示")
    private Boolean deptHiding;

    @Column(name = "deptPermits")
    @ApiModelProperty(name = "可以查看指定隐藏部门的其他部门列表，如果部门隐藏，则此值生效，取值为其他的部门id组成的的字符串，使用“\\|”符号进行分割")
    private String deptPermits;

    @Column(name = "userPermits")
    @ApiModelProperty(name = "可以查看指定隐藏部门的其他人员列表，如果部门隐藏，则此值生效，取值为其他的人员userid组成的的字符串，使用“\\|”符号进行分割")
    private String userPermits;

    @Column(name = "outerDept")
    @ApiModelProperty(name = "是否本部门的员工仅可见员工自己，为true时，本部门员工默认只能看到员工自己")
    private Boolean outerDept;

    @Column(name = "outerPermitDepts")
    @ApiModelProperty(name = "本部门的员工仅可见员工自己为true时，可以配置额外可见部门，值为部门id组成的的字符串，使用“\\|”符号进行分割")
    private String outerPermitDepts;

    @Column(name = "outerPermitUsers")
    @ApiModelProperty(name = "本部门的员工仅可见员工自己为true时，可以配置额外可见人员，值为userid组成的的字符串，使用“\\|”符号进行分割")
    private String outerPermitUsers;

    @Column(name = "orgDeptOwner")
    @ApiModelProperty(name = "企业群群主")
    private String orgDeptOwner;

    @Column(name = "deptManagerUseridList")
    @ApiModelProperty(name = "部门的主管列表，取值为由主管的userid组成的字符串，不同的userid使用“\\|”符号进行分割")
    private String deptManagerUseridList;

    @Column(name = "sourceIdentifier")
    @ApiModelProperty(name = "部门标识字段，开发者可用该字段来唯一标识一个部门，并与钉钉外部通讯录里的部门做映射")
    private String sourceIdentifier;

    @Column(name = "groupContainSubDept")
    @ApiModelProperty(name = "部门群是否包含子部门")
    private Boolean groupContainSubDept;
}
