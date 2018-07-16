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
 * 查询申请单状态
 */
@Service
public class QueryApplyStatusTask {
    private static final Logger logger = LoggerFactory.getLogger(QueryApplyStatusTask.class);
    @Autowired
    BaseDao dao;

    /**
     * 45秒通知一次
     * 查询申请单状态
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void queryApplyStatusTask() {
        logger.info("---------查询申请单状态开始-----------");
        logger.info("---------查询申请单状态结束-----------");
    }
}
