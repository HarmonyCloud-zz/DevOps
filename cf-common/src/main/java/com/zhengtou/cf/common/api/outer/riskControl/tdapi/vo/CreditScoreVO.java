package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo;

/**
 * 信用分返回结构
 * @author 葛文镇
 */
public class CreditScoreVO{
    private Integer credit_score;
    private String decision;

    public Integer getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Integer credit_score) {
        this.credit_score = credit_score;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}