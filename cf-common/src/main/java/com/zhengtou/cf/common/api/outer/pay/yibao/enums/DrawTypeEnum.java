package com.zhengtou.cf.common.api.outer.pay.yibao.enums;

/**
 * 体现类型
 * @author 葛文镇
 */
public enum  DrawTypeEnum {
    NATRALDAY_NORMAL("自然日t+1"),NATRALDAY_URGENT("自然日t+0");
    private String code;

    DrawTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DrawTypeEnum getEnum(String code) {
        for (DrawTypeEnum c : DrawTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
