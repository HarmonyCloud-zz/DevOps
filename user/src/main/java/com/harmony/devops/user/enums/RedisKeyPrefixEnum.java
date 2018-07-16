package com.harmony.devops.user.enums;

public enum RedisKeyPrefixEnum {
    LOGIN_TOKEN("LOGIN_TOKEN_", "登录成功token"),
    PNG_TOKEN("PNG_TOKEN_", "图片验证码token"),
    REG_SMS_CODE("REG_SMS_CODE_", "注册短信验证码"),
    PWD_SMS_CODE("RWD_SMS_CODE_", "修改密码短信验证码"),
    TRADE_PWD_SMS_CODE("TRADE_PWD_SMS_CODE_", "修改交易密码短信验证码");

    private String prefix;
    private String des;

    RedisKeyPrefixEnum(String prefix, String des) {
        this.prefix = prefix;
        this.des = des;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
