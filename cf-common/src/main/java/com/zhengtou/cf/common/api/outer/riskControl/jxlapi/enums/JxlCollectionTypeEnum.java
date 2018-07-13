package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.enums;

/**
 * 聚信立采集流程泛型
 * @author 葛文镇
 */
public enum  JxlCollectionTypeEnum {
    提交短信验证码("SUBMIT_CAPTCHA"),重发短信验证码("RESEND_CAPTCHA"),提交查询密码("SUBMIT_QUERY_PWD");
    private String code;
    JxlCollectionTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static JxlCollectionTypeEnum getEnum(String code) {
        for (JxlCollectionTypeEnum c : JxlCollectionTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
