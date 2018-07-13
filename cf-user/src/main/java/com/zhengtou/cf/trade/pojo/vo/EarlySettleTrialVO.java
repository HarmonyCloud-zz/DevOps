package com.zhengtou.cf.trade.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 提前结清试算vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-提前结清试算vo")
public class EarlySettleTrialVO extends PeakBaseVo {
    @ApiModelProperty(value="贷款合同编号",name="contrNo")
    private String contrNo;//合同编号
    @ApiModelProperty(value="贷款流水号",name="tradeFlowNo")
    private String tradeFlowNo;
    @ApiModelProperty(value="总扣款金额",name="totalCutAmt")
    private String totalCutAmt;//总扣款金额
    @ApiModelProperty(value="借据编号",name="billNo")
    private String billNo;//借据编号
    @ApiModelProperty(value="提前结清手续费",name="txnFeeAmt")
    private String txnFeeAmt;//提前结清手续费
    @ApiModelProperty(value="结清本金",name="principal")
    private String principal;//结清本金
    @ApiModelProperty(value="结清利息",name="settleInterest")
    private String settleInterest;//结清利息
    @ApiModelProperty(value="剩余总利息",name="allInterest")
    private String allInterest;//剩余总利息

    public String getContrNo() {
        return contrNo;
    }

    public void setContrNo(String contrNo) {
        this.contrNo = contrNo;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getTotalCutAmt() {
        return totalCutAmt;
    }

    public void setTotalCutAmt(String totalCutAmt) {
        this.totalCutAmt = totalCutAmt;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTxnFeeAmt() {
        return txnFeeAmt;
    }

    public void setTxnFeeAmt(String txnFeeAmt) {
        this.txnFeeAmt = txnFeeAmt;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSettleInterest() {
        return settleInterest;
    }

    public void setSettleInterest(String settleInterest) {
        this.settleInterest = settleInterest;
    }

    public String getAllInterest() {
        return allInterest;
    }

    public void setAllInterest(String allInterest) {
        this.allInterest = allInterest;
    }
}
