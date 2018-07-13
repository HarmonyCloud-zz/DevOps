package com.zhengtou.cf.handler.task.trade;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.PayStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.jpush.PushAPI;
import com.zhengtou.cf.jpush.pojo.enums.PersonMsgTypeEnum;
import com.zhengtou.cf.jpush.service.MsgService;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.service.TermService;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.service.ConsumerUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 葛文镇
 * 资金定时任务
 */
@Service
public class FundsTask {
    private static final Logger logger = LoggerFactory.getLogger(FundsTask.class);

    @Autowired
    PayApi payApi;
    @Autowired
    TermService termService;
    @Autowired
    BaseDao dao;
    @Autowired
    SmsApi smsApi;
    @Autowired
    PushAPI pushAPI;
    @Autowired
    MsgService msgService;
    @Autowired
    ConsumerUserService consumerUserService;

    /**
     * 每天下午17点执行
     * 对当天正常到期款项进行扣款
     */
    @Scheduled(cron = "0 0 17 * * ?")
    public void changeBack() {
        String date = TimeUtil.dateToString(System.currentTimeMillis());
        logger.info("对当天正常到期款项进行扣款定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLong(date), TimeUtil.dateToLongAddOne(date), TermStatusEnum
                .待还款);
        if (receiptTaskVOS.isEmpty()) {
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            String cardNo = receiptTaskVO.getCardNo();
            String transacNo = DBUtil.getTransacNo();
            try {
                payApi.noSmsPay(transacNo, receiptTaskVO.getIdCard(), cardNo.substring(0, 6), cardNo.substring(cardNo.length()
                        - 4, cardNo.length()), MoneyUtil.moneyToString(receiptTaskVO.getReceiptAmt()), receiptTaskVO.getOrgName(), TimeUtil
                        .timeToString(System.currentTimeMillis()), TimeUtil.timeToString(receiptTaskVO.getUserCretime()));
            } catch (BaseException e) {
                logger.error("对当天正常到期款项进行扣款定时器执行异常：" + JSON.toJSONString(receiptTaskVO));
                termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.扣款失败, transacNo, e.getErrorCode(), e.getErrorMsg());
            }
            termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.还款中, transacNo, "", "");
        }
        logger.info("对当天正常到期款项进行扣款定时器执行---------------完毕");
    }

    /**
     * 查询扣款状态定时器
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void queryChangeBackStatus() {
        logger.info("查询扣款状态定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(null, null, TermStatusEnum
                .还款中);
        if (receiptTaskVOS.isEmpty()) {
            logger.info("查询扣款状态定时器执行---------------无结果返回");
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            try {
                PayStatusEnum payStatusEnum = payApi.queryPayStatus(receiptTaskVO.getPaymentTransNo());
                if (payStatusEnum.equals(PayStatusEnum.PAY_SUCCESS)) {
                    termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.已还款, "", "", "");
                }
            } catch (BaseException e) {
                termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.扣款失败, "", e.getErrorCode(), e.getErrorMsg());
            }
            ConsumerUserEntity consumerUserEntity=consumerUserService.getConsumerUserByUserNo(receiptTaskVO.getUserNo());
            try {
                String thirdMsgId = pushAPI.sendPushAliasAllMessage(receiptTaskVO.getUserNo(),"【郑投网】恭喜您还款成功");
                msgService.addPersonMsg(consumerUserEntity,"【郑投网】","恭喜您还款成功", PersonMsgTypeEnum.还款消息,thirdMsgId);
            } catch (BaseException e) {
                try {
                    smsApi.sendSms(consumerUserEntity.getPhone(),"【郑投网】恭喜您还款成功");
                } catch (BaseException e1) {
                    e1.printStackTrace();
                }
            }
        }
        logger.info("查询扣款状态定时器执行---------------完毕");
    }

    /**
     * 每小时执行一次，对延期项目
     * 对15天前非正常到期款项进行扣款
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void changeBackOld() {
        logger.info("对15天前非正常到期款项进行扣款定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLongAddN(-15), TimeUtil.dateToLongAddN(1), TermStatusEnum.扣款失败);
        if (receiptTaskVOS.isEmpty()) {
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            String cardNo = receiptTaskVO.getCardNo();
            String transacNo = DBUtil.getTransacNo();
            try {
                payApi.noSmsPay(transacNo, receiptTaskVO.getIdCard(), cardNo.substring(0, 5), cardNo.substring(cardNo.length()
                        - 4, cardNo.length()), MoneyUtil.moneyToString(receiptTaskVO.getReceiptAmt()), receiptTaskVO.getOrgName(), TimeUtil
                        .timeToString(System.currentTimeMillis
                        ()), TimeUtil.timeToString(receiptTaskVO.getUserCretime()));
            } catch (BaseException e) {
                logger.error("对15天前非正常到期款项进行扣款定时器执行异常：" + JSON.toJSONString(receiptTaskVO));
                termService.changeTermStatus(receiptTaskVO.getTermId(), null, transacNo, e.getErrorCode(), e.getErrorMsg());
            }
            termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.还款中, transacNo, "", "");
        }
        logger.info("对15天前非正常到期款项进行扣款定时器执行---------------完毕");
    }

    /**
     * 更改逾期
     * 每天晚上11点执行
     * 将连续扣款15天项目，改为逾期
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void changeTermStatus() {
        logger.info("当天更改逾期定时器执行---------------开始");
        List<ReceiptTaskVO> receiptTaskVOS = termService.getReceiptAll(TimeUtil.dateToLongAddN(-16), TimeUtil.dateToLongAddN(-15), TermStatusEnum
                .扣款失败);
        if (receiptTaskVOS.isEmpty()) {
            return;
        }
        for (ReceiptTaskVO receiptTaskVO : receiptTaskVOS) {
            termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.已逾期, "", "", "");
        }
        logger.info("当天更改逾期定时器执行---------------完毕");
    }
}
