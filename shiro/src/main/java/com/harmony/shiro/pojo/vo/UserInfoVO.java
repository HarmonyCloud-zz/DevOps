package com.harmony.shiro.pojo.vo;

import com.harmony.devops.common.pojo.AbstractEntity;

import java.util.List;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
public class UserInfoVO extends AbstractEntity{
    public UserInfoVO(String realName, List<RoleInfoVO> roleInfoVOList) {
        this.realName = realName;
        this.roleInfoVOList = roleInfoVOList;
    }

    /**
     * 真实姓名
     */
    public String realName;
    /**
     * 角色编码
     */
    public List<RoleInfoVO> roleInfoVOList;
}
