package com.harmony.devops.user.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 目录与权限对照表
 */
@Entity
@Table(name = "t_access_menu_permission")
public class MenuPermissionEntity extends BaseEntity {
    @ManyToOne
    private MenuEntity menu;
    @ManyToOne
    private PermissionEntity permission;

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public PermissionEntity getPermission() {
        return permission;
    }

    public void setPermission(PermissionEntity permission) {
        this.permission = permission;
    }
}
