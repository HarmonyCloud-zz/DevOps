package com.harmony.devops.user.jpush.pojo.entity;

import com.harmony.devops.user.jpush.pojo.enums.PersonMsgTypeEnum;
import com.harmony.devops.user.user.pojo.entity.UserEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
public class PushPersonMsgEntity extends BaseMsgEntity {
    @ManyToOne
    private UserEntity consumer;
    /**
     * 别名
     */
    private String userNo;
    /**
     * 标签
     */
    @Enumerated(EnumType.STRING)
    private PersonMsgTypeEnum personMsgType;

    public UserEntity getConsumer() {
        return consumer;
    }

    public void setConsumer(UserEntity consumer) {
        this.consumer = consumer;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public PersonMsgTypeEnum getPersonMsgType() {
        return personMsgType;
    }

    public void setPersonMsgType(PersonMsgTypeEnum personMsgType) {
        this.personMsgType = personMsgType;
    }
}
