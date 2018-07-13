package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.trade.pojo.entity.bill.TermEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.BnpTypeEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.pojo.vo.ReceiptVO;
import com.zhengtou.cf.trade.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class TermServiceImpl implements TermService {
    @Autowired
    BaseDao dao;


    @Override
    public void changeTermStatus(long termId, TermStatusEnum termStatusEnum, String paymentTransNo, String errorCode, String errorDesc) {
        TermEntity term = dao.get(TermEntity.class, termId);
        if(termStatusEnum!=null){
            term.setTermStatus(termStatusEnum);
            if (termStatusEnum.equals(TermStatusEnum.已还款)) {
                term.setPaidOutDate(System.currentTimeMillis());
            }
        }
        term.setLastModifyTime(System.currentTimeMillis());
        if (StringUtils.isNotBlank(paymentTransNo)) {
            term.setPaymentTransNo(paymentTransNo);
        }
        if (StringUtils.isNotBlank(errorCode)) {
            term.setErrorCode(errorCode);
        }
        if (StringUtils.isNotBlank(errorDesc)) {
            term.setErrorDesc(errorDesc);
        }
        dao.modify(term);
    }


    @Override
    public List<ReceiptTaskVO> getReceiptAll(Long pmtDueDateStart, Long pmtDueDateEnd, TermStatusEnum termStatusEnum) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO(t.id,t.paymentTransNo,cu.personal.idNo,c.trade.bankCard" +
                ".cardNo,c.trade.org.name,c.trade.consumerUser.createTime,t.schdAmt,b.schdAmt,t.termStatus,cu.userNo) from TermEntity t,ContractEntity c,BnpEntity b," +
                "ConsumerUserEntity cu where c.trade.consumerUser=cu and t.bill=c.bill and b.term=t and b.bnpType=:bnpType ");
        HashMap<String,Object> where=new HashMap<>();
        where.put("bnpType",BnpTypeEnum.本金);
        if(pmtDueDateStart!=null){
            hql.append(" and t.pmtDueDate>=:pmtDueDateStart ");
            where.put("pmtDueDateStart",pmtDueDateStart);
        }
        if(pmtDueDateEnd!=null){
            hql.append(" and t.pmtDueDate<:pmtDueDateEnd ");
            where.put("pmtDueDateEnd",pmtDueDateEnd);
        }
        if(termStatusEnum!=null){
            hql.append(" and t.termStatus=:termStatus ");
            where.put("termStatus",termStatusEnum);
        }
        hql.append("order by t.createTime");
        return dao.find(hql.toString(),where);
    }

    @Override
    public ReceiptTaskVO getReceiptByTermId(long termId) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO(t.id,t.paymentTransNo,cu.personal.idNo,c.trade.bankCard" +
                ".cardNo,c.trade.org.name,c.trade.consumerUser.createTime,t.schdAmt,b.schdAmt,t.termStatus,cu.userNo) from TermEntity t,ContractEntity c,BnpEntity b," +
                "ConsumerUserEntity cu where t.id=?0 and c.trade.consumerUser=cu and t.bill=c.bill and b.term=t and b.bnpType=?1 order by t.createTime");
        return dao.get(hql.toString(), new Object[]{termId, BnpTypeEnum.本金});
    }

    @Override
    public List<ReceiptVO> getReceiptList(Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.trade.pojo.vo.ReceiptVO(t.id,c.trade.tradeFlowNo,t.termNo,t.pmtDueDate,t.schdAmt," +
                "t.paidOutDate,t.termStatus) from TermEntity t,ContractEntity c where t.isDeleted=false and t.termNo<>?0 and c.bill=t.bill order by t.createTime", new
                Object[]{"总计"}, page, size);
    }

    @Override
    public Long countReceipt() {
        return dao.count("select count(*) from TermEntity t where t.isDeleted=false  and t.termNo<>?0", new Object[]{"总计"});
    }

    @Override
    public List<ReceiptVO> getReceiptByTradeId(long tradeId, Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.trade.pojo.vo.ReceiptVO(t.id,c.trade.tradeFlowNo,t.termNo,t.pmtDueDate,t.schdAmt," +
                "t.paidOutDate,t.termStatus,b.schdAmt,t.errorCode,t.errorDesc) from TermEntity t,ContractEntity c,BnpEntity b where t.isDeleted=false and c.trade.id=?0 and" +
                " t.termNo<>?1 and c.bill=t.bill and b.term=t and b.bnpType=?2 ", new Object[]{tradeId, "总计", BnpTypeEnum.本金}, page, size);
    }

    @Override
    public Long countReceiptByTradeId(long tradeId) {
        return dao.count("select count(t.id) from TermEntity t,ContractEntity c,BnpEntity b where t.isDeleted=false and c.trade.id=?0 and" +
                " t.termNo<>?1 and c.bill=t.bill and b.term=t and b.bnpType=?2 ", new Object[]{tradeId, "总计", BnpTypeEnum.本金});
    }
}
