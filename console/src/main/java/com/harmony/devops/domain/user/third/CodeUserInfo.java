package com.harmony.devops.domain.user.third;

import com.harmony.devops.domain.user.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/19
 * @描述
 */
@Data
@MappedSuperclass
public abstract class CodeUserInfo extends ThirdAPILoginUser{
    @ManyToOne
    protected User creater;//创建人

    protected String email;//邮箱
    protected String username;//登陆用户名
    protected String name;//展示用户名
    protected String skype;//账号名称
    protected String organization;//机构名称
    protected String projects_limit="2";//新建项目限制
    protected boolean admin=false;//是否是管理员true/false（default）
    protected boolean can_create_group=false;//是否可以新建群组true/false(default)
    protected boolean skip_confirmation=true;//跳过邮箱验证true/false
    protected boolean private_profile=true;//私有化个人配置true/false


}
