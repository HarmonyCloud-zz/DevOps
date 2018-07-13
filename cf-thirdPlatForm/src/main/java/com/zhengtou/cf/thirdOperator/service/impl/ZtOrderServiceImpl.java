package com.zhengtou.cf.thirdOperator.service.impl;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.entity.ZtOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;
import com.zhengtou.cf.thirdOperator.pojo.vo.ZtOrderVO;
import com.zhengtou.cf.thirdOperator.service.ZtOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class ZtOrderServiceImpl implements ZtOrderService {
    @Autowired
    BaseDao dao;

    @Override
    public void createZtOrderByThirdOrder(ThirdOrderEntity thirdOrder, String note) {
        ZtOrderEntity ztOrderEntity = new ZtOrderEntity(thirdOrder, note);
        dao.save(ztOrderEntity);
    }

    @Override
    public void changeZtOrderStatus(long ztOrderId, SignStatusEnum signStatusEnum) {
        ZtOrderEntity ztOrderEntity = dao.get(ZtOrderEntity.class, ztOrderId);
        ztOrderEntity.setSignStatus(signStatusEnum);
        if (signStatusEnum.equals(SignStatusEnum.已发标)) {
            ztOrderEntity.setSignTime(System.currentTimeMillis());
        }
        dao.modify(ztOrderEntity);
    }

    @Override
    public void changeZtOrderByThirdOrderId(String thirdOrderId, String term) throws BaseException {
        ZtOrderEntity ztOrderEntity = dao.get("from ZtOrderEntity t where t.isDeleted=false and t.thirdOrderId=?0", new
                Object[]{thirdOrderId});
        if (ztOrderEntity == null) {
            throw new BaseException("000", "未发现该订单");
        }
        ztOrderEntity.setLastRepayTime(System.currentTimeMillis());
        ztOrderEntity.setRepayTerm(Integer.parseInt(term));
        dao.modify(ztOrderEntity);
    }

    @Override
    public void changeZtOrderNoteById(long ztOrderId, String note) {
        ZtOrderEntity ztOrderEntity = dao.get(ZtOrderEntity.class, ztOrderId);
        ztOrderEntity.setNote(note);
        dao.modify(ztOrderEntity);
    }

    @Override
    public List<ZtOrderVO> fetchZtOrder(Long createTimeStart, Long createTimeEnd, Long signTimeStart, Long signTimeEnd, SignStatusEnum
            signStatusEnum, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.thirdOperator.pojo.vo.ZtOrderVO(z.createTime,z.id,z.thirdOrderId,z.goodId,z" +
                ".thirdCreateTime,z.lastRepayTime,z.repayTerm,z.thirdOrderNo,z.orderAmt,z.orderTerm,z.signTime,z.note,z.signStatus) from  " +
                "ZtOrderEntity z where z.isDeleted=false ");
        HashMap<String ,Object> where=new HashMap<>();
        if(createTimeStart!=null){
            hql.append(" and z.createTime>:createTimeStart");
            where.put("createTimeStart",createTimeStart);
        }
        if(createTimeEnd!=null){
            hql.append(" and z.createTime<=:createTimeEnd");
            where.put("createTimeEnd",createTimeEnd);
        }
        if(signTimeStart!=null){
            hql.append(" and z.signTime>:signTimeStart");
            where.put("signTimeStart",signTimeStart);
        }
        if(signTimeEnd!=null){
            hql.append(" and z.signTime<=:signTimeEnd");
            where.put("signTimeEnd",signTimeEnd);
        }
        if(signStatusEnum!=null){
            hql.append(" and z.signStatus=:signStatusEnum");
            where.put("signStatusEnum",signStatusEnum);
        }
        hql.append(" order by z.lastRepayTime ,z.signTime,z.createTime desc");
        return dao.find(hql.toString(),where,page,size);
    }

    @Override
    public Long countZtOrder(Long createTimeStart, Long createTimeEnd, Long signTimeStart, Long signTimeEnd, SignStatusEnum signStatusEnum) {
        StringBuffer hql = new StringBuffer("select count(*) from  " +
                "ZtOrderEntity z where z.isDeleted=false ");
        HashMap<String ,Object> where=new HashMap<>();
        if(createTimeStart!=null){
            hql.append(" and z.createTime>:createTimeStart");
            where.put("createTimeStart",createTimeStart);
        }
        if(createTimeEnd!=null){
            hql.append(" and z.createTime<=:createTimeEnd");
            where.put("createTimeEnd",createTimeEnd);
        }
        if(signTimeStart!=null){
            hql.append(" and z.signTime>:signTimeStart");
            where.put("signTimeStart",signTimeStart);
        }
        if(signTimeEnd!=null){
            hql.append(" and z.signTime<=:signTimeEnd");
            where.put("signTimeEnd",signTimeEnd);
        }
        if(signStatusEnum!=null){
            hql.append(" and z.signStatus=:signStatusEnum");
            where.put("signStatusEnum",signStatusEnum);
        }
        return dao.count(hql.toString(),where);
    }
}
