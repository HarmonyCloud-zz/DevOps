package com.harmony.devops.user.risk.service;


import com.zhengtou.cf.common.exception.BaseException;
import com.harmony.devops.user.enums.TdTypeEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.RiskRecordEntity;
import com.harmony.devops.user.risk.pojo.vo.risk.RiskItemVO;
import com.harmony.devops.user.risk.pojo.vo.risk.RiskVO;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import java.util.List;

public interface RiskService {
    /**
     * 根据用户拿到风控记录
     */
    RiskRecordEntity getNewRiskRecordEntityByConsumerUser(ConsumerUserEntity consumerUserEntity);
    /**
     * 风控服务
     */
    RiskVO getRiskResultByConsumerUser(ConsumerUserEntity consumerUserEntity)throws BaseException;

    /**
     * 保存风控结果
     */
    void saveRuleRecord(ConsumerUserEntity consumerUserEntity, TdTypeEnum tdTypeEnum) throws BaseException;

    /**
     * 风控记录查询
     */
    List<RiskItemVO> getRuleRecords(RiskRecordEntity riskRecordEntity);
}
