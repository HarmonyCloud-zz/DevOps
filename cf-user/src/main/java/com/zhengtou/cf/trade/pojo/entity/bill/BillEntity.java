package com.zhengtou.cf.trade.pojo.entity.bill;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author 葛文镇
 * 借款
 */
@Entity
@Table(name = "t_bill")
public class BillEntity extends BaseEntity {
    //借据编号
    private String billNo;
    //子产品代码
    private String productSubCd;
    //贷款期数
    private String loanTerm;
    //贷款本金
    private long loanPrin;
    //未到期本金
    private long xfrOutPrin;
    //还款方式
    private RepayMethodEnum repayMethod;
    //提取金额
    private long withdrawAmt;
    //提取日期
    private String withdrawDate;
    //当前期数
    private String termNo;
    //贷款余额
    private long currBal;
    //未还款金额
    private long unpaidAmt;
    //总利息
    private long totalIntersetAmt;
    //服务费
    private long serviceFee;
    //溢缴款余额
    private Long depositBal;
    @Enumerated(EnumType.STRING)
    private BillStatusEnum billStatus;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getProductSubCd() {
        return productSubCd;
    }

    public void setProductSubCd(String productSubCd) {
        this.productSubCd = productSubCd;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    public long getLoanPrin() {
        return loanPrin;
    }

    public void setLoanPrin(long loanPrin) {
        this.loanPrin = loanPrin;
    }

    public long getXfrOutPrin() {
        return xfrOutPrin;
    }

    public void setXfrOutPrin(long xfrOutPrin) {
        this.xfrOutPrin = xfrOutPrin;
    }

    public RepayMethodEnum getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(RepayMethodEnum repayMethod) {
        this.repayMethod = repayMethod;
    }

    public long getWithdrawAmt() {
        return withdrawAmt;
    }

    public void setWithdrawAmt(long withdrawAmt) {
        this.withdrawAmt = withdrawAmt;
    }

    public String getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(String withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public long getCurrBal() {
        return currBal;
    }

    public void setCurrBal(long currBal) {
        this.currBal = currBal;
    }

    public long getUnpaidAmt() {
        return unpaidAmt;
    }

    public void setUnpaidAmt(long unpaidAmt) {
        this.unpaidAmt = unpaidAmt;
    }

    public long getDepositBal() {
        return depositBal;
    }

    public void setDepositBal(long depositBal) {
        this.depositBal = depositBal;
    }

    public long getTotalIntersetAmt() {
        return totalIntersetAmt;
    }

    public void setTotalIntersetAmt(long totalIntersetAmt) {
        this.totalIntersetAmt = totalIntersetAmt;
    }

    public long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BillStatusEnum getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatusEnum billStatus) {
        this.billStatus = billStatus;
    }
}
