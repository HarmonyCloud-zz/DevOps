package com.zhengtou.cf.jpush.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.user.pojo.entity.UserEntity;
import com.zhengtou.cf.jpush.pojo.entity.PushPersonMsgEntity;
import com.zhengtou.cf.jpush.pojo.entity.PushSysMsgEntity;
import com.zhengtou.cf.jpush.pojo.enums.MsgStatusEnum;
import com.zhengtou.cf.jpush.pojo.enums.PersonMsgTypeEnum;
import com.zhengtou.cf.jpush.pojo.vo.MsgVO;
import com.zhengtou.cf.jpush.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    BaseDao dao;

    @Override
    public void addSysMsg(String title, String message, Long planSendTime,String thirdMsgId) {
        PushSysMsgEntity pushSysMsgEntity = new PushSysMsgEntity();
        pushSysMsgEntity.setTitle(title);
        pushSysMsgEntity.setMessage(message);
        pushSysMsgEntity.setPlanSendTime(planSendTime);
        pushSysMsgEntity.setMsgStatus(MsgStatusEnum.未发送);
        pushSysMsgEntity.setThirdMsgId(thirdMsgId);
        dao.save(pushSysMsgEntity);
    }

    @Override
    public PushSysMsgEntity getSysMsgByMsgStatus(MsgStatusEnum msgStatus) {
        return dao.get("select p from PushSysMsgEntity p where p.msgStatus=?0", new Object[]{msgStatus});
    }

    @Override
    public void addPersonMsg(UserEntity userEntity, String title, String message, PersonMsgTypeEnum personMsgType,String thirdMsgId) {
        PushPersonMsgEntity pushPersonMsgEntity = new PushPersonMsgEntity();
        pushPersonMsgEntity.setConsumer(userEntity);
        pushPersonMsgEntity.setPersonMsgType(personMsgType);
        pushPersonMsgEntity.setTitle(title);
        pushPersonMsgEntity.setMessage(message);
        pushPersonMsgEntity.setUserNo(userEntity.getUserNo());
        pushPersonMsgEntity.setMsgStatus(MsgStatusEnum.未发送);
        pushPersonMsgEntity.setThirdMsgId(thirdMsgId);
        dao.save(pushPersonMsgEntity);
    }

    @Override
    public PushPersonMsgEntity getPersonMsgByMsgStatus(MsgStatusEnum msgStatus) {
        return dao.get("select p from PushPersonMsgEntity p where p.msgStatus=?0", new Object[]{msgStatus});
    }

    @Override
    public MsgVO getMsgByThirdMsgId(String thirdMsgId) {
        return dao.get("select new com.zhengtou.cf.jpush.pojo.vo.MsgVO(p.title,p.message,p.createTime) from BaseMsgEntity p where p.thirdMsgId=?0",
                new Object[]{thirdMsgId});
    }

    @Override
    public List<MsgVO> findPersonMsgByUserNo(String userNo ,Integer page,Integer size) {
        return dao.find("select new com.zhengtou.cf.jpush.pojo.vo.MsgVO(p.title,p.message,p.createTime) from PushPersonMsgEntity p where p.userNo=?0",
                new Object[]{userNo},page,size);
    }

    @Override
    public Long countPersonMsgByUserNo(String userNo) {
        return dao.count("select count(*) from PushPersonMsgEntity p where p.userNo=?0",
                new Object[]{userNo});
    }

    @Override
    public List<MsgVO> findSysMsgByUserId() {
        return dao.find("select new com.zhengtou.cf.jpush.pojo.vo.MsgVO(p.title,p.message,p.createTime) from PushSysMsgEntity p",
                new Object[]{});
    }
}
