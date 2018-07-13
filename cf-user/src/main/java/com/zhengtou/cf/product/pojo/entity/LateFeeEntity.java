package com.zhengtou.cf.product.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 滞纳金定义
 */
@Entity
@Table(name = "t_product_Latefee")
public class LateFeeEntity extends BaseEntity{
    private String productCd;
    private String productSubCd;
    //滞纳金基数
        /*
        *INIT_PRINCIPAL|总本金
        *REMAIN_PRINCIPAL|未到期本金
        *OVER_DUE_PRINCIPAL|逾期本金
        *UNPAID_PRINCIPAL|未还总本金
        *CTD_PRINCIPAL|当期本金
        *CTD_PRINCIPAL_INTEREST|当期本息
        *ZERO|不收取
        * */
    private String lateFeeRadix;
    //滞纳金比例
    private String lateFeeRate;
    //滞纳金附加费
    private String lateFeeVal;
    //单笔金额上限
    private String maxAmt;
    //单笔金额下限
    private String minAmt;

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

    public String getLateFeeRadix() {
        return lateFeeRadix;
    }

    public void setLateFeeRadix(String lateFeeRadix) {
        this.lateFeeRadix = lateFeeRadix;
    }

    public String getLateFeeRate() {
        return lateFeeRate;
    }

    public void setLateFeeRate(String lateFeeRate) {
        this.lateFeeRate = lateFeeRate;
    }

    public String getLateFeeVal() {
        return lateFeeVal;
    }

    public void setLateFeeVal(String lateFeeVal) {
        this.lateFeeVal = lateFeeVal;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
    }

    public String getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(String minAmt) {
        this.minAmt = minAmt;
    }
}
