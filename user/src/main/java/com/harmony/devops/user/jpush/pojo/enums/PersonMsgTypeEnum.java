package com.harmony.devops.user.jpush.pojo.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public enum PersonMsgTypeEnum {
    还款消息,审批消息,逾期催收消息;
    public String toPrintString() {
        return this.name();
    }
}
