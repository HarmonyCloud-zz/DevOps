package com.harmony.devops.user.user.pojo.entity;

import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.operator.pojo.entity.StoreEntity;
import com.harmony.devops.user.user.pojo.entity.role.RoleEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 后台用户
 */
@Entity
public class BackUserEntity extends UserEntity {
    //组织机构
    @ManyToOne
    private OrganizationEntity organiza;

    //门店
    @ManyToOne
    private StoreEntity store;

    /**
     * 角色
     */
    @OneToOne
    private RoleEntity role;

    private String menu;

    public OrganizationEntity getOrganiza() {
        return organiza;
    }

    public void setOrganiza(OrganizationEntity organiza) {
        this.organiza = organiza;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
