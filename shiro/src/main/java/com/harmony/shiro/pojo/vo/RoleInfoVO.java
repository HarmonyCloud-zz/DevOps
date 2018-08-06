package com.harmony.shiro.pojo.vo;

import com.harmony.devops.common.pojo.AbstractEntity;

import java.util.List;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
public class RoleInfoVO extends AbstractEntity{
    public RoleInfoVO(String roleCode, List<String> permissionList) {
        this.roleCode = roleCode;
        this.permissionList = permissionList;
    }

    /**
     * 角色编码
     */
    public String roleCode;
    /**
     * 权限编码列表
     */
    public List<String> permissionList;
}
