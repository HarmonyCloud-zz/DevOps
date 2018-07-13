package com.zhengtou.cf.trade.pojo.entity.bill;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;

import javax.persistence.*;

/**
 * 期供
 */
@Entity
@Table(name = "t_bill_term")
public class TermEntity extends BaseEntity {
    @ManyToOne
    private BillEntity bill;
    private String termNo;//期数
    private long pmtDueDate;//到期还款日
    private long loanRemainPrin;//剩余本金
    private long schdAmt;//应还金额
    private long paidAmt;//已还金额
    private Long overDueAmt;//逾期金额
    private Long wavAmt;//减免金额
    private String ageCd;//账龄
    private Long paidOutDate;//结清日期
    //失败原因
    private String errorDesc;
    //失败错误码
    private String errorCode;
    /**
     * U|待还款
     * B|已延期
     * I|待还款(免息期)
     * O|已逾期
     * C|已代偿
     * W|已核销
     * S|已还款
     */
    @Enumerated(EnumType.STRING)
    private TermStatusEnum termStatus;//期供状态

    /**
     * 支付交易流水号
     */
    private String paymentTransNo;

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public long getPmtDueDate() {
        return pmtDueDate;
    }

    public void setPmtDueDate(long pmtDueDate) {
        this.pmtDueDate = pmtDueDate;
    }

    public long getLoanRemainPrin() {
        return loanRemainPrin;
    }

    public void setLoanRemainPrin(long loanRemainPrin) {
        this.loanRemainPrin = loanRemainPrin;
    }

    public long getSchdAmt() {
        return schdAmt;
    }

    public void setSchdAmt(long schdAmt) {
        this.schdAmt = schdAmt;
    }

    public long getPaidAmt() {
        return paidAmt;
    }

    public void setPaidAmt(long paidAmt) {
        this.paidAmt = paidAmt;
    }

    public Long getOverDueAmt() {
        return overDueAmt;
    }

    public void setOverDueAmt(Long overDueAmt) {
        this.overDueAmt = overDueAmt;
    }

    public Long getWavAmt() {
        return wavAmt;
    }

    public void setWavAmt(Long wavAmt) {
        this.wavAmt = wavAmt;
    }

    public String getAgeCd() {
        return ageCd;
    }

    public void setAgeCd(String ageCd) {
        this.ageCd = ageCd;
    }

    public Long getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(Long paidOutDate) {
        this.paidOutDate = paidOutDate;
    }

    public TermStatusEnum getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(TermStatusEnum termStatus) {
        this.termStatus = termStatus;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPaymentTransNo() {
        return paymentTransNo;
    }

    public void setPaymentTransNo(String paymentTransNo) {
        this.paymentTransNo = paymentTransNo;
    }
}
