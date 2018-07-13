package com.zhengtou.cf.user.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.entity.enums.BindCardStatusEnum;
import com.zhengtou.cf.user.pojo.vo.BindCardTradeVO;
import com.zhengtou.cf.user.pojo.vo.CardVO;
import com.zhengtou.cf.user.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class BankCardServiceImpl implements BankCardService {
    @Autowired
    BaseDao dao;

    @Override
    public List<CardVO> getBankCardByUserId(long userId) {
        return dao.find("select new com.zhengtou.cf.user.pojo.vo.CardVO(b.id,b.cardNo,b.user.phone,b.bankName,b.isDefault,b.isRepayDefault) from " +
                "BankCardEntity b where b.isDeleted=false and b.user.id=?0 and b.status=?1 order by b.cardNo", new Object[]{userId, BindCardStatusEnum.已绑定});
    }

    @Override
    public BankCardEntity queryDefaultBankCardEntityByUserId(long userId, boolean isDefault) {
        return dao.get("select b from BankCardEntity b where b.user.id=?0 and b.status=?1 and b.isDeleted=false and b.isDefault=?2", new
                Object[]{userId, BindCardStatusEnum.已绑定, isDefault});
    }

    @Override
    public BankCardEntity queryRepayDefaultBankCardEntityByUserId(long userId, boolean isRepayDefault) {
        return dao.get("select b from BankCardEntity b where b.user.id=?0 and b.status=?1 and b.isDeleted=false and b.isRepayDefault=?2", new
                Object[]{userId, BindCardStatusEnum.已绑定, isRepayDefault});
    }

    @Override
    public List<BindCardTradeVO> getBindCardTrade(String consumerRealName, String phone, String idnum, String cardNo, String requestno,
                                                  BindCardStatusEnum status, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.user.pojo.vo.BindCardTradeVO(b.requestno,b.consumerUser.personal.name,b" +
                ".consumerUser.phone,b.consumerUser.personal.idNo,b.bankCard.cardNo,b.bankCard.bankName,b.requesttime,b.status) from BindCardTradeEntity b " +
                "where b.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(consumerRealName)) {
            hql.append("and b.consumerUser.personal.name like :realname");
            where.put("realname", DBUtil.generateLikeSql(consumerRealName));
        }
        if (StringUtils.isNotBlank(phone)) {
            hql.append("and b.consumerUser.phone = :phone");
            where.put("phone", phone);
        }
        if (StringUtils.isNotBlank(idnum)) {
            hql.append("and b.consumerUser.personal.idNo like :idnum");
            where.put("idnum", DBUtil.generateLikeSql(idnum));
        }
        if (StringUtils.isNotBlank(cardNo)) {
            hql.append("and b.bankCard.cardNo like :cardNo");
            where.put("cardNo", DBUtil.generateLikeSql(cardNo));
        }
        if (StringUtils.isNotBlank(requestno)) {
            hql.append("and b.requestno = :requestno");
            where.put("requestno", requestno);
        }
        if (status != null) {
            hql.append("and b.status = :status");
            where.put("status", status);
        }
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public Long countBindCardTrade(String consumerRealName, String phone, String idnum, String cardNo, String requestno, BindCardStatusEnum status) {
        StringBuffer hql = new StringBuffer("select count(*) from BindCardTradeEntity b where b.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(consumerRealName)) {
            hql.append("and b.consumerUser.personal.name like :realname");
            where.put("realname", DBUtil.generateLikeSql(consumerRealName));
        }
        if (StringUtils.isNotBlank(phone)) {
            hql.append("and b.consumerUser.phone = :phone");
            where.put("phone", phone);
        }
        if (StringUtils.isNotBlank(idnum)) {
            hql.append("and b.consumerUser.personal.idNo like :idnum");
            where.put("idnum", DBUtil.generateLikeSql(idnum));
        }
        if (StringUtils.isNotBlank(cardNo)) {
            hql.append("and b.bankCard.cardNo like :cardNo");
            where.put("cardNo", DBUtil.generateLikeSql(cardNo));
        }
        if (StringUtils.isNotBlank(requestno)) {
            hql.append("and b.requestno = :requestno");
            where.put("requestno", requestno);
        }
        if (status != null) {
            hql.append("and b.status = :status");
            where.put("status", status);
        }
        return dao.count(hql.toString(), where);
    }
}
