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
@Table(name = "t_shiro_user_role")
public class UserRoleEntity extends BaseEntity{
    @ManyToOne
    public UserEntity user;
    @ManyToOne
    public RoleEntity role;
}
