package com.zhengtou.cf.product.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款产品组
 */
@Entity
@Table(name = "t_product")
public class ProductEntity extends BaseEntity {
    //产品组代码
    private String  productGroupCd;
    //产品代码
    private String productCd;
    //产品名称
    private String productName;
    //产品描述
    private String description;
    //是否启用
    private String productStatus;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
