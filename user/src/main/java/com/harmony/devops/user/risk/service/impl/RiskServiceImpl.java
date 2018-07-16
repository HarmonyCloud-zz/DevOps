package com.harmony.devops.user.risk.service.impl;


import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.harmony.devops.user.enums.TdTypeEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.RiskRecordEntity;
import com.harmony.devops.user.risk.pojo.entity.rule.RuleRecordEntity;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalResultTypeEnum;
import com.harmony.devops.user.risk.pojo.vo.risk.RiskItemVO;
import com.harmony.devops.user.risk.pojo.vo.risk.RiskVO;
import com.harmony.devops.user.risk.pojo.vo.rule.RuleVO;
import com.harmony.devops.user.risk.service.RiskService;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.vo.person.PersonalVO;
import com.harmony.devops.user.user.service.ConsumerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskServiceImpl implements RiskService {

    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    ConsumerUserService consumerUserService;

    @Override
    public RiskRecordEntity getNewRiskRecordEntityByConsumerUser(ConsumerUserEntity consumerUserEntity) {
        return dao.get(" from RiskRecordEntity r where r.isDeleted=false and r.consumer=?0 order by r.createTime", new Object[]{consumerUserEntity});
    }

    @Override
    public RiskVO getRiskResultByConsumerUser(ConsumerUserEntity consumerUserEntity) throws BaseException {
        //查询redis中是否有记录
        if (myRedisComponent.hasKey("risk" + consumerUserEntity.getId())) {
            return (RiskVO) myRedisComponent.get("risk" + consumerUserEntity.getId());
        } else {
            RiskRecordEntity riskRecordEntity = dao.get("from RiskRecordEntity r where r.consumer=?0 order by r.createTime desc ", new
                    Object[]{consumerUserEntity});
            if (riskRecordEntity == null) {
                throw new BaseException(RtnResultEnum.E070004);
            } else {
                RiskVO riskVO = new RiskVO(riskRecordEntity.getCreditScore(), getRuleRecords(riskRecordEntity), riskRecordEntity.getResult());
                myRedisComponent.setDay("risk" + consumerUserEntity.getId(), riskVO, 30l);
                return riskVO;
            }
        }
    }

    @Override
    public void saveRuleRecord(ConsumerUserEntity consumerUserEntity, TdTypeEnum tdTypeEnum) throws BaseException {
    }

    @Override
    public List<RiskItemVO> getRuleRecords(RiskRecordEntity riskRecordEntity) {
        return dao.find("select new com.harmony.devops.user.risk.pojo.vo.risk.RiskItemVO(re.outRuleId,re.decision,re.score,re.approvalCompany,re.ruleType) " +
                "from RuleRecordEntity re where re.isDeleted=false and re.riskRecordEntity=?0", new Object[]{riskRecordEntity});
    }
}
