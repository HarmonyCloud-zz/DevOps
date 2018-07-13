package com.zhengtou.cf.user.pojo.entity.person.enums;

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

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.SocialAccountEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.SocialAccountEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums.SocialAccountEnum.values()) {
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
