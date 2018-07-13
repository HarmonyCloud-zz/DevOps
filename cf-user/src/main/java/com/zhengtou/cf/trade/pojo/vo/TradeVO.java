package com.zhengtou.cf.trade.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 借款订单记录
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-贷款信息vo")
public class TradeVO extends PeakBaseVo {
    //后台
    public TradeVO(long id, String realname, String idnum, Integer cycleCnt, long createTime, ZtProductEnum ztProductEnum, String productCd, RepayMethodEnum repayMethod, String tradeFlowNo, String outTradeFlowNo, String
            appNO, String contractNo, TradeStageEnum flowStage, TradeStatusEnum tradeStatus, long requestAmount, Long approvalAmount, long orgId, String orgName) {
        this.tradeId=id;
        this.realname = realname;
        this.idnum = idnum;
        this.createTime= TimeUtil.timeToString(createTime);
        this.cycleCnt = cycleCnt;
        this.ztProductEnum = ztProductEnum;
        this.tradeFlowNo = tradeFlowNo;
        this.productCd=productCd;
        this.outTradeFlowNo = outTradeFlowNo;
        this.repayMethod=repayMethod;
        this.appNO = appNO;
        this.contractNo = contractNo;
        this.flowStage = flowStage;
        this.tradeStatus = tradeStatus;
        this.requestAmount = MoneyUtil.moneyToString(requestAmount);
        this.approvalAmount = approvalAmount!=null?MoneyUtil.moneyToString(approvalAmount):"";
        this.orgId=orgId;
        this.orgName=orgName;
    }
    @ApiModelProperty(value="贷款id",name="tradeId")
    private Long tradeId;
    @ApiModelProperty(value="客户来源机构id",name="orgId")
    private Long orgId;
    @ApiModelProperty(value="客户来源机构名称",name="orgName")
    private String orgName;
    @ApiModelProperty(value="申请时间",name="createTime")
    private String createTime;
    @ApiModelProperty(value="贷款人真实姓名",name="realname")
    private String realname;
    @ApiModelProperty(value="贷款人身份证号",name="idnum")
    private String idnum;
    @ApiModelProperty(value="贷款期数",name="cycleCnt")
    private Integer cycleCnt;
    @ApiModelProperty(value="贷款产品类型：*消费贷(\"ZTShopping\"),*现金贷(\"ZTCASH\");",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="产品编码",name="productCd")
    private String productCd;
    @ApiModelProperty(value="还款方式：" +
            "   *随借随还(\"AT\"),\n" +
            "    *等额本金(\"AP\"),\n" +
            "    *等额本息(\"AI\"),\n" +
            "    *先息后本(\"IF\"),\n" +
            "    *一次性还本付息(\"OT\");",name="repayMethod")
    private RepayMethodEnum repayMethod;
    @ApiModelProperty(value="贷款流水号",name="tradeFlowNo")
    private String tradeFlowNo;
    @ApiModelProperty(value="外部贷款流水号",name="outTradeFlowNo")
    private String outTradeFlowNo;
    @ApiModelProperty(value="长亮系统单号",name="appNO")
    private String appNO;
    @ApiModelProperty(value="贷款合同号",name="contractNo")
    private String contractNo;
    @ApiModelProperty(value="贷款阶段：预申请,审批,合同,借据;",name="flowStage")
    private TradeStageEnum flowStage;
    @ApiModelProperty(value="订单状态：" +
            "   *//审批\n" +
            "   * 正在处理,通过,拒绝,放弃,\n" +
            "    *//合同\n" +
            "    *打款中,打款成功,打款失败,商户结算,\n" +
            "    *//TODO\n" +
            "   * 驳回待补件;",name="tradeStatus")
    private TradeStatusEnum tradeStatus;
    @ApiModelProperty(value="用户申请金额",name="requestAmount")
    private String requestAmount;
    @ApiModelProperty(value="审批的金额",name="approvalAmount")
    private String approvalAmount;

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

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public RepayMethodEnum getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(RepayMethodEnum repayMethod) {
        this.repayMethod = repayMethod;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
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

    public String getAppNO() {
        return appNO;
    }

    public void setAppNO(String appNO) {
        this.appNO = appNO;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public TradeStageEnum getFlowStage() {
        return flowStage;
    }

    public void setFlowStage(TradeStageEnum flowStage) {
        this.flowStage = flowStage;
    }

    public TradeStatusEnum getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatusEnum tradeStatus) {
        this.tradeStatus = tradeStatus;
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
}
