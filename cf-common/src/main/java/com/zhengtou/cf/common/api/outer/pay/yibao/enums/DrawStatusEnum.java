package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

/**
 * 提现状态
 * @author 葛文镇
 */
public enum  DrawStatusEnum {
    ACCEPT("已接收"),PROCESSING("处理中"),WITHDRAW_SUCCESS("提现成功"),WITHDRAW_FAIL("提现失败"),FAIL("系统异常");
    private String code;

    DrawStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DrawStatusEnum getEnum(String code) {
        for (DrawStatusEnum c : DrawStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
