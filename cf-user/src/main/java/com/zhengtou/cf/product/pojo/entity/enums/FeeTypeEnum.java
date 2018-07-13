package com.zhengtou.cf.product.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 费项定义
 */
public enum FeeTypeEnum {
    延期手续费("BROADEN_FEE"), 还款日变更手续费("CYCLE_DAY_CHANGE_FEE"), 提款提货手续费("WITHDRAW_FEE"), 展期手续费("EXTEND_FEE"), 缩期手续费("SHORTEN_FEE"), 降额手续费("SHRINK_FEE")
    , 提前结清手续费("ADV_SETTLE_FEE"), 退货手续费("RETURN_FEE"), 贷款服务费("LOAN_SERV_FEE"), 分期服务费("TERM_SERV_FEE");
    private String code;

    FeeTypeEnum(String code) {
        this.code = code;
    }

    public static FeeTypeEnum getEnum(String code) {
        for (FeeTypeEnum c : FeeTypeEnum.values()) {
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
