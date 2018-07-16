package com.harmony.devops.user.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 婚姻状况枚举
 */
public enum MarryStatusEnum {
    已婚("C"),
    未婚("S"),
    其他("O");

    private String code;

    MarryStatusEnum(String code) {
        this.code = code;
    }

    public static MarryStatusEnum getEnum(String code) {
        for (MarryStatusEnum c : MarryStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
}
