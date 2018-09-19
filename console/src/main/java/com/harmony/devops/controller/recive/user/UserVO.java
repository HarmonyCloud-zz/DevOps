package com.harmony.devops.controller.recive.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/4
 * @描述
 */
@Data
@ApiModel(description = "用户vo")
public class UserVO implements Serializable{
    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名",name = "userName")
    public String userName;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名",name = "name")
    public String name;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name = "telePhone")
    public String telePhone;

    /**
     * 关联机构，外键<br>
     * 当userType为商户管理者, 商户操作员, 商户审核员,商户综合操作员时需要关联
     */
    @ApiModelProperty(value = "机构id",name = "orgId")
    public Long orgId;

    /**
     * 关联部门，外键<br>
     * 当userType为管理者时需要关联
     */
    @ApiModelProperty(value = "部门id",name = "departId")
    public Long departId;
}
