package com.harmony.shiro.pojo.entity;

import com.harmony.devops.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
@Entity
@Table(name = "t_shiro_role_permission")
public class RolePermissionEntity extends BaseEntity{
    @ManyToOne
    public RoleEntity role;
    @ManyToOne
    public PermissionEntity permission;
}
