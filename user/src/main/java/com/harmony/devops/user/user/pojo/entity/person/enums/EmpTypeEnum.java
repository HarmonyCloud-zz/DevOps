package com.harmony.devops.user.user.pojo.entity.person.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 工作性质枚举
 */
public enum  EmpTypeEnum {
    农_林_牧_渔业("A"),
    卫生_社会保障和社会福利业("Q"),
    电力_燃气及水的生产和供应业("D"),
    科学研究_技术服务业和地质勘察业("M"),
    批发和零售业("H"),
    其他("Z"),
    教育("P"),
    制造业("C"),
    租赁和商务服务业("L"),
    信息传输_计算机服务和软件业("G"),
    国际组织("T"),
    居民服务和其他服务业("O"),
    采掘业("B"),
    房地产业("K"),
    公共管理和社会组织("S"),
    交通运输_仓储和邮政业("F"),
    水利_环境和公共设施管理业("N"),
    金融业("J"),
    文化_体育和娱乐业("R"),
    建筑业("E"),
    住宿和餐饮业("I");

    private String code;
    EmpTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EmpTypeEnum getEnum(String code) {
        for (EmpTypeEnum c : EmpTypeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
