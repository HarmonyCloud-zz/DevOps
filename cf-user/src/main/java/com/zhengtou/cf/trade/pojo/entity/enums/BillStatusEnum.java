package com.zhengtou.cf.trade.pojo.entity.enums;

public enum BillStatusEnum {
    正常("N"),
    已逾期("O"),
    已终止("T"),
    已结清("S");
    private String code;

    BillStatusEnum(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static BillStatusEnum getEnum(String value) {
        for (BillStatusEnum c : BillStatusEnum.values()) {
            if (c.getCode().equals(value)) {
                return c;
            }
        }
        return null;
    }
}
