package com.zhengtou.cf.trade.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 收款记录vo
 * //TODO 需要支付核心模块 暂时由合同和期供记录提供支付订单记录，扣款也由两边记录进行打款或扣款
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-收款记录vo")
public class ReceiptVO extends PeakBaseVo {
    /**
     * 收付款列表view
     */
    public ReceiptVO(long termId, String tradeFlowNo, String termNo, long pmtDueDate, long schdAmt, Long paidOutDate, TermStatusEnum termStatus,
                     Long loanPrin,String errorCode,String errorDesc) {
        this.termId = termId;
        this.tradeFlowNo = tradeFlowNo;
        this.termNo = termNo;
        this.pmtDueDate = TimeUtil.timeToString(pmtDueDate);
        this.schdAmt = MoneyUtil.moneyToString(schdAmt);
        this.paidOutDate = paidOutDate == null ? "" : TimeUtil.timeToString(paidOutDate);
        this.termStatus = termStatus;
        this.loanPrin= MoneyUtil.moneyToString(loanPrin);
        this.interest=MoneyUtil.moneyToString(schdAmt-loanPrin);
        this.errorCode= StringUtils.isNotBlank(errorCode)?errorCode:"";
        this.errorDesc=StringUtils.isNotBlank(errorDesc)?errorDesc:"";
    }

    /**
     * 借据列表view，根据贷款查询
     */
    public ReceiptVO(long termId, String tradeFlowNo,String termNo, long pmtDueDate, long schdAmt, Long paidOutDate, TermStatusEnum termStatus) {
        this.termId = termId;
        this.tradeFlowNo = tradeFlowNo;
        this.termNo = termNo;
        this.pmtDueDate = TimeUtil.timeToString(pmtDueDate);
        this.schdAmt = MoneyUtil.moneyToString(schdAmt);
        this.paidOutDate = paidOutDate == null ? "" : TimeUtil.timeToString(paidOutDate);
        this.termStatus = termStatus;
    }

    @ApiModelProperty(value="期供id",name="termId")
    private Long termId;
    @ApiModelProperty(value="贷款流水号",name="tradeFlowNo")
    private String tradeFlowNo;
    @ApiModelProperty(value="贷款当前期数",name="termNo")
    private String termNo;//期数
    @ApiModelProperty(value="到期还款日",name="pmtDueDate")
    private String pmtDueDate;//到期还款日
    @ApiModelProperty(value="应还金额",name="schdAmt")
    private String schdAmt;//应还金额
    @ApiModelProperty(value="结清日期",name="paidOutDate")
    private String paidOutDate;//结清日期
    @ApiModelProperty(value="期供状态：" +
            "   *待还款(\"U\"),\n" +
            "    *还款中(\"I\"),\n" +
            "    *已逾期(\"O\"),\n" +
            "    *已还款(\"S\"),\n" +
            "    *延期扣款(\"YK\"),\n" +
            "    *扣款失败(\"F\");",name="termStatus")
    private TermStatusEnum termStatus;//期供状态
    @ApiModelProperty(value="当期本金",name="loanPrin")
    private String loanPrin;//本金
    @ApiModelProperty(value="当期利息",name="interest")
    private String interest;//利息
    @ApiModelProperty(value="扣款失败原因",name="errorDesc")
    private String errorDesc;
    @ApiModelProperty(value="扣款失败错误码",name="errorCode")
    private String errorCode;


    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getPmtDueDate() {
        return pmtDueDate;
    }

    public void setPmtDueDate(String pmtDueDate) {
        this.pmtDueDate = pmtDueDate;
    }

    public String getSchdAmt() {
        return schdAmt;
    }

    public void setSchdAmt(String schdAmt) {
        this.schdAmt = schdAmt;
    }

    public String getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(String paidOutDate) {
        this.paidOutDate = paidOutDate;
    }

    public TermStatusEnum getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(TermStatusEnum termStatus) {
        this.termStatus = termStatus;
    }

    public String getLoanPrin() {
        return loanPrin;
    }

    public void setLoanPrin(String loanPrin) {
        this.loanPrin = loanPrin;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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
}
