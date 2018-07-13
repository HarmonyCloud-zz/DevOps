package com.zhengtou.cf.handler.task.trade;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.service.TermService;
import com.zhengtou.cf.common.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 借据定时任务
 */
@Service
public class BillTask {
    private static final Logger logger = LoggerFactory.getLogger(BillTask.class);

    @Autowired
    TermService termService;
    @Autowired
    BaseDao dao;

    /**
     * 还款成功定时器
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void changeBillSuc() {
        logger.info("凌晨一点更改借据得汇总信息:还款成功定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLongAddN(-1), TimeUtil.dateToLongAddN(0), TermStatusEnum.已还款);
        if (receiptTaskVOS.isEmpty()) {
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            BillEntity billEntity = dao.get("select b from BillEntity b ,TermEntity t where t.bill=b and b.termNo=t.termNo and t.id=?0 ", new
                    Object[]{receiptTaskVO.getTermId()});
            if(Integer.parseInt(billEntity.getTermNo())+1>Integer.parseInt(billEntity.getLoanTerm())){
                billEntity.setBillStatus(BillStatusEnum.已结清);
            }else{
                billEntity.setTermNo(billEntity.getTermNo()+1);
            }
            billEntity.setXfrOutPrin(billEntity.getLoanPrin()-receiptTaskVO.getLoanPrin());
            billEntity.setCurrBal(billEntity.getUnpaidAmt()-receiptTaskVO.getReceiptAmt());
            billEntity.setUnpaidAmt(billEntity.getUnpaidAmt()-receiptTaskVO.getReceiptAmt());
            dao.modify(billEntity);
        }
        logger.info("凌晨一点更改借据得汇总信息:还款成功定时器执行---------------结束");
    }

    /**
     * 还款失败定时器。
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void changeBillFai() {
        logger.info("凌晨三点更改借据得汇总信息:还款失败定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLongAddN(-1), TimeUtil.dateToLongAddN(0), TermStatusEnum.已逾期);
        if (receiptTaskVOS.isEmpty()) {
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            BillEntity billEntity = dao.get("select b from BillEntity b ,TermEntity t where t.bill=b and b.termNo=t.termNo and t.id=?0 ", new
                    Object[]{receiptTaskVO.getTermId()});
            billEntity.setBillStatus(BillStatusEnum.已逾期);
            dao.modify(billEntity);
        }
        logger.info("凌晨三点更改借据得汇总信息:还款失败定时器执行---------------结束");
    }
}
