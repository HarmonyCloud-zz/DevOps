package com.zhengtou.cf.handler.task.trade;

import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.service.TermService;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.common.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 葛文镇
 * 贷款成功通知
 * //TODO 暂用嵌入方式做
 */
@Service
public class LoanNoticeTask {
    private static final Logger logger = LoggerFactory.getLogger(LoanNoticeTask.class);
    @Autowired
    TradeService tradeService;
    @Autowired
    SmsApi smsApi;
    @Autowired
    TermService termService;

    /**
     * 45秒通知一次
     * 审批成功通知
     * 审批-通过
     */
    @Scheduled(cron = "*/45 * * * * ?")
    public void approvalSucNotice() {
    }

    /**
     * 每天上午11点通知一次
     * 补充银行卡资金
     */
    @Scheduled(cron = "0 0 11 * * ?")
    public void prePaidNotice() {
        String date = TimeUtil.dateToString(System.currentTimeMillis());
        logger.info("补充资金通知定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLong(date), TimeUtil.dateToLongAddOne(date), TermStatusEnum.待还款);
        if(receiptTaskVOS.isEmpty()){
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            //TODO 通知补充银行卡金额
        }
        logger.info("补充资金通知定时器执行---------------完毕");
    }

    /**
     * 扣款失败逾期通知
     * 每天一次
     */
    @Scheduled(cron = "0 0 20 * * ?")
    public void overDueNotice() {

    }
}
