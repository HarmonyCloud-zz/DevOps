package com.zhengtou.cf.trade.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单模式
 */
public enum TradeModelEnum {
    预审批单号("PA"),
    一般进件审批单号("NA");

    private String code;

    TradeModelEnum(String code) {
        this.code = code;
    }

    public static TradeModelEnum getEnum(String code) {
        for (TradeModelEnum c : TradeModelEnum.values()) {
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
