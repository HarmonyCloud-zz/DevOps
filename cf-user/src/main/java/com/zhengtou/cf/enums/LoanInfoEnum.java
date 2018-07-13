package com.zhengtou.cf.enums;

public enum LoanInfoEnum {
    未申请,审批中, 已授信, 已放款,已还完;

    // 普通方法
    public static String getName(int index) {
        for (LoanInfoEnum c : LoanInfoEnum.values()) {
            if (c.ordinal() == index) {
                return c.name();
            }
        }
        return null;
    }
}
