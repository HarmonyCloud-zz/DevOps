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
@Table(name = "t_product_group")
public class ProductGrpupEntity extends BaseEntity {
    /**
     * 产品组代码
     */
    private String productGroupCd;
    /**
     * 产品组名称
     */
    private String productGroupName;
    /**
     * 产品组描述
     */
    private String description;

    public String getProductGroupCd() {
        return productGroupCd;
    }

    public void setProductGroupCd(String productGroupCd) {
        this.productGroupCd = productGroupCd;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
