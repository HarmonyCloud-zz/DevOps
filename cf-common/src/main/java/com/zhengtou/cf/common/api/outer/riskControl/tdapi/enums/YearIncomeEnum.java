package com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums;

/**
 * 年收入泛型
 * @author 葛文镇
 */
public enum YearIncomeEnum {
    一万以下("10000以下"),
    一万到五万("10000-50000"),
    五万到十万("50000-100000"),
    十万到二十万("100000-200000"),
    二十万以上("200000以上");
    private String value;

    YearIncomeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static YearIncomeEnum getEnum(String value) {
        for (YearIncomeEnum c : YearIncomeEnum.values()) {
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
