package com.zhengtou.cf.risk.pojo.entity;

import com.zhengtou.cf.risk.pojo.enums.TDRiskResultEnum;

import javax.persistence.Entity;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
public class TdRiskEntity extends RiskEntity {
    public TdRiskEntity(String name, String phone, String idNo, Long lastVerificationTime, String reason_desc, String
            reason_code,String tdRiskResultEnum,Integer finalScore,Integer creditScore,String decision) {
        super(name,phone,idNo,lastVerificationTime);
        this.reason_desc = reason_desc;
        this.reason_code = reason_code;
        this.tdRiskResultEnum=TDRiskResultEnum.getEnum(tdRiskResultEnum);
        this.finalScore=finalScore;
        this.creditScore=creditScore;
        this.decision=decision;
    }

    /**
     * 贷前欺诈分
     */
    private Integer finalScore;
    /**
     * 贷前建议情况
     */
    private TDRiskResultEnum tdRiskResultEnum;
    /**
     * 错误详情描述
     */
    private String reason_desc;
    /**
     * 调用失败时的错误码
     */
    private String reason_code;
    /**
     * 信用分
     */
    private Integer creditScore;
    /**
     * 描述
     */
    private String decision;

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public TDRiskResultEnum getTdRiskResultEnum() {
        return tdRiskResultEnum;
    }

    public void setTdRiskResultEnum(TDRiskResultEnum tdRiskResultEnum) {
        this.tdRiskResultEnum = tdRiskResultEnum;
    }

    public String getReason_desc() {
        return reason_desc;
    }

    public void setReason_desc(String reason_desc) {
        this.reason_desc = reason_desc;
    }

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
