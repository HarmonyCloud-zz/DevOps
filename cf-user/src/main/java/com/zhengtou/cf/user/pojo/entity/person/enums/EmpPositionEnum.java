package com.zhengtou.cf.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 工作职位枚举
 */
public enum  EmpPositionEnum {
    农_林_牧_渔_水利业生产人员("E"),
    行政机构_企事业单位管理人员("B"),
    军人("G"),
    个体工商户("N"),
    商业_服务人员("D"),
    行政机构_企事业单位负责人或高层管理人员("A"),
    保安_防损("M"),
    专业技术人员("C"),
    经济_金融_法律_教育从业人员("J"),
    销售_中介_业务代表_促销("L"),
    其他从业人员("H"),
    工人("F"),
    公务员_行政机构办事人员和有关人员("K");

    private String code;

    public static EmpPositionEnum getEnum(String code) {
        for (EmpPositionEnum c : EmpPositionEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    EmpPositionEnum(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
