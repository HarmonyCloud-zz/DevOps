package com.harmony.devops.user.thirdApi.handler.task;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.harmony.devops.user.enums.TdTypeEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalResultTypeEnum;
import com.harmony.devops.user.risk.pojo.vo.risk.RiskVO;
import com.harmony.devops.user.risk.service.RiskService;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
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
public class UserTask {
    private static final Logger logger = LoggerFactory.getLogger(UserTask.class);
    @Autowired
    BaseDao dao;
    @Autowired
    RiskService riskService;

    /**
     * 45秒一次
     * 首次预授信
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void firstCredit() {
        logger.info("---------首次预授信开始-----------");
        List<ConsumerUserEntity> consumerUserEntities = dao.find("from ConsumerUserEntity t where t.isDeleted=false and t.isUserInfoComplete=?0 and" +
                " t.creditAmt=null", new
                Object[]{UserInfoCompleteEnum.已完善});
        if (consumerUserEntities.isEmpty()) {
            logger.info("---------首次预授信无结果结束-----------");
            return;
        }
        for (ConsumerUserEntity consumerUserEntity : consumerUserEntities) {
            RiskVO riskVO = null;
            try {
                riskService.saveRuleRecord(consumerUserEntity, TdTypeEnum.web);
                riskVO = riskService.getRiskResultByConsumerUser(consumerUserEntity);
            } catch (BaseException e) {
                logger.error("首次预授信异常：" + e.getErrorCode() + "----------------" + e.getErrorMsg());
            }

            if (riskVO.getResult().equals(ApprovalResultTypeEnum.拒绝)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(0l);
                consumerUserEntity.setCanUseAmt(0l);
            }
            if (riskVO.getResult().equals(ApprovalResultTypeEnum.待定)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(10000l * (100 - riskVO.getCredit_score()));
                consumerUserEntity.setCanUseAmt(10000l * (100 - riskVO.getCredit_score()));
            }
            if (riskVO.getResult().equals(ApprovalResultTypeEnum.通过)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(30000l * (100 - riskVO.getCredit_score()));
                consumerUserEntity.setCanUseAmt(30000l * (100 - riskVO.getCredit_score()));
            }
            consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
            dao.modify(consumerUserEntity);
        }
        logger.info("---------首次预授信结束-----------");
    }

    /**
     * 3个月一次授信
     * 每天凌晨0点执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void credit() {
        logger.info("---------3个月一次授信开始-----------");
        List<ConsumerUserEntity> consumerUserEntities = dao.find("from ConsumerUserEntity t where t.isDeleted=false and t.isUserInfoComplete=?0 and" +
                " t.creditAmt!=null and t.amtCreateTime>=?1", new Object[]{UserInfoCompleteEnum.已完善,System.currentTimeMillis() - 3 * 30 * 24 * 60
                * 60 * 1000});
        if (consumerUserEntities.isEmpty()) {
            logger.info("---------3个月一次授信无结果结束-----------");
            return;
        }
        for (ConsumerUserEntity consumerUserEntity : consumerUserEntities) {
            RiskVO riskVO = null;
            try {
                riskService.saveRuleRecord(consumerUserEntity, TdTypeEnum.web);
                riskVO = riskService.getRiskResultByConsumerUser(consumerUserEntity);
            } catch (BaseException e) {
                logger.error("3个月一次授信异常：" + e.getErrorCode() + "----------------" + e.getErrorMsg());
            }

            if (riskVO.getResult().equals(ApprovalResultTypeEnum.拒绝)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(0l);
                consumerUserEntity.setCanUseAmt(0l);
            }
            if (riskVO.getResult().equals(ApprovalResultTypeEnum.待定)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(10000l * (100 - riskVO.getCredit_score()));
                consumerUserEntity.setCanUseAmt(10000l * (100 - riskVO.getCredit_score()));
            }
            if (riskVO.getResult().equals(ApprovalResultTypeEnum.通过)) {
                consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
                consumerUserEntity.setCreditAmt(30000l * (100 - riskVO.getCredit_score()));
                consumerUserEntity.setCanUseAmt(30000l * (100 - riskVO.getCredit_score()));
            }
            consumerUserEntity.setAmtCreateTime(System.currentTimeMillis());
            dao.modify(consumerUserEntity);
        }
        logger.info("---------3个月一次授信结束-----------");
    }
}
