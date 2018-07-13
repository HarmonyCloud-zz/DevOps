package com.zhengtou.cf.product.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 罚金定义
 */
@Entity
@Table(name = "t_product_mulctfee")
public class MulctFeeEntity extends BaseEntity{
    private String productCd;
    private String productSubCd;
    //罚金基数
    private String mulctFeeRadix;
    //罚金费率
    private String mulctFeeRate;
    //罚金附加费
    private String mulctFeeVal;

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

    public String getMulctFeeRadix() {
        return mulctFeeRadix;
    }

    public void setMulctFeeRadix(String mulctFeeRadix) {
        this.mulctFeeRadix = mulctFeeRadix;
    }

    public String getMulctFeeRate() {
        return mulctFeeRate;
    }

    public void setMulctFeeRate(String mulctFeeRate) {
        this.mulctFeeRate = mulctFeeRate;
    }

    public String getMulctFeeVal() {
        return mulctFeeVal;
    }

    public void setMulctFeeVal(String mulctFeeVal) {
        this.mulctFeeVal = mulctFeeVal;
    }
}
