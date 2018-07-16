package com.harmony.devops.user.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 社交账户类型
 */
public enum  SocialAccountEnum {
    QQ号码("Q"),
    微信账号("W"),
    京东账号("J"),
    淘宝账号("T");

    private String code;

    SocialAccountEnum(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
