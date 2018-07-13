package com.zhengtou.cf.handler.task.trade;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.ApplyFormStatusEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.response.ApplyStatusQueryResponse;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 查询申请单状态
 */
@Service
public class QueryApplyStatusTask {
    private static final Logger logger = LoggerFactory.getLogger(QueryApplyStatusTask.class);
    @Autowired
    BaseDao dao;
    @Autowired
    ApplyApi applyApi;
    @Autowired
    SmsApi smsApi;

    /**
     * 45秒通知一次
     * 查询申请单状态
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void queryApplyStatusTask() {
        logger.info("---------查询申请单状态开始-----------");
        List<TradeEntity> tradeEntityList = dao.find("from TradeEntity t where t.isDeleted=false and t.tradeStatus=?0", new
                Object[]{TradeStatusEnum.正在处理});
        if (tradeEntityList.isEmpty()) {
            logger.info("---------查询申请单状态无结果结束-----------");
            return;
        }
        for (TradeEntity tradeEntity : tradeEntityList) {
            try {
                ApplyStatusQueryResponse applyStatusQueryResponse = applyApi.queryApplyStatus(tradeEntity.getAppNO());
                if (applyStatusQueryResponse.getStatus().equals(ApplyFormStatusEnum.U)) {
                    tradeEntity.setTradeStatus(TradeStatusEnum.正在处理);
                } else if (applyStatusQueryResponse.getStatus().equals(ApplyFormStatusEnum.A)) {
                    tradeEntity.setTradeStatus(TradeStatusEnum.通过);
                    tradeEntity.setApprovalAmount(MoneyUtil.moneyToLong(applyStatusQueryResponse.getAccLmt()));
                } else if (applyStatusQueryResponse.getStatus().equals(ApplyFormStatusEnum.J)) {
                    tradeEntity.setTradeStatus(TradeStatusEnum.放弃);
                } else {
                    tradeEntity.setTradeStatus(TradeStatusEnum.拒绝);
                }
                dao.modify(tradeEntity);
            } catch (BaseException e) {
                logger.error("查询申请单异常：" + e.getErrorCode() + "----------------" + e.getErrorMsg());
            }
        }
        logger.info("---------查询申请单状态结束-----------");
    }
}
