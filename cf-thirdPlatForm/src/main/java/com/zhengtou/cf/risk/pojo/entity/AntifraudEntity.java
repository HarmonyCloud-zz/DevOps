package com.zhengtou.cf.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_risk_antifraud")
public class AntifraudEntity extends BaseEntity{
    public AntifraudEntity(String idNo, String ruleId, Integer score, String decision, String riskName) {
        this.idNo = idNo;
        this.ruleId = ruleId;
        this.score = score;
        this.decision = decision;
        this.riskName = riskName;
    }

    /**
     * 验证标识
     */
    private String idNo;
    /**
     * 规则id
     */
    private String ruleId;
    /**
     * 规则分数
     */
    private Integer score;
    /**
     * 规则描述
     */
    private String decision;
    /**
     * 规则名称
     */
    private String riskName;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }
}
