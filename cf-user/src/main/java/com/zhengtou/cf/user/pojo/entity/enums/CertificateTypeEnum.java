package com.zhengtou.cf.user.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 证件类型枚举
 */
public enum  CertificateTypeEnum {
    身份证("I"),
    临时身份证("T"),
    军官证_士兵证("S"),
    护照("P"),
    营业执照("L"),
    其他有效证件("O"),
    户口簿("R"),
    港澳居民来往内地通行证("H"),
    台湾同胞来往内地通行证("W"),
    外国人居留证("F"),
    警官证("C");

    private String code;
    CertificateTypeEnum(String code) {
        this.code = code;
    }

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.CertificateTypeEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.CertificateTypeEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums
                .CertificateTypeEnum.values()) {
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
