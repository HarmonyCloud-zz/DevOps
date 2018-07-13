package com.zhengtou.cf.user.pojo.entity.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 用户信息是否完善
 */
public enum  UserInfoCompleteEnum {
    未完善,已完善;
    public String toPrintString() {
        return this.name();
    }
}
