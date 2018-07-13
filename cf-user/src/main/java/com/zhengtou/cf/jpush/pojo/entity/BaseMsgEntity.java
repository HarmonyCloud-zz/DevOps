package com.zhengtou.cf.jpush.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.jpush.pojo.enums.MsgStatusEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_message")
public class BaseMsgEntity extends BaseEntity{
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String message;
    /**
     * 推送状态
     */
    private MsgStatusEnum msgStatus;
    /**
     * 三方消息id
     */
    private String thirdMsgId;
    /**
     * 计划发送时间
     */
    private Long realSendTime;
    /**
     * 错误信息
     */
    @Column(length = 2048)
    private String errorMsg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MsgStatusEnum getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(MsgStatusEnum msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getThirdMsgId() {
        return thirdMsgId;
    }

    public void setThirdMsgId(String thirdMsgId) {
        this.thirdMsgId = thirdMsgId;
    }

    public Long getRealSendTime() {
        return realSendTime;
    }

    public void setRealSendTime(Long realSendTime) {
        this.realSendTime = realSendTime;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
