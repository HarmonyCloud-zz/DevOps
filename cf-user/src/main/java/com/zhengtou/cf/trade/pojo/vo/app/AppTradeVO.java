package com.zhengtou.cf.trade.pojo.vo.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.app.enums.AppTradetatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 借款订单记录
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-手机端贷款列表信息vo")
public class AppTradeVO extends PeakBaseVo {
    /**
     * 未生成借据
     */
    public AppTradeVO(Long tradeId, Long createTime, Integer cycleCnt, ZtProductEnum ztProductEnum, String productCd,String productSubCd, RepayMethodEnum repayMethod,
                      String tradeFlowNo, String contractNo, Long requestAmount, Long approvalAmount, TradeStageEnum tradeStageEnum,
                      TradeStatusEnum tradeStatusEnum,BankCodeEnum bankName,String cardNo) {
        this.tradeId = tradeId;
        this.createTime = TimeUtil.timeToString(createTime);
        this.cycleCnt = cycleCnt;
        this.ztProductEnum = ztProductEnum;
        this.productCd = productCd;
        this.productSubCd=productSubCd;
        this.repayMethod = repayMethod.name();
        this.tradeFlowNo = tradeFlowNo;
        this.contractNo = contractNo;
        this.cardNo=bankName.name()+"****"+cardNo.substring(cardNo.length()-4,cardNo.length());
        this.requestAmount = requestAmount == null ? "" : MoneyUtil.moneyToString(requestAmount);
        this.approvalAmount = approvalAmount == null ? "" : MoneyUtil.moneyToString(approvalAmount);
        if (tradeStageEnum.equals(TradeStageEnum.审批)) {
            if (tradeStatusEnum.equals(TradeStatusEnum.正在处理)) {
                this.appTradetatusEnum = AppTradetatusEnum.审批中;
            }
            if (tradeStatusEnum.equals(TradeStatusEnum.通过)) {
                this.appTradetatusEnum = AppTradetatusEnum.审批通过;
            }
            if (tradeStatusEnum.equals(TradeStatusEnum.拒绝)) {
                this.appTradetatusEnum = AppTradetatusEnum.已拒绝;
            }
            if (tradeStatusEnum.equals(TradeStatusEnum.放弃)) {
                this.appTradetatusEnum = AppTradetatusEnum.已放弃;
            }
        }
    }

    /**
     * 已生成借据
     */
    public AppTradeVO(Long tradeId, Long createTime, Integer cycleCnt, ZtProductEnum ztProductEnum, String productCd,String productSubCd, RepayMethodEnum repayMethod,
                      String tradeFlowNo, String contractNo, Long requestAmount, Long approvalAmount, Long schdAmt, Long pmtDueDate, BillStatusEnum
                              billStatusEnum,String billNo,Long xfrOutPrin,Long unpaidAmt,String termNo) {
        this.tradeId = tradeId;
        this.createTime = TimeUtil.timeToString(createTime);
        this.cycleCnt = cycleCnt;
        this.ztProductEnum = ztProductEnum;
        this.productCd = productCd;
        this.productSubCd=productSubCd;
        this.repayMethod = repayMethod.name();
        this.tradeFlowNo = tradeFlowNo;
        this.contractNo = contractNo;
        this.billNo=billNo;
        if (billStatusEnum.equals(BillStatusEnum.已逾期)) {
            this.isOverDue = true;
            this.appTradetatusEnum=AppTradetatusEnum.已逾期;
            this.schdAmt = MoneyUtil.moneyToString(schdAmt);
            this.pmtDueDate = TimeUtil.timeToString(pmtDueDate);
            this.xfrOutPrin=MoneyUtil.moneyToString(xfrOutPrin);
            this.allInterest=MoneyUtil.moneyToString(unpaidAmt-xfrOutPrin);
            this.termNo=termNo;
        }
        if (billStatusEnum.equals(BillStatusEnum.正常)){
            this.appTradetatusEnum = AppTradetatusEnum.还款中;
            this.schdAmt = MoneyUtil.moneyToString(schdAmt);
            this.pmtDueDate = TimeUtil.timeToString(pmtDueDate);
            this.xfrOutPrin=MoneyUtil.moneyToString(xfrOutPrin);
            this.allInterest=MoneyUtil.moneyToString(unpaidAmt-xfrOutPrin);
            this.termNo=termNo;
        }
        if (billStatusEnum.equals(BillStatusEnum.已结清)) {
            this.appTradetatusEnum=AppTradetatusEnum.已结清;
        }
        this.requestAmount = MoneyUtil.moneyToString(requestAmount);
        this.approvalAmount = MoneyUtil.moneyToString(approvalAmount);
    }

    @ApiModelProperty(value="贷款id",name="tradeId")
    private Long tradeId;
    @ApiModelProperty(value="贷款申请时间",name="createTime")
    private String createTime;
    @ApiModelProperty(value="贷款期数",name="cycleCnt")
    private Integer cycleCnt;
    @ApiModelProperty(value="贷款产品类型：*消费贷(\"ZTShopping\"),*现金贷(\"ZTCASH\");",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="产品编码",name="productCd")
    private String productCd;
    @ApiModelProperty(value="子产品编号",name="productSubCd")
    private String productSubCd;
    @ApiModelProperty(value="还款方式：" +
            "   *随借随还(\"AT\"),\n" +
            "    *等额本金(\"AP\"),\n" +
            "    *等额本息(\"AI\"),\n" +
            "    *先息后本(\"IF\"),\n" +
            "    *一次性还本付息(\"OT\");",name="repayMethod")
    private String repayMethod;
    @ApiModelProperty(value="贷款流水号",name="tradeFlowNo")
    private String tradeFlowNo;
    @ApiModelProperty(value="贷款合同号",name="contractNo")
    private String contractNo;
    @ApiModelProperty(value="贷款借据号",name="billNo")
    private String billNo;
    @ApiModelProperty(value="收款银行卡号",name="cardNo")
    private String cardNo;
    @ApiModelProperty(value="贷款是否逾期",name="isOverDue")
    private boolean isOverDue = false;
    @ApiModelProperty(value="订单状态：" +
            "   *审批中,\n" +
            "    *审批通过,\n" +
            "    *已拒绝,\n" +
            "    *已放弃,\n" +
            "    *还款中,\n" +
            "    *已逾期,\n" +
            "    *已结清;",name="appTradetatusEnum")
    private AppTradetatusEnum appTradetatusEnum;
    @ApiModelProperty(value="用户申请金额",name="requestAmount")
    private String requestAmount;
    @ApiModelProperty(value="审批的金额",name="approvalAmount")
    private String approvalAmount;
    @ApiModelProperty(value="应还金额",name="schdAmt")
    private String schdAmt;
    @ApiModelProperty(value="当期应还时间",name="pmtDueDate")
    private String pmtDueDate;
    @ApiModelProperty(value="剩余未还本金",name="xfrOutPrin")
    private String xfrOutPrin;
    @ApiModelProperty(value="剩余总利息",name="allInterest")
    private String allInterest;
    @ApiModelProperty(value="贷款当前期数",name="termNo")
    private String termNo;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCycleCnt() {
        return cycleCnt;
    }

    public void setCycleCnt(Integer cycleCnt) {
        this.cycleCnt = cycleCnt;
    }

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public String getProductSubCd() {
        return productSubCd;
    }

    public void setProductSubCd(String productSubCd) {
        this.productSubCd = productSubCd;
    }

    public String getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(String repayMethod) {
        this.repayMethod = repayMethod;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public boolean isOverDue() {
        return isOverDue;
    }

    public void setOverDue(boolean overDue) {
        isOverDue = overDue;
    }

    public AppTradetatusEnum getAppTradetatusEnum() {
        return appTradetatusEnum;
    }

    public void setAppTradetatusEnum(AppTradetatusEnum appTradetatusEnum) {
        this.appTradetatusEnum = appTradetatusEnum;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getApprovalAmount() {
        return approvalAmount;
    }

    public void setApprovalAmount(String approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public String getSchdAmt() {
        return schdAmt;
    }

    public void setSchdAmt(String schdAmt) {
        this.schdAmt = schdAmt;
    }

    public String getPmtDueDate() {
        return pmtDueDate;
    }

    public void setPmtDueDate(String pmtDueDate) {
        this.pmtDueDate = pmtDueDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getXfrOutPrin() {
        return xfrOutPrin;
    }

    public void setXfrOutPrin(String xfrOutPrin) {
        this.xfrOutPrin = xfrOutPrin;
    }

    public String getAllInterest() {
        return allInterest;
    }

    public void setAllInterest(String allInterest) {
        this.allInterest = allInterest;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }
}
