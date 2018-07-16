package com.harmony.devops.user.enums;

/**
 * 银行代码枚举
 * @author 葛文镇
 */
public enum BankNameEnum {
    人民银行("0104"),未知("0000");
    private String code;

    BankNameEnum(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static BankNameEnum getEnum(String value) {
        for (BankNameEnum c : BankNameEnum.values()) {
            if (c.getCode().equals(value)) {
                return c;
            }
        }
        return null;
    }

    public String toString(){
        return this.name();
    }
}
