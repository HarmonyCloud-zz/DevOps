package com.zhengtou.cf.product.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款产品vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVO extends PeakBaseVo{
    public ProductVO(Long id, String productGroupCd, String productCd, String productName, String description, String productStatus) {
        this.id = id;
        this.productGroupCd = productGroupCd;
        this.productCd = productCd;
        this.productName = productName;
        this.description = description;
        this.productStatus = productStatus;
    }

    //id
    private Long id;
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
