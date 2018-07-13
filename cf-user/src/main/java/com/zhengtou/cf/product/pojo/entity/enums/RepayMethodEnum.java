package com.zhengtou.cf.product.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 费项定义
 */
public enum RepayMethodEnum {
    随借随还("AT"),
    等额本金("AP"),
    等额本息("AI"),
    先息后本("IF"),
    一次性还本付息("OT");
    private String code;

    RepayMethodEnum(String code) {
        this.code = code;
    }

    public static RepayMethodEnum getEnum(String code) {
        for (RepayMethodEnum c : RepayMethodEnum.values()) {
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
