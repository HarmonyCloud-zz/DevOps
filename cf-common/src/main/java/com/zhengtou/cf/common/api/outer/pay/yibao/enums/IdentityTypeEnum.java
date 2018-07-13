package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

public enum  IdentityTypeEnum {
    MAC("网卡地址"),IMEI("国际移动设备标识"),ID_CARD("用户身份证号"),PHONE("手机号"),EMAIL("邮箱"),USER_ID("用户id"),AGREEMENT_NO("用户纸质订单协议号");
    private String code;

    IdentityTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static IdentityTypeEnum getEnum(String code) {
        for (IdentityTypeEnum c : IdentityTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
