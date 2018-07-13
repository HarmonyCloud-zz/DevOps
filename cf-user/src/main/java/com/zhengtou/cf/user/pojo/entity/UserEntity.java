package com.zhengtou.cf.user.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public abstract class UserEntity extends BaseEntity{
    /**
     * 用户No
     */
    private String userNo= DBUtil.getUserNo();
    /**
     * 用户昵称，默认为手机号
     */
    private String nickName;
    /**
     * 用户名
     */
    @Column(unique = true)
    private String loginName;
    /**
     * 用户密码，默认为手机号后六位
     */
    private String password;
    /**
     * 用户手机号
     */
    @Column(unique = true)
    private String phone;
    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;
    /**
     * 交易密码
     */
    private String tradePwd;
    /**
     * 是否设置交易密码
     */
    private boolean hasTradePwd=false;
    /**
     * 最近登陆时间
     */
    private Long lastLoginTime;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isHasTradePwd() {
        return hasTradePwd;
    }

    public void setHasTradePwd(boolean hasTradePwd) {
        this.hasTradePwd = hasTradePwd;
    }
}
