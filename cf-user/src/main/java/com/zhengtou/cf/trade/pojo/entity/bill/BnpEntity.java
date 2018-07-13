package com.zhengtou.cf.trade.pojo.entity.bill;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.BnpTypeEnum;

import javax.persistence.*;

/**
 * 余额成分
 */
@Entity
@Table(name = "t_bill_bnp")
public class BnpEntity extends BaseEntity {
    @ManyToOne
    public TermEntity term;
    /**
     * "PRINCIPAL|本金
     * INTEREST|利息
     * INTEREST_DELAY|逾期利息
     * PENALTY|罚息
     * PENALTY_PURPOSE|挪用罚息
     * COMPOUND|复利
     * MULCT_FEE|罚金
     * LATE_FEE|滞纳金
     * TXN_FEE|手续费
     * 注：竖线前部分"
     */
    @Enumerated(EnumType.STRING)
    private BnpTypeEnum bnpType;//余额成分代码
    private Long overDueAmt;//当前余额
    private Long schdAmt;//应还金额
    private Long paidAmt;//已还金额
    private Long wavAmt;//减免金额

    public TermEntity getTerm() {
        return term;
    }

    public void setTerm(TermEntity term) {
        this.term = term;
    }

    public double getOverDueAmt() {
        return overDueAmt;
    }

    public BnpTypeEnum getBnpType() {
        return bnpType;
    }

    public void setBnpType(BnpTypeEnum bnpType) {
        this.bnpType = bnpType;
    }

    public void setOverDueAmt(Long overDueAmt) {
        this.overDueAmt = overDueAmt;
    }

    public Long getSchdAmt() {
        return schdAmt;
    }

    public void setSchdAmt(Long schdAmt) {
        this.schdAmt = schdAmt;
    }

    public Long getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(Long paidAmt) {
        this.paidAmt = paidAmt;
    }

    public Long getWavAmt() {
        return wavAmt;
    }

    public void setWavAmt(Long wavAmt) {
        this.wavAmt = wavAmt;
    }
}
