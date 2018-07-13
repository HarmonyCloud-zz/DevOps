package com.zhengtou.cf.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 权限表
 */
@Entity
@Table(name = "t_access_permission")
public class PermissionEntity extends BaseEntity {
    /**
     * 权限名
     */
    @Column(unique = true)
    private String name;
    /**
     * 权限路径
     */
    @Column(unique = true)
    private String path;
    /**
     * 权限类型(query,deleted,save,update,forceDeleted)
     */
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
