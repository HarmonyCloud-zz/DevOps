package com.harmony.devops.user.user.pojo.entity.role;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 栏目管理
 */
@Entity
@Table(name = "t_access_menu")
public class MenuEntity extends BaseEntity{
    /**
     * 栏目名
     */
    private String name;
    /**
     * 栏目路径
     */
    @Column(unique = true)
    private String path;
    /**
     * 标题
     */
    private String title;
    /**
     * 上级
     */
    @OneToOne
    private MenuEntity parent;
    /**
     * 下级
     */
    @ManyToOne
    private MenuEntity children;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuEntity getParent() {
        return parent;
    }

    public void setParent(MenuEntity parent) {
        this.parent = parent;
    }

    public MenuEntity getChildren() {
        return children;
    }

    public void setChildren(MenuEntity children) {
        this.children = children;
    }
}
