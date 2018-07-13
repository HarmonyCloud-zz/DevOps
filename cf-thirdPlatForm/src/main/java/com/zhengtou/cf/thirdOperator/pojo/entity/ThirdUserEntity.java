package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdTypeEnum;

import javax.persistence.*;

/**
 * 前台用户
 * @author 葛文镇
 */
@Entity
@Table(name = "t_third_user")
public class ThirdUserEntity extends BaseEntity {
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
    private ThirdTypeEnum userType;
    /**
     * 详细街道地址
     */
    private String address;
    /**
     * 身份证
     */
    private String idNo;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 银行卡号
     */
    private String cardNo;
    /**
     * 省市区
     */
    private String city;


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

    public ThirdTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(ThirdTypeEnum userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
