package com.zhengtou.cf.risk.pojo.entity.rule;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalCompanyEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;

import javax.persistence.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 规则命中记录
 */
@Entity
@Table(name = "t_rule_record")
public class RuleRecordEntity extends BaseEntity {
    private String outRuleId;
    //分值
    private Integer score;
    //描述
    private String decision;
    @ManyToOne
    private RiskRecordEntity riskRecordEntity;
    /**
     * 风控公司
     */
    @Enumerated(EnumType.STRING)
    private ApprovalCompanyEnum approvalCompany;

    /**
     * 规则类型: 拒绝项，关注项;
     */
    @Enumerated(EnumType.STRING)
    private ApprovalRuleTypeEnum ruleType;

    public String getOutRuleId() {
        return outRuleId;
    }

    public void setOutRuleId(String outRuleId) {
        this.outRuleId = outRuleId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public RiskRecordEntity getRiskRecordEntity() {
        return riskRecordEntity;
    }

    public void setRiskRecordEntity(RiskRecordEntity riskRecordEntity) {
        this.riskRecordEntity = riskRecordEntity;
    }

    public ApprovalCompanyEnum getApprovalCompany() {
        return approvalCompany;
    }

    public void setApprovalCompany(ApprovalCompanyEnum approvalCompany) {
        this.approvalCompany = approvalCompany;
    }

    public ApprovalRuleTypeEnum getRuleType() {
        return ruleType;
    }

    public void setRuleType(ApprovalRuleTypeEnum ruleType) {
        this.ruleType = ruleType;
    }
}
