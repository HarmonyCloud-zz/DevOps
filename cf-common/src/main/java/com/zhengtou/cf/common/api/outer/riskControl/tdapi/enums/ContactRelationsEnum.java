package com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums;

/**
 * 联系人社会关系泛型
 * @author 葛文镇
 */
public enum ContactRelationsEnum {
    同事("coworker"),
    母亲("mother"),
    父亲("father"),
    其他亲属("other_relative"),
    朋友("friend"),
    配偶("spouse"),
    其他("others"),
    子女("child");
    private String value;

    ContactRelationsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ContactRelationsEnum getEnum(String value) {
        for (ContactRelationsEnum c : ContactRelationsEnum.values()) {
            if (c.getValue().equals(value)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
