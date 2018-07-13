package com.zhengtou.cf.risk.pojo.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public enum  TDRiskResultEnum {
    通过("PASS"),
    复核("REVIEW"),
    拒绝("REJECT");

    private String code;

    TDRiskResultEnum(String code) {
        this.code = code;
    }

    public static TDRiskResultEnum getEnum(String code) {
        for (TDRiskResultEnum c : TDRiskResultEnum.values()) {
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
