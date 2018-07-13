package com.zhengtou.cf.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 住房状况
 */
public enum  HouseConditionEnum {
    亲属楼宇("D"),
    自置无按揭("A"),
    自置有按揭("B"),
    租房("C"),
    集体宿舍("E"),
    其他("Z");

    private String code;

    HouseConditionEnum(String code) {
        this.code = code;
    }

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.HouseConditionEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.HouseConditionEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums.HouseConditionEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
}
