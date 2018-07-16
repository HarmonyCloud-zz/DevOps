package com.harmony.devops.user.user.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 卡状态枚举
 */
public enum  BindCardStatusEnum {
    已绑定,已解绑,未知,待短验;
    public String toPrintString() {
        return this.name();
    }
}
