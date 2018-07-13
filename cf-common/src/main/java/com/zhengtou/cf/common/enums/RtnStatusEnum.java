package com.zhengtou.cf.common.enums;

/**
 * 系统异常状态
 */
public enum RtnStatusEnum {
    SUCCESS("请求操作成功"), FAIL("请求操作失败"), UNKNOW("请求操作异常");

    private String msg = "";

    RtnStatusEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
