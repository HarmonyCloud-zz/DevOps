package com.harmony.devops.user.risk.pojo.entity.rule;


import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalCompanyEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalRuleStatusEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;
import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "t_rule")
public class RuleEntity extends BaseEntity{

    /**
     * 规则id
     */
    private String outRuleId;

    /**
     * 规则名称
     */
    private String ruleName;

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

    /**
     * 规则状态: 启用，关闭；
     */
    @Enumerated(EnumType.STRING)
    private ApprovalRuleStatusEnum ruleStatus;


    public String getOutRuleId() {
        return outRuleId;
    }

    public void setOutRuleId(String outRuleId) {
        this.outRuleId = outRuleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    public ApprovalRuleStatusEnum getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(ApprovalRuleStatusEnum ruleStatus) {
        this.ruleStatus = ruleStatus;
    }
}
