package com.harmony.devops.user.risk.pojo.entity.rule;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalCompanyEnum;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalResultTypeEnum;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 规则命中记录
 */
@Entity
@Table(name = "t_risk_record")
public class RiskRecordEntity extends BaseEntity {
    @ManyToOne
    private ConsumerUserEntity consumer;
    //信用分值
    private Integer creditScore;
    //信用描述
    private String creditDecision;
    //欺诈分值
    private Integer antifraudScore;
    //欺诈描述
    private String antifraudDecision;
    //风控结果
    @Enumerated(EnumType.STRING)
    private ApprovalResultTypeEnum result;
    /**
     * 风控公司
     */
    @Enumerated(EnumType.STRING)
    private ApprovalCompanyEnum approvalCompany=ApprovalCompanyEnum.同盾;

    public ConsumerUserEntity getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerUserEntity consumer) {
        this.consumer = consumer;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getCreditDecision() {
        return creditDecision;
    }

    public void setCreditDecision(String creditDecision) {
        this.creditDecision = creditDecision;
    }

    public Integer getAntifraudScore() {
        return antifraudScore;
    }

    public void setAntifraudScore(Integer antifraudScore) {
        this.antifraudScore = antifraudScore;
    }

    public String getAntifraudDecision() {
        return antifraudDecision;
    }

    public void setAntifraudDecision(String antifraudDecision) {
        this.antifraudDecision = antifraudDecision;
    }

    public ApprovalResultTypeEnum getResult() {
        return result;
    }

    public void setResult(ApprovalResultTypeEnum result) {
        this.result = result;
    }

    public ApprovalCompanyEnum getApprovalCompany() {
        return approvalCompany;
    }

    public void setApprovalCompany(ApprovalCompanyEnum approvalCompany) {
        this.approvalCompany = approvalCompany;
    }
}
