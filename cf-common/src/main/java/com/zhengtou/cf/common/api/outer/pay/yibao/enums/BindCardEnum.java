package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

public enum  BindCardEnum {
    ACCEPT("已接收"),TO_ENHANCED("待补充鉴权"),TO_VALIDATE("待短验"),BIND_SUCCESS("绑卡成功"),BIND_FAIL("绑卡失败"),BIND_ERROR("绑卡异常"),TIME_OUT("超时失败"),WAIT_BIND("待绑卡"),FAIL("系统异常");
    private String code;

    BindCardEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BindCardEnum getEnum(String code) {
        for (BindCardEnum c : BindCardEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
