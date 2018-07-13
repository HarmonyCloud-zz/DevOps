package com.zhengtou.cf.trade.pojo.entity.enums;

public enum BnpTypeEnum {
    利息("INTEREST"),
    平台服务费("DSPT"),
    分期服务费("TERM_SERV_FEE"),
    贷款服务费("LOAN_SERV_FEE"),
    本金("PRINCIPAL"),
    总计服务费("ALL");
    private String code;

    BnpTypeEnum(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static BnpTypeEnum getEnum(String value) {
        for (BnpTypeEnum c : BnpTypeEnum.values()) {
            if (c.getCode().equals(value)) {
                return c;
            }
        }
        return null;
    }
}
