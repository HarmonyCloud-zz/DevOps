package com.zhengtou.cf.risk.service;


import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.enums.TdTypeEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.RiskRecordEntity;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskItemVO;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskVO;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;

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
