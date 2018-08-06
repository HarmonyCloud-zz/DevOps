package com.harmony.shiro.pojo.entity;

import com.harmony.devops.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
@Entity
@Table(name = "t_shiro_permission")
public class PermissionEntity extends BaseEntity{
    /**
     * 权限名
     */
    public String permissionName;
    /**
     * 权限码
     */
    public String permissionCode;
}
