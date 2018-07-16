package com.harmony.devops.user.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 性别枚举
 */
public enum GenderEnum {
    男("M"),
    女("F");

    private String code;

    GenderEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
