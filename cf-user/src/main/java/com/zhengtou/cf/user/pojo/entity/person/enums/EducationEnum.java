package com.zhengtou.cf.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 教育情况枚举
 */
public enum  EducationEnum {
    大学本科("C"),
    大学专科专科学校("D"),
    高中_中专_技校("E"),
    初中("F"),
    初中以下("G"),
    博士及以上("A"),
    硕士("B");

    private String code;

    EducationEnum(String code) {
        this.code = code;
    }

    public static com.zhengtou.cf.common.api.outer.cl.apply.enums.EducationEnum getEnum(String code) {
        for (com.zhengtou.cf.common.api.outer.cl.apply.enums.EducationEnum c : com.zhengtou.cf.common.api.outer.cl.apply.enums.EducationEnum.values()) {
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
