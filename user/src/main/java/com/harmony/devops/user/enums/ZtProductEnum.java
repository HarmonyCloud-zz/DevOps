package com.harmony.devops.user.enums;

public enum ZtProductEnum {
    消费贷("ZTShopping"),现金贷("ZTCASH");
    ZtProductEnum(String code) {
        this.code = code;
    }
    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
