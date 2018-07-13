package com.zhengtou.cf.trade.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class OrderProductVO extends PeakBaseVo{
    public OrderProductVO(String commodityId, String brand, String commodityName, Double price, Integer quantity) {
        this.commodityId = commodityId;
        this.brand = brand;
        this.commodityName = commodityName;
        this.price = price;
        this.quantity = quantity;
    }

    //商品编号
    private String commodityId;
    //一级品类
    private String categoryLevel1Id;
    //一级品类名称
    private String categoryLevel1Name;
    //二级品类
    private String categoryLevel2Id;
    //二级品类名称
    private String categoryLevel2Name;
    //三级品类
    private String categoryLevel3Id;
    //三级品类名称
    private String categoryLevel3Name;
    //品牌名称
    private String brand;
    //商品名称
    private String commodityName;
    //单价
    private Double price;
    //件数
    private Integer quantity;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(String categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public String getCategoryLevel1Name() {
        return categoryLevel1Name;
    }

    public void setCategoryLevel1Name(String categoryLevel1Name) {
        this.categoryLevel1Name = categoryLevel1Name;
    }

    public String getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(String categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }

    public String getCategoryLevel2Name() {
        return categoryLevel2Name;
    }

    public void setCategoryLevel2Name(String categoryLevel2Name) {
        this.categoryLevel2Name = categoryLevel2Name;
    }

    public String getCategoryLevel3Id() {
        return categoryLevel3Id;
    }

    public void setCategoryLevel3Id(String categoryLevel3Id) {
        this.categoryLevel3Id = categoryLevel3Id;
    }

    public String getCategoryLevel3Name() {
        return categoryLevel3Name;
    }

    public void setCategoryLevel3Name(String categoryLevel3Name) {
        this.categoryLevel3Name = categoryLevel3Name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
