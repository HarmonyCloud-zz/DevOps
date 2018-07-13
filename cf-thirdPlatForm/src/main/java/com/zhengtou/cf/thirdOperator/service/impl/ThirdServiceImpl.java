package com.zhengtou.cf.thirdOperator.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.inner.third.reciveVO.QueryThirdUserVO;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdOrderVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRepaymentVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRiskVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdUserVO;
import com.zhengtou.cf.thirdOperator.service.ThirdService;
import com.zhengtou.cf.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class ThirdServiceImpl implements ThirdService {
    @Autowired
    BaseDao dao;

    @Override
    public List<ThirdUserVO> queryThirdUserList(QueryThirdUserVO queryThirdUserVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.thirdOperator.pojo.vo.ThirdUserVO(t.id,t.userNo,t.phone,t.userType,t.address,t" +
                ".zhimaScore,t" +
                ".idNo,t.name,t.cardNo,t.city) from ThirdUserEntity t where t.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(queryThirdUserVO.getAddress())) {
            hql.append(" and t.address like :address");
            where.put("address", DBUtil.generateLikeSql(queryThirdUserVO.getAddress()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getCardNo())) {
            hql.append(" and t.cardNo like :cardNo");
            where.put("cardNo", DBUtil.generateLikeSql(queryThirdUserVO.getCardNo()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getCity())) {
            hql.append(" and t.city like :city");
            where.put("city", DBUtil.generateLikeSql(queryThirdUserVO.getCity()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getIdNo())) {
            hql.append(" and t.idNo like :idNo");
            where.put("idNo", DBUtil.generateLikeSql(queryThirdUserVO.getIdNo()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getName())) {
            hql.append(" and t.name =:name");
            where.put("name", queryThirdUserVO.getName());
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getPhone())) {
            hql.append(" and t.phone = :phone");
            where.put("phone", queryThirdUserVO.getPhone());
        }
        if (queryThirdUserVO.getZhimaScoreStart() != null) {
            hql.append(" and t.zhimaScore>= :zhimaScoreStart");
            where.put("zhimaScoreStart", queryThirdUserVO.getZhimaScoreStart());
        }
        if (queryThirdUserVO.getZhimaScoreEnd() != null) {
            hql.append(" and t.zhimaScore<= :zhimaScoreEnd");
            where.put("zhimaScoreEnd", queryThirdUserVO.getZhimaScoreEnd());
        }
        if (queryThirdUserVO.getUserType() != null) {
            hql.append(" and t.userType =:userType");
            where.put("userType", queryThirdUserVO.getUserType());
        }
        hql.append(" group by t.userType ,t.id");
        hql.append(" order by t.createTime desc");
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public Long countThirdUserList(QueryThirdUserVO queryThirdUserVO) {
        StringBuffer hql = new StringBuffer("select count(*) from ThirdUserEntity t where t.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(queryThirdUserVO.getAddress())) {
            hql.append(" and t.address like :address");
            where.put("address", DBUtil.generateLikeSql(queryThirdUserVO.getAddress()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getCardNo())) {
            hql.append(" and t.cardNo like :cardNo");
            where.put("cardNo", DBUtil.generateLikeSql(queryThirdUserVO.getCardNo()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getCity())) {
            hql.append(" and t.city like :city");
            where.put("city", DBUtil.generateLikeSql(queryThirdUserVO.getCity()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getIdNo())) {
            hql.append(" and t.idNo like :idNo");
            where.put("idNo", DBUtil.generateLikeSql(queryThirdUserVO.getIdNo()));
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getName())) {
            hql.append(" and t.name =:name");
            where.put("name", queryThirdUserVO.getName());
        }
        if (StringUtils.isNotBlank(queryThirdUserVO.getPhone())) {
            hql.append(" and t.phone = :phone");
            where.put("phone", queryThirdUserVO.getPhone());
        }
        if (queryThirdUserVO.getZhimaScoreStart() != null) {
            hql.append(" and t.zhimaScore>= :zhimaScoreStart");
            where.put("zhimaScoreStart", queryThirdUserVO.getZhimaScoreStart());
        }
        if (queryThirdUserVO.getZhimaScoreEnd() != null) {
            hql.append(" and t.zhimaScore<= :zhimaScoreEnd");
            where.put("zhimaScoreEnd", queryThirdUserVO.getZhimaScoreEnd());
        }
        if (queryThirdUserVO.getUserType() != null) {
            hql.append(" and t.userType =:userType");
            where.put("userType", queryThirdUserVO.getUserType());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public List<ThirdRiskVO> queryThirdRiskByUserId(long thirdUserId, Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRiskVO(t.name,t.idNo,t.industryfocus) from ThirdRiskEntity t where t" +
                ".isDeleted=false and t.thirdUser.id=?0 order by t.createTime desc", new Object[]{thirdUserId}, page, size);
    }

    @Override
    public Long countThirdRiskByUserId(long thirdUserId) {
        return dao.count("select count(*) from ThirdRiskEntity t where t" +
                ".isDeleted=false and t.thirdUser.id=?0 ", new Object[]{thirdUserId});
    }

    @Override
    public List<ThirdOrderVO> queryThirdOrderByUserId(Long thirdUserId,String thirOrderId, Integer page, Integer size) {
        StringBuffer hql=new StringBuffer("select new com.zhengtou.cf.thirdOperator.pojo.vo.ThirdOrderVO(t.id,t.thirdOrderId,t.goodId,t.thirdCreateTime,t.thirdOrderNo,t.orderTerm," +
                "t.isNew,t.isInstall,t.monthAmt,t.createTime) from ThirdOrderEntity t");
        HashMap<String,Object> where =new HashMap<>();
        if(thirdUserId!=null){
            hql.append(" ,ThirdUserEntity tu where t.isDeleted=false and t.idNo=tu.idNo and tu.id=:thirdUserId and tu.isDeleted=false");
            where.put("thirdUserId",thirdUserId);
        }else{
            hql.append(" where t.isDeleted=false");
        }
        if(StringUtils.isNotBlank(thirOrderId)){
            hql.append(" and t.thirOrderId=:thirOrderId ");
            where.put("thirOrderId",thirOrderId);
        }
        hql.append(" order by t.createTime desc");
        return dao.find(hql.toString(),where,page,size);
    }

    @Override
    public Long countThirdOrderByUserId(Long thirdUserId,String thirOrderId) {
        StringBuffer hql=new StringBuffer("select count(*) from ThirdOrderEntity t");
        HashMap<String,Object> where =new HashMap<>();
        if(thirdUserId!=null){
            hql.append(" ,ThirdUserEntity tu where t.isDeleted=false and t.idNo=tu.idNo and tu.id=:thirdUserId and tu.isDeleted=false");
            where.put("thirdUserId",thirdUserId);
        }else{
            hql.append(" where t.isDeleted=false");
        }
        if(StringUtils.isNotBlank(thirOrderId)){
            hql.append(" and t.thirOrderId=:thirOrderId ");
            where.put("thirOrderId",thirOrderId);
        }
        return dao.count(hql.toString(),where);
    }

    @Override
    public List<ThirdRepaymentVO> queryThirdRepayByOrderId(long thirdOrderId, Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRepaymentVO(t.thirdRepaymentId,t.number,t.amountNeed,t.amountAlready,t.createTime," +
                "t.thirdCreateTime,t.thirdLastPaytime,t.payTime,t.payDetail,t.totalAmt,t.overDueDay,t.overDueAmt) from ThirdRepaymentEntity t," +
                "ThirdOrderEntity tu where t.isDeleted=false and t.thirdOrder=tu and tu.id=?0 and tu.isDeleted=false order by t.createTime desc", new
                Object[]{thirdOrderId}, page, size);
    }

    @Override
    public Long countThirdRepayByOrderId(long thirdOrderId) {
        return dao.count("select count(*) from ThirdRepaymentEntity t," +
                "ThirdOrderEntity tu where t.isDeleted=false and t.thirdOrder=tu and tu.id=?0 and tu.isDeleted=false",
                new Object[]{thirdOrderId});
    }

    @Override
    public ThirdOrderEntity getThirdOrderByThirdOrderId(String thirdOrderId) {
        return dao.get("select t from ThirdOrderEntity t where t.thirdOrderId=?0 ",new Object[]{thirdOrderId});
    }
}
