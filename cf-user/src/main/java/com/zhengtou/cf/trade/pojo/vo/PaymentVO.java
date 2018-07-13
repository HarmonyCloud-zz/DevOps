package com.zhengtou.cf.trade.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.LoanStatusEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 付款记录vo
 * //TODO 需要支付核心模块 暂时由合同和期供记录提供支付订单记录，扣款也由两边记录进行打款或扣款
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentVO extends PeakBaseVo {
    public PaymentVO(long contractId,long createTime,long orgId, String orgName, Long contrPrin, String purpose, LoanStatusEnum loanStatus) {
        this.createTime= TimeUtil.timeToString(createTime);
        this.contractId=contractId;
        this.orgId = orgId;
        this.orgName = orgName;
        this.contrPrin = MoneyUtil.moneyToString(contrPrin);
        this.purpose = purpose;
        this.loanStatus = loanStatus;
    }
    /**
     * 创建日期
     */
    private String createTime;
    /**
     * 合同id
     */
    private Long contractId;
    /**
     * 机构
     */
    private Long orgId;
    private String orgName;
    /**
     * 打款金额
     */
    private String contrPrin;//合同本金
    private String purpose;//贷款用途
    /**
     * 打款状态
     */
    private LoanStatusEnum loanStatus;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getContrPrin() {
        return contrPrin;
    }

    public void setContrPrin(String contrPrin) {
        this.contrPrin = contrPrin;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LoanStatusEnum getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatusEnum loanStatus) {
        this.loanStatus = loanStatus;
    }
}
