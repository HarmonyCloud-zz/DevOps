package com.harmony.shiro.pojo.entity;

import com.harmony.devops.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
@Entity
@Table(name = "t_shiro_user")
public class UserEntity extends BaseEntity{
    /**
     * 用户手机号
     */
    public String cellPhone;
    /**
     * 登陆用户名
     */
    public String loginName;
    /**
     * 登陆密码
     */
    public String passWord;
    /**
     * 用户真实姓名
     */
    public String realName;
}
