package com.zhengtou.cf.product.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款产品vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-贷款子产品返回vo")
public class SubProductVO extends PeakBaseVo{
    //展示
    public SubProductVO(Long id, String productGroupCd, String productCd, String productSubCd, ZtProductEnum ztProductEnum, String description,
                        RepayMethodEnum repayMethodEnum, Integer cycleCnt, Double maxAmt, Double minAmt, String tag) {
        this.id = id;
        this.productGroupCd = productGroupCd;
        this.productCd = productCd;
        this.productSubCd = productSubCd;
        this.ztProductEnum = ztProductEnum;
        this.description = StringUtils.isNotBlank(description)?description:"";
        this.repayMethod = repayMethod;
        this.cycleCnt = cycleCnt;
        this.maxAmt = maxAmt;
        this.minAmt = minAmt;
        this.tag = StringUtils.isNotBlank(tag)?tag:"";
    }

    public SubProductVO(Long id, String productCd, RepayMethodEnum repayMethod, Integer cycleCnt) {
        this.id = id;
        this.productCd = productCd;
        this.repayMethod = repayMethod;
        this.cycleCnt = cycleCnt;
    }

    @ApiModelProperty(value="子产品id",name="id")
    private Long id;
    @ApiModelProperty(value="产品组编码",name="productGroupCd")
    private String productGroupCd;
    @ApiModelProperty(value="产品编码",name="productCd")
    private String productCd;
    @ApiModelProperty(value="子产品编码",name="productSubCd")
    private String productSubCd;
    @ApiModelProperty(value="产品类型:消费贷(\"ZTShopping\"),现金贷(\"ZTCASH\");",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="描述",name="description")
    private String description;
    @ApiModelProperty(value="还款方式:" +
            "*随借随还(\"AT\"),\n" +
            "    *等额本金(\"AP\"),\n" +
            "    *等额本息(\"AI\"),\n" +
            "    *先息后本(\"IF\"),\n" +
            "    *一次性还本付息(\"OT\");",name="repayMethod")
    private RepayMethodEnum repayMethod;
    @ApiModelProperty(value="期数",name="cycleCnt")
    private Integer cycleCnt;
    @ApiModelProperty(value="金额上限",name="maxAmt")
    private Double maxAmt;
    @ApiModelProperty(value="金额下限",name="minAmt")
    private Double minAmt;
    @ApiModelProperty(value="标签",name="tag")
    private String tag;
    @ApiModelProperty(value="周期类型：年/月/日",name="cycleType")
    private String cycleType;
    @ApiModelProperty(value="周期间隔",name="cycleInterval")
    private Integer cycleInterval;
    @ApiModelProperty(value="周期间隔范围上限",name="cycleIntervalMax")
    private String cycleIntervalMax;
    @ApiModelProperty(value="周期间隔范围下限",name="cycleIntervalMin")
    private String cycleIntervalMin;
    @ApiModelProperty(value="基准利率单位",name="interestRatePeriod")
    private String interestRatePeriod;
    @ApiModelProperty(value="默认基准利率",name="interestRate")
    private Double interestRate;
    @ApiModelProperty(value="是否启用升档罚息利率",name="isPenaltyUp")
    private String isPenaltyUp;
    @ApiModelProperty(value="罚息利率单位",name="penaltyRatePeriod")
    private String penaltyRatePeriod;
    @ApiModelProperty(value="默认罚息利率",name="penaltyRate")
    private Double penaltyRate;
    @ApiModelProperty(value="罚息利率升档类型",name="penaltyUpType")
    private String penaltyUpType;
    @ApiModelProperty(value="罚息利率升档值",name="penaltyUpValue")
    private Double penaltyUpValue;
    @ApiModelProperty(value="升档罚息利率",name="penaltyUpRate")
    private Double penaltyUpRate;
    @ApiModelProperty(value="默认挪用罚息利率",name="penaltyPurposeRate")
    private Double penaltyPurposeRate;
    @ApiModelProperty(value="复利利率单位",name="compoundRatePeriod")
    private String compoundRatePeriod;
    @ApiModelProperty(value="默认复利利率",name="compoundRate")
    private Double compoundRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductGroupCd() {
        return productGroupCd;
    }

    public void setProductGroupCd(String productGroupCd) {
        this.productGroupCd = productGroupCd;
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

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RepayMethodEnum getRepayMethod() {
        return repayMethod;
    }

    public void setRepayMethod(RepayMethodEnum repayMethod) {
        this.repayMethod = repayMethod;
    }

    public Integer getCycleCnt() {
        return cycleCnt;
    }

    public void setCycleCnt(Integer cycleCnt) {
        this.cycleCnt = cycleCnt;
    }

    public Double getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(Double maxAmt) {
        this.maxAmt = maxAmt;
    }

    public Double getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(Double minAmt) {
        this.minAmt = minAmt;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType;
    }

    public Integer getCycleInterval() {
        return cycleInterval;
    }

    public void setCycleInterval(Integer cycleInterval) {
        this.cycleInterval = cycleInterval;
    }

    public String getCycleIntervalMax() {
        return cycleIntervalMax;
    }

    public void setCycleIntervalMax(String cycleIntervalMax) {
        this.cycleIntervalMax = cycleIntervalMax;
    }

    public String getCycleIntervalMin() {
        return cycleIntervalMin;
    }

    public void setCycleIntervalMin(String cycleIntervalMin) {
        this.cycleIntervalMin = cycleIntervalMin;
    }

    public String getInterestRatePeriod() {
        return interestRatePeriod;
    }

    public void setInterestRatePeriod(String interestRatePeriod) {
        this.interestRatePeriod = interestRatePeriod;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getIsPenaltyUp() {
        return isPenaltyUp;
    }

    public void setIsPenaltyUp(String isPenaltyUp) {
        this.isPenaltyUp = isPenaltyUp;
    }

    public String getPenaltyRatePeriod() {
        return penaltyRatePeriod;
    }

    public void setPenaltyRatePeriod(String penaltyRatePeriod) {
        this.penaltyRatePeriod = penaltyRatePeriod;
    }

    public Double getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(Double penaltyRate) {
        this.penaltyRate = penaltyRate;
    }

    public String getPenaltyUpType() {
        return penaltyUpType;
    }

    public void setPenaltyUpType(String penaltyUpType) {
        this.penaltyUpType = penaltyUpType;
    }

    public Double getPenaltyUpValue() {
        return penaltyUpValue;
    }

    public void setPenaltyUpValue(Double penaltyUpValue) {
        this.penaltyUpValue = penaltyUpValue;
    }

    public Double getPenaltyUpRate() {
        return penaltyUpRate;
    }

    public void setPenaltyUpRate(Double penaltyUpRate) {
        this.penaltyUpRate = penaltyUpRate;
    }

    public Double getPenaltyPurposeRate() {
        return penaltyPurposeRate;
    }

    public void setPenaltyPurposeRate(Double penaltyPurposeRate) {
        this.penaltyPurposeRate = penaltyPurposeRate;
    }

    public String getCompoundRatePeriod() {
        return compoundRatePeriod;
    }

    public void setCompoundRatePeriod(String compoundRatePeriod) {
        this.compoundRatePeriod = compoundRatePeriod;
    }

    public Double getCompoundRate() {
        return compoundRate;
    }

    public void setCompoundRate(Double compoundRate) {
        this.compoundRate = compoundRate;
    }
}
