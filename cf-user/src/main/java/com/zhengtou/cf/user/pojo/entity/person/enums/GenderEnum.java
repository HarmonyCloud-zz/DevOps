package com.zhengtou.cf.user.pojo.entity.person.enums;

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

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.GenderEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.GenderEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums.GenderEnum.values()) {
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
