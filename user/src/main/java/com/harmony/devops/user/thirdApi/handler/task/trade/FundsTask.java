package com.harmony.devops.user.thirdApi.handler.task.trade;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.harmony.devops.user.jpush.PushAPI;
import com.harmony.devops.user.jpush.service.MsgService;
import com.harmony.devops.user.user.service.ConsumerUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author 葛文镇
 * 资金定时任务
 */
@Service
public class FundsTask {
    private static final Logger logger = LoggerFactory.getLogger(FundsTask.class);

    @Autowired
    BaseDao dao;
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
        logger.info("对当天正常到期款项进行扣款定时器执行---------------完毕");
    }

    /**
     * 查询扣款状态定时器
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void queryChangeBackStatus() {
        logger.info("查询扣款状态定时器执行---------------开始");
        logger.info("查询扣款状态定时器执行---------------完毕");
    }

    /**
     * 每小时执行一次，对延期项目
     * 对15天前非正常到期款项进行扣款
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void changeBackOld() {
        logger.info("对15天前非正常到期款项进行扣款定时器执行---------------开始");
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
        logger.info("当天更改逾期定时器执行---------------完毕");
    }
}
