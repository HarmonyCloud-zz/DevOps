package com.zhengtou.cf.product.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款子产品
 */
@Entity
@Table(name = "t_product_sub")
public class SubProductEntity extends BaseEntity {
    private String productGroupCd;
    private String productCd;
    @Enumerated(EnumType.STRING)
    private ZtProductEnum ztProductEnum;
    //子产品编号
    private String productSubCd;
    //描述
    private String description;
    //还款方式
    @Enumerated(EnumType.STRING)
    private RepayMethodEnum repayMethod;
    //期数
    private Integer cycleCnt;
    //金额上限
    private Double maxAmt;
    //金额下限
    private Double minAmt;
    //标签
    private String tag;
    //周期类型
    private String cycleType;
    //周期间隔
    private Integer cycleInterval;
    //周期间隔范围上限
    private String cycleIntervalMax;
    //周期间隔范围下限
    private String cycleIntervalMin;
    //基准利率单位
    private String interestRatePeriod;
    //默认基准利率
    private Double interestRate;
    //是否启用升档罚息利率
    private String isPenaltyUp;
    //罚息利率单位
    private String penaltyRatePeriod;
    //默认罚息利率
    private Double penaltyRate;
    //罚息利率升档类型
    private String penaltyUpType;
    //罚息利率升档值
    private Double penaltyUpValue;
    //升档罚息利率
    private Double penaltyUpRate;
    //默认挪用罚息利率
    private Double penaltyPurposeRate;
    //复利利率单位
    private String compoundRatePeriod;
    //默认复利利率
    private Double compoundRate;


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

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public String getProductSubCd() {
        return productSubCd;
    }

    public void setProductSubCd(String productSubCd) {
        this.productSubCd = productSubCd;
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
