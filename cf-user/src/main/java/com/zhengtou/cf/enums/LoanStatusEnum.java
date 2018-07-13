package com.zhengtou.cf.enums;

public enum LoanStatusEnum {
    未审核("U"), 执行中("P"), 已完成("S"), 已撤消("C"), 已失效("V"), 放款失败("F");

    private String code;

    LoanStatusEnum(String value) {
        this.code = value;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static LoanStatusEnum getEnum(String value) {
        for (LoanStatusEnum c : LoanStatusEnum.values()) {
            if (c.getCode().equals(value)) {
                return c;
            }
        }
        return null;
    }
}
