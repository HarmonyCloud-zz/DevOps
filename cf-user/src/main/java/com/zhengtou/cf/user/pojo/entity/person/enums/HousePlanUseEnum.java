package com.zhengtou.cf.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 房产规划用户
 */
public enum  HousePlanUseEnum {
    住宅("F1"),
    商住_公寓("F2"),
    商业("F3"),
    别墅_写字楼_办公("F4");

    private String code;

    HousePlanUseEnum(String code) {
        this.code = code;
    }

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.HousePlanUseEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.HousePlanUseEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums.HousePlanUseEnum.values()) {
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
