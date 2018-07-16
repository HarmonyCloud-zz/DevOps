package com.harmony.devops.user.risk.pojo.vo.risk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalCompanyEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 风控报警项返回
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskItemVO extends PeakBaseVo{
    public RiskItemVO(String outRuleId, String ruleName, Integer score,ApprovalCompanyEnum approvalCompany,ApprovalRuleTypeEnum ruleType) {
        this.outRuleId = outRuleId;
        this.ruleName = ruleName;
        this.score = score;
        this.approvalCompany=approvalCompany;
        this.ruleType=ruleType;
    }

    /**
     * 规则id
     */
    private String outRuleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 风控公司
     */
    private ApprovalCompanyEnum approvalCompany;

    /**
     * 规则类型: 拒绝项，关注项;
     */
    private ApprovalRuleTypeEnum ruleType;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
