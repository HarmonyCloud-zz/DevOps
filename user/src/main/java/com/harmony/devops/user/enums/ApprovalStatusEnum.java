package com.harmony.devops.user.enums;

public enum ApprovalStatusEnum {
    正在处理("U"),通过("A"),拒绝("R"), 风控取消("C"), 签署("N"), 放弃("J"), 驳回待补件("B");

    private String code;

    ApprovalStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 普通方法
    public static String getName(String code) {
        for (ApprovalStatusEnum c : ApprovalStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name();
            }
        }
        return null;
    }

    public static ApprovalStatusEnum getEnum(String code) {

        for (ApprovalStatusEnum c : ApprovalStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
