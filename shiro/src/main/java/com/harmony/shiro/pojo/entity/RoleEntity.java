package com.harmony.shiro.pojo.entity;

import com.harmony.devops.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述 角色表
 */
@Entity
@Table(name = "t_shiro_role")
public class RoleEntity extends BaseEntity{
    //角色名
    public String roleName;
    //角色编码
    public String roleCode;
}
