package com.zhengtou.cf.risk.pojo.vo.rule;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalCompanyEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalRuleStatusEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 规则vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleVO extends PeakBaseVo {
    public RuleVO(String outRuleId, String ruleName, ApprovalCompanyEnum approvalCompany, ApprovalRuleTypeEnum ruleType, ApprovalRuleStatusEnum
            ruleStatus, Long ruleId) {
        this.outRuleId = outRuleId;
        this.ruleName = ruleName;
        this.approvalCompany = approvalCompany;
        this.ruleType = ruleType;
        this.ruleStatus = ruleStatus;
        this.ruleId = ruleId;
    }

    public RuleVO() {
    }

    //redis，风控标志
    public RuleVO(String outRuleId, ApprovalRuleTypeEnum ruleType){
        this.outRuleId=outRuleId;
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
     * 风控公司
     */
    private ApprovalCompanyEnum approvalCompany;

    /**
     * 规则类型: 拒绝项，关注项;
     */
    private ApprovalRuleTypeEnum ruleType;

    /**
     * 规则状态: 启用，关闭；
     */
    private ApprovalRuleStatusEnum ruleStatus;
    //id
    private Long ruleId;

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

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}
