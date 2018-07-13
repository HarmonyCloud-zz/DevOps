package com.zhengtou.cf.trade.pojo.entity.enums;

/**
 * 期供状态
 */
public enum TermStatusEnum {
    待还款("U"),
    还款中("I"),
    已逾期("O"),
    已还款("S"),
    延期扣款("YK"),
    扣款失败("F");
    private String code;

    TermStatusEnum(String value) {
        this.code = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 普通方法
    public static String getName(String value) {
        for (TermStatusEnum c : TermStatusEnum.values()) {
            if (c.getCode().equals(value)) {
                return c.name();
            }
        }
        return null;
    }
}
