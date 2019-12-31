package com.tfkuding.isv.common.pojo.dd;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author hsy
 * @version 1.0
 * @date 2019/12/31 9:00
 *
 * 钉钉用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity(name = "dd_user")
public class DdUser implements Serializable {
    @ApiModelProperty(name = "Id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(name = "员工在当前企业内的唯一标识，也称staffId")
    @Column(name = "userid")
    private String userid;

    @ApiModelProperty(name = "员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变")
    @Column(name = "unionid")
    private String unionid;

    @ApiModelProperty(name = "员工名字")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(name = "是否已经激活，true表示已激活，false表示未激活")
    @Column(name = "active")
    private Boolean active;

    @ApiModelProperty(name = "在对应的部门中的排序，Map结构的json字符串，key是部门的Id，value是人员在这个部门的排序值")
    @Column(name = "orderInDepts")
    private String orderInDepts;

    @ApiModelProperty(name = "是否为企业的管理员，true表示是，false表示不是")
    @Column(name = "isAdmin")
    private Boolean isAdmin;

    @ApiModelProperty(name = "是否为企业的老板，true表示是，false表示不是")
    @Column(name = "isBoss")
    private Boolean isBoss;

    @ApiModelProperty(name = "在对应的部门中是否为主管：Map结构的json字符串，key是部门的Id，value是人员在这个部门中是否为主管，true表示是，false表示不是")
    @Column(name = "isLeaderInDepts")
    private String isLeaderInDepts;

    @ApiModelProperty(name = "是否号码隐藏，true表示隐藏，false表示不隐藏")
    @Column(name = "isHide")
    private Boolean isHide;

    @ApiModelProperty(name = "成员所属部门id列表")
    @Column(name = "department")
    private String department;

    @ApiModelProperty(name = "职位信息")
    @Column(name = "position")
    private String position;

    @ApiModelProperty(name = "头像url")
    @Column(name = "avatar")
    private String avatar;

    @ApiModelProperty(name = "入职时间。Unix时间戳 （在OA后台通讯录中的员工基础信息中维护过入职时间才会返回)")
    @Column(name = "hiredDate")
    private Date hiredDate;

    @ApiModelProperty(name = "员工工号")
    @Column(name = "jobnumber")
    private String jobnumber;

    @ApiModelProperty(name = "是否是高管")
    @Column(name = "isSenior")
    private Boolean isSenior;

    @ApiModelProperty(name = "用户所在角色列表")
    @Column(name = "roles")
    private String roles;
}
