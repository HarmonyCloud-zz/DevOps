package com.harmony.devops.user.jpush.pojo.entity;

import javax.persistence.Entity;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
public class PushSysMsgEntity extends BaseMsgEntity {
    /**
     * 计划发送时间
     */
    private Long planSendTime;

    public Long getPlanSendTime() {
        return planSendTime;
    }

    public void setPlanSendTime(Long planSendTime) {
        this.planSendTime = planSendTime;
    }
}
