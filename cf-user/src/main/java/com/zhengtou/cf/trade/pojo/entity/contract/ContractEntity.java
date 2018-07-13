package com.zhengtou.cf.trade.pojo.entity.contract;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.enums.LoanStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;

import javax.persistence.*;

/**
 * 合同相关信息
 */
@Entity
@Table(name = "t_contract")
public class ContractEntity extends BaseEntity {
    @OneToOne
    public TradeEntity trade;

    private String contrNo;//合同编号
    private Long contrLimit;//合同级额度
    private Long contrPrin;//合同本金
    private String applyNo;//申请单编号
    private long applyDate;//申请日期
    private long signDate;//签约日期
    private String purpose;//贷款用途
    private String custSource;//客源合作渠道号
    private String contrPath;//合同路径

    /**
     * 合同状态信息
     */
    @Enumerated(EnumType.STRING)
    private EnumContractStatus contractStatus;
    /**
     * 放款状态信息
     */
    @Enumerated(EnumType.STRING)
    private LoanStatusEnum loanStatus;
    public enum EnumContractStatus {
        有效, 无效;
    }
    @OneToOne
    private BillEntity bill;

    public TradeEntity getTrade() {
        return trade;
    }

    public void setTrade(TradeEntity trade) {
        this.trade = trade;
    }

    public String getContrNo() {
        return contrNo;
    }

    public void setContrNo(String contrNo) {
        this.contrNo = contrNo;
    }

    public Long getContrLimit() {
        return contrLimit;
    }

    public void setContrLimit(Long contrLimit) {
        this.contrLimit = contrLimit;
    }

    public Long getContrPrin() {
        return contrPrin;
    }

    public void setContrPrin(Long contrPrin) {
        this.contrPrin = contrPrin;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public long getSignDate() {
        return signDate;
    }

    public void setSignDate(long signDate) {
        this.signDate = signDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getContrPath() {
        return contrPath;
    }

    public void setContrPath(String contrPath) {
        this.contrPath = contrPath;
    }

    public EnumContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(EnumContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public LoanStatusEnum getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatusEnum loanStatus) {
        this.loanStatus = loanStatus;
    }

    public BillEntity getBill() {
        return bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }
}
