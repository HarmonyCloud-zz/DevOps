package com.zhengtou.cf.user.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public enum ConsumerUserStatusEnum {
    正常,冻结;
    public String toPrintString() {
        return this.name();
    }
}
