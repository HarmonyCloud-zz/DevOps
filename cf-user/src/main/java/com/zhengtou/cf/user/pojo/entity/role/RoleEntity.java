package com.zhengtou.cf.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.enums.RoleStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 角色表
 */
@Entity
@Table(name = "t_role")
public class RoleEntity extends BaseEntity {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private RoleStatusEnum roleStatus;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleStatusEnum getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(RoleStatusEnum roleStatus) {
        this.roleStatus = roleStatus;
    }
}
