package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

/**
 * 用户基础信息
 * @author 葛文镇
 */
public class UserBasicVO {
    //用户出生地:省份
    private String user_province;
    //用户出生地:城市
    private String user_city;
    //用户出生地:区县
    private String user_region;
    //身份证号码
    private String user_idcard;
    //年龄
    private String user_age;
    //性别
    private String user_gender;
    //手机号码
    private String user_phone;
    //身份证是否有效
    private String user_idcard_valid;
    //姓名
    private String user_name;
    // 手机号归属地:省份
    private String user_phone_city;
    // 手机号归属地:城市
    private String user_phone_province;
    // 运营商类别
    private String user_phone_operator;

    public String getUser_province() {
        return user_province;
    }

    public void setUser_province(String user_province) {
        this.user_province = user_province;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_region() {
        return user_region;
    }

    public void setUser_region(String user_region) {
        this.user_region = user_region;
    }

    public String getUser_idcard() {
        return user_idcard;
    }

    public void setUser_idcard(String user_idcard) {
        this.user_idcard = user_idcard;
    }

    public String getUser_age() {
        return user_age;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_idcard_valid() {
        return user_idcard_valid;
    }

    public void setUser_idcard_valid(String user_idcard_valid) {
        this.user_idcard_valid = user_idcard_valid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone_city() {
        return user_phone_city;
    }

    public void setUser_phone_city(String user_phone_city) {
        this.user_phone_city = user_phone_city;
    }

    public String getUser_phone_province() {
        return user_phone_province;
    }

    public void setUser_phone_province(String user_phone_province) {
        this.user_phone_province = user_phone_province;
    }

    public String getUser_phone_operator() {
        return user_phone_operator;
    }

    public void setUser_phone_operator(String user_phone_operator) {
        this.user_phone_operator = user_phone_operator;
    }
}
