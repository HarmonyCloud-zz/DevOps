package com.harmony.devops.user.jpush.service;

import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.jpush.pojo.entity.PushPersonMsgEntity;
import com.harmony.devops.user.jpush.pojo.entity.PushSysMsgEntity;
import com.harmony.devops.user.jpush.pojo.enums.MsgStatusEnum;
import com.harmony.devops.user.jpush.pojo.enums.PersonMsgTypeEnum;
import com.harmony.devops.user.jpush.pojo.vo.MsgVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface MsgService {
    /**
     * 增加系统消息
     */
    void addSysMsg(String title,String message,Long planSendTime,String thirdMsgId);
    /**
     * 根据状态查询系统消息
     */
    PushSysMsgEntity getSysMsgByMsgStatus(MsgStatusEnum msgStatus);

    /**
     * 增加用户消息
     */
    void addPersonMsg(UserEntity userEntity, String title, String message, PersonMsgTypeEnum personMsgType,String thirdMsgId);

    /**
     * 根据状态查询用户消息
     */
    PushPersonMsgEntity getPersonMsgByMsgStatus(MsgStatusEnum msgStatus);

    /**
     * 根据三方消息id，拿到消息
     */
    MsgVO getMsgByThirdMsgId(String thirdMsgId);

    /**
     * 根据用户id，拿到消息列表
     */
    List<MsgVO> findPersonMsgByUserNo(String userNo,Integer page,Integer size);
    Long countPersonMsgByUserNo(String userNo);

    /**
     * 拿到系统消息列表
     */
    List<MsgVO> findSysMsgByUserId();
}
