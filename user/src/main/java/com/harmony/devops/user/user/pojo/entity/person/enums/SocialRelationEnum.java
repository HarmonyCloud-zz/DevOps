package com.harmony.devops.user.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 社会关系枚举
 */
public enum  SocialRelationEnum {
    母亲("M"),
    朋友("Y"),
    本人("I"),
    同事("W"),
    同学("T"),
    其它("O"),
    配偶("C"),
    父亲("F"),
    子女("H"),
    兄弟("B"),
    姐妹("S");

    private String code;

    SocialRelationEnum(String code) {
        this.code = code;
    }

    public static SocialRelationEnum getEnum(String code) {
        for (SocialRelationEnum c : SocialRelationEnum.values()) {
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
