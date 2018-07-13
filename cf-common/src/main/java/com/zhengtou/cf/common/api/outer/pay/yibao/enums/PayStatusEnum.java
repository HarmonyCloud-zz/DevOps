package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

/**
 * 支付订单状态
 */
public enum  PayStatusEnum {
    ACCEPT("已接收"),TO_VALIDATE("待短验"),PAY_FAIL("支付失败"),PROCESSING("处理中"),PAY_SUCCESS("支付成功"),TIME_OUT("超时失败"),FAIL("系统异常");
    private String code;

    PayStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PayStatusEnum getEnum(String code) {
        for (PayStatusEnum c : PayStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
