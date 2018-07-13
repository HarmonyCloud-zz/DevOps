package com.zhengtou.cf.trade.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;

import java.util.Date;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 合同vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractVO extends PeakBaseVo {
    /**
     * 确认合同信息
     */
    public ContractVO(String tradeFlowNo, String outTradeFlowNo, String contrNo, long contrPrin, long applyDate, Date signDate, String purpose,
                      String custSource) {
        this.tradeFlowNo = tradeFlowNo;
        this.outTradeFlowNo = outTradeFlowNo;
        this.contrNo = contrNo;
        this.contrPrin = MoneyUtil.moneyToString(contrPrin);
        this.applyDate = TimeUtil.dateToString(applyDate);
        this.signDate = TimeUtil.dateToString(signDate);
        this.purpose = StringUtils.isBlank(purpose)?"":purpose;
        this.custSource = custSource;
    }

    /**
     * 签订合同后查询合同信息
     */
    public ContractVO(String tradeFlowNo, String outTradeFlowNo, String contrNo, Long contrPrin, long applyDate, long signDate, String purpose,
                      String custSource, String contrPath) {
        this.tradeFlowNo = tradeFlowNo;
        this.outTradeFlowNo = outTradeFlowNo;
        this.contrNo = contrNo;
        this.contrPrin = MoneyUtil.moneyToString(contrPrin);
        this.applyDate = TimeUtil.timeToString(applyDate);
        this.signDate = TimeUtil.timeToString(signDate);
        this.purpose = StringUtils.isBlank(purpose)?"":purpose;
        this.custSource = custSource;
        this.contrPath = contrPath;
    }

    /**
     * 合同id
     */
    private Long contractId;
    /**
     * 贷款订单编号
     */
    private String tradeFlowNo;
    /**
     * 外部订单编号
     */
    private String outTradeFlowNo;
    /**
     * 合同编号
     */
    private String contrNo;
    /**
     * 借款本金
     */
    private String contrPrin;
    /**
     * 申请日期
     */
    private String applyDate;
    /**
     * 签约日期
     */
    private String signDate;
    /**
     * 贷款用途
     */
    private String purpose;
    /**
     *合作渠道编号
     */
    private String custSource;
    /**
     * 合同路径
     */
    private String contrPath;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getOutTradeFlowNo() {
        return outTradeFlowNo;
    }

    public void setOutTradeFlowNo(String outTradeFlowNo) {
        this.outTradeFlowNo = outTradeFlowNo;
    }

    public String getContrNo() {
        return contrNo;
    }

    public void setContrNo(String contrNo) {
        this.contrNo = contrNo;
    }

    public String getContrPrin() {
        return contrPrin;
    }

    public void setContrPrin(String contrPrin) {
        this.contrPrin = contrPrin;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
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
}
