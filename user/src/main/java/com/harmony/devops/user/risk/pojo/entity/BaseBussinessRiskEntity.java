package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 基础电商数据
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_buss")
public class BaseBussinessRiskEntity extends BaseEntity{
    @OneToOne
    private ConsumerUserEntity user;
    //用户名
    private String user_name;
    //邮箱
    private String email;
    //用户级别
    private String user_level;
    //昵称
    private String nick_name;
    //真实姓名
    private String name;
    //性别
    private String gender;
    //绑定手机号码
    private String mobile;
    //实名认证姓名
    private String real_name;
    //使命认证身份证
    private String identity_code;
    //会员成长值
    private String vip_count;

    public BaseBussinessRiskEntity(String user_name, String email, String user_level, String nick_name) {
        this.user_name = user_name;
        this.email = email;
        this.user_level = user_level;
        this.nick_name = nick_name;
    }


    public ConsumerUserEntity getUser() {
        return user;
    }

    public void setUser(ConsumerUserEntity user) {
        this.user = user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdentity_code() {
        return identity_code;
    }

    public void setIdentity_code(String identity_code) {
        this.identity_code = identity_code;
    }

    public String getVip_count() {
        return vip_count;
    }

    public void setVip_count(String vip_count) {
        this.vip_count = vip_count;
    }
}
