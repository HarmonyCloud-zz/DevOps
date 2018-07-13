package com.zhengtou.cf.user.pojo.vo.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 栏目vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuVO extends PeakBaseVo{
    /**
     * hql
     */
    public MenuVO(Long id, String name, String path, String title, Long childrenId, String childrenName, String childrenpath, String childrentitle) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.title = title;
        this.childrenId = childrenId;
        this.childrenName = childrenName;
        this.childrenpath = childrenpath;
        this.childrentitle = childrentitle;
    }

    /**
     * 构造
     */
    public MenuVO(Long id, String name, String path, String title) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.title = title;
    }

    private Long id;
    private String name;
    private String path;
    private String title;
    /**
     * 下级
     */
    private MenuVO children;
    /**
     * 下级属性(检索需要)
     */
    private Long childrenId;
    private String childrenName;
    private String childrenpath;
    private String childrentitle;

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

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public String getChildrenpath() {
        return childrenpath;
    }

    public void setChildrenpath(String childrenpath) {
        this.childrenpath = childrenpath;
    }

    public String getChildrentitle() {
        return childrentitle;
    }

    public void setChildrentitle(String childrentitle) {
        this.childrentitle = childrentitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuVO getChildren() {
        return children;
    }

    public void setChildren(MenuVO children) {
        this.children = children;
    }

    public Long getChildrenId() {
        return childrenId;
    }

    public void setChildrenId(Long childrenId) {
        this.childrenId = childrenId;
    }
}
