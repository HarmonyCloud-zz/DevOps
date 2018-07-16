package com.harmony.devops.user.thirdApi.handler.task.trade;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 借据定时任务
 */
@Service
public class BillTask {
    private static final Logger logger = LoggerFactory.getLogger(BillTask.class);

    @Autowired
    BaseDao dao;

    /**
     * 还款成功定时器
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void changeBillSuc() {
        logger.info("凌晨一点更改借据得汇总信息:还款成功定时器执行---------------开始");
        logger.info("凌晨一点更改借据得汇总信息:还款成功定时器执行---------------结束");
    }

    /**
     * 还款失败定时器。
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void changeBillFai() {
        logger.info("凌晨三点更改借据得汇总信息:还款失败定时器执行---------------开始");
        logger.info("凌晨三点更改借据得汇总信息:还款失败定时器执行---------------结束");
    }
}
