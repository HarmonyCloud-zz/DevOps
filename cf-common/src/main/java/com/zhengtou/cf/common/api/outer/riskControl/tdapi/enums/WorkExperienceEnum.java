package com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums;

/**
 * 年收入泛型
 * @author 葛文镇
 */
public enum WorkExperienceEnum {
    二年("2年"),
    三到四年("3-4年"),
    一年("1年"),
    八到九年("8-9年"),
    一年一下("1年以下"),
    五到七年("5-7年"),
    十年以上("10年以上");
    private String value;

    WorkExperienceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static WorkExperienceEnum getEnum(String value) {
        for (WorkExperienceEnum c : WorkExperienceEnum.values()) {
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
