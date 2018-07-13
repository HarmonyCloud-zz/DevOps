package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

public enum AdviceSmsTypeEnum {
    MESSAGE("短信形式发送"),VOICE("语音方式发送");
    private String code;

    AdviceSmsTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AdviceSmsTypeEnum getEnum(String code) {
        for (AdviceSmsTypeEnum c : AdviceSmsTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
