package com.zhengtou.cf.user.pojo.vo.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 栏目权限vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuPermissionVO extends PeakBaseVo{
    public MenuPermissionVO(Long menuId, String menuName,Long perId, String perName, String perPath, String perType) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.perId=perId;
        this.perName = perName;
        this.perPath = perPath;
        this.perType = perType;
    }

    public MenuPermissionVO(Long perId, String perName, String perPath, String perType) {
        this.perId = perId;
        this.perName = perName;
        this.perPath = perPath;
        this.perType = perType;
    }

    private Long menuId;
    private String menuName;
    private Long perId;
    private String perName;
    private String perPath;
    private String perType;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerPath() {
        return perPath;
    }

    public void setPerPath(String perPath) {
        this.perPath = perPath;
    }

    public String getPerType() {
        return perType;
    }

    public void setPerType(String perType) {
        this.perType = perType;
    }
}
