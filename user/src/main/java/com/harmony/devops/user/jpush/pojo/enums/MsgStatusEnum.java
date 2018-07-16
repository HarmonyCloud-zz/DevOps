package com.harmony.devops.user.jpush.pojo.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public enum MsgStatusEnum {
    未发送,已发送,发送失败;
    public String toPrintString() {
        return this.name();
    }
}
