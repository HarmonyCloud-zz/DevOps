package com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums;

/**
 * 业务流泛型
 * @author 葛文镇
 */
public enum FlowNoEnum {
    郑投网("zhengtouweb");
    private String value;

    FlowNoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static FlowNoEnum getEnum(String value) {
        for (FlowNoEnum c : FlowNoEnum.values()) {
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
