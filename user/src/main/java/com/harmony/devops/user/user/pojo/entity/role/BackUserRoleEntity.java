package com.harmony.devops.user.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.BackUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 用户角色关系表
 */
@Entity
@Table(name = "t_access_user_role")
public class BackUserRoleEntity extends BaseEntity {
    @ManyToOne
    private BackUserEntity backUser;
    @ManyToOne
    private RoleEntity role;

    public BackUserEntity getBackUser() {
        return backUser;
    }

    public void setBackUser(BackUserEntity backUser) {
        this.backUser = backUser;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
