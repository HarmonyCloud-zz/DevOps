package com.zhengtou.cf.common.enums;

/**
 * 通信层
 */
public enum ReturnMsgEnum {

    SUCCESS("200", "请求成功"),
    ERROR("500", "服务器异常，请稍后重试");

    private String code;
    private String msg;

    ReturnMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ReturnMsgEnum getEnum(String code) {
        for (ReturnMsgEnum c : ReturnMsgEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
