package com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums;

/**
 * 职位泛型
 * @author 葛文镇
 */
public enum PositionEnum {
    初级助理人员("BEGINNERS"),
    基层管理人员("JUNIOR"),
    高层管理人员("SENIOR"),
    见习专员("PRACTICE"),
    中层管理人员("MIDDLE"),
    中级技术人员("INTERMEDIATES"),
    普通员工("NORMAL"),
    高级资深人员("ADVANCED");
    private String value;

    PositionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static PositionEnum getEnum(String value) {
        for (PositionEnum c : PositionEnum.values()) {
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
