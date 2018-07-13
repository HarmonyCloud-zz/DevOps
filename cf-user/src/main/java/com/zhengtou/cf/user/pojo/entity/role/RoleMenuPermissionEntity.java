package com.zhengtou.cf.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_role_menu_permission")
public class RoleMenuPermissionEntity extends BaseEntity{
    @ManyToOne
    private RoleEntity role;
    @ManyToOne
    private MenuEntity menu;
    @ManyToOne
    private PermissionEntity permission;

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}
