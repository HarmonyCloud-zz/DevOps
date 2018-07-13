package com.zhengtou.cf.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 社会身份枚举
 */
public enum SocialIdTypeEnum {
    学生("SI01"),
    在职人员("SI02"),
    自由职业("SI03"),
    企业负责人("SI04"),
    无业("SI05"),
    退休("SI06");


    private String code;

    public String getCode() {
        return code;
    }

    SocialIdTypeEnum(String code) {
        this.code = code;
    }

    public static SocialIdTypeEnum getEnum(String code) {
        for (SocialIdTypeEnum c : SocialIdTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
