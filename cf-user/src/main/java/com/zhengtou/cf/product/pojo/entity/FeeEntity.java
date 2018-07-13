package com.zhengtou.cf.product.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.product.pojo.entity.enums.ChargeFeeMethodEnum;
import com.zhengtou.cf.product.pojo.entity.enums.ChargeFeeRadixEnum;
import com.zhengtou.cf.product.pojo.entity.enums.FeeTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 手续费定义
 */
@Entity
@Table(name = "t_product_fee")
public class FeeEntity extends BaseEntity{
    private String productCd;
    private String productSubCd;
    //手续费代码
    @Enumerated(EnumType.STRING)
    private FeeTypeEnum feeType;
    //手续费收取方式
    @Enumerated(EnumType.STRING)
    private ChargeFeeMethodEnum chargeFeeMethod;
    //手续费基数
    @Enumerated(EnumType.STRING)
    private ChargeFeeRadixEnum chargeFeeRadix;
    //手续费比例
    private Double chargeFeeRate;
    //手续费附加费
    private Long chargeFeeVal;

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

    public FeeTypeEnum getFeeType() {
        return feeType;
    }

    public void setFeeType(FeeTypeEnum feeType) {
        this.feeType = feeType;
    }

    public ChargeFeeMethodEnum getChargeFeeMethod() {
        return chargeFeeMethod;
    }

    public void setChargeFeeMethod(ChargeFeeMethodEnum chargeFeeMethod) {
        this.chargeFeeMethod = chargeFeeMethod;
    }

    public ChargeFeeRadixEnum getChargeFeeRadix() {
        return chargeFeeRadix;
    }

    public void setChargeFeeRadix(ChargeFeeRadixEnum chargeFeeRadix) {
        this.chargeFeeRadix = chargeFeeRadix;
    }

    public Double getChargeFeeRate() {
        return chargeFeeRate;
    }

    public void setChargeFeeRate(Double chargeFeeRate) {
        this.chargeFeeRate = chargeFeeRate;
    }

    public Long getChargeFeeVal() {
        return chargeFeeVal;
    }

    public void setChargeFeeVal(Long chargeFeeVal) {
        this.chargeFeeVal = chargeFeeVal;
    }
}
