package com.zhengtou.cf.risk.service.impl;


import com.zhengtou.cf.api.third.RiskApi;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.ResultDescVO;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.RiskItem;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.enums.TdTypeEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.RiskRecordEntity;
import com.zhengtou.cf.risk.pojo.entity.rule.RuleRecordEntity;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalResultTypeEnum;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskItemVO;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskVO;
import com.zhengtou.cf.risk.pojo.vo.rule.RuleVO;
import com.zhengtou.cf.risk.service.RiskService;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.vo.person.PersonalVO;
import com.zhengtou.cf.user.service.ConsumerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskServiceImpl implements RiskService {

    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    RiskApi riskApi;
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
        //查询redis中是否有记录
        if (myRedisComponent.hasKey("risk" + consumerUserEntity.getId())) {
            return;
        }
        PersonalVO personalVO=consumerUserService.getPersonalByUserId(consumerUserEntity.getId());
        ResultDescVO resultDescVO = riskApi.risk(personalVO.getIdNo(), consumerUserEntity.getPhone(),personalVO.getName(),
                tdTypeEnum == null ? TdTypeEnum.web : tdTypeEnum);
        RiskRecordEntity riskRecordEntity = new RiskRecordEntity();
        riskRecordEntity.setConsumer(consumerUserEntity);
        riskRecordEntity.setAntifraudDecision(resultDescVO.getANTIFRAUD().getFinal_decision());
        riskRecordEntity.setAntifraudScore(resultDescVO.getANTIFRAUD().getFinal_score());
        riskRecordEntity.setCreditDecision(resultDescVO.getCREDITSCORE().getDecision());
        riskRecordEntity.setCreditScore(resultDescVO.getCREDITSCORE().getCredit_score());

        List<RiskItem> riskItems = resultDescVO.getANTIFRAUD().getRisk_items();
        List<RuleRecordEntity> ruleRecordEntities = new ArrayList<>();
        if (resultDescVO.getANTIFRAUD().getFinal_decision().equals("PASS") || resultDescVO.getANTIFRAUD().getFinal_decision().equals("WARN")) {
            if (riskItems.isEmpty()) {
                riskRecordEntity.setResult(ApprovalResultTypeEnum.通过);
            } else {
                riskRecordEntity.setResult(ApprovalResultTypeEnum.待定);
                for (RiskItem riskItem : riskItems) {
                    if (myRedisComponent.hasKey("RejectRule" + riskItem.getRule_id())) {
                        RuleVO ruleVO = (RuleVO) myRedisComponent.get("RejectRule" + riskItem.getRule_id());
                        riskRecordEntity.setResult(ApprovalResultTypeEnum.拒绝);
                        RuleRecordEntity ruleRecordEntity = new RuleRecordEntity();
                        ruleRecordEntity.setOutRuleId(ruleVO.getOutRuleId());
                        ruleRecordEntity.setDecision(riskItem.getDecision());
                        ruleRecordEntity.setScore(riskItem.getScore());
                        ruleRecordEntities.add(ruleRecordEntity);
                    }
                    if (myRedisComponent.hasKey("WarnRule" + riskItem.getRule_id())) {
                        RuleVO ruleVO = (RuleVO) myRedisComponent.get("WarnRule" + riskItem.getRule_id());
                        if (!riskRecordEntity.getResult().equals(ApprovalResultTypeEnum.拒绝)) {
                            riskRecordEntity.setResult(ApprovalResultTypeEnum.待定);
                        }
                        RuleRecordEntity ruleRecordEntity = new RuleRecordEntity();
                        ruleRecordEntity.setOutRuleId(ruleVO.getOutRuleId());
                        ruleRecordEntity.setDecision(riskItem.getDecision());
                        ruleRecordEntity.setScore(riskItem.getScore());
                        ruleRecordEntities.add(ruleRecordEntity);
                    }
                }
            }
        } else {
            riskRecordEntity.setResult(ApprovalResultTypeEnum.拒绝);
        }
        dao.save(riskRecordEntity);
        for (RuleRecordEntity ruleRecordEntity : ruleRecordEntities) {
            ruleRecordEntity.setRiskRecordEntity(riskRecordEntity);
            dao.save(ruleRecordEntity);
        }
    }

    @Override
    public List<RiskItemVO> getRuleRecords(RiskRecordEntity riskRecordEntity) {
        return dao.find("select new com.zhengtou.cf.risk.pojo.vo.risk.RiskItemVO(re.outRuleId,re.decision,re.score,re.approvalCompany,re.ruleType) " +
                "from RuleRecordEntity re where re.isDeleted=false and re.riskRecordEntity=?0", new Object[]{riskRecordEntity});
    }
}
