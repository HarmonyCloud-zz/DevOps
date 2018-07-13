package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdOrderStatusEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 三方订单数据
 */
@Entity
@Table(name = "t_third_order")
public class ThirdOrderEntity extends BaseEntity {
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 三方订单编号
     */
    private String thirdOrderId;
    /**
     * 商品id
     */
    private String goodId;
    /**
     * 三方下单时间
     */
    private Long thirdCreateTime;
    /**
     * 三方订单号
     */
    private String thirdOrderNo;
    /**
     * 订单期数
     */
    private String orderTerm;
    /**
     * 商品新旧程度
     */
    private String isNew;
    /**
     * 是否购买安装包
     */
    private String isInstall;
    /**
     * 每月价格
     */
    private Long monthAmt;
    /**
     * 商品分类
     */
    private String goodCate;
    /**
     * 商品进价
     */
    private Long goodPurPrice;
    /**
     * 商品总价
     */
    private Long goodPrice;
    /**
     * 商品总租金
     */
    private Long orderGrossRent;
    /**
     * 订单来源
     */
    private String orderComeForm;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    private ThirdOrderStatusEnum thirdOrderStatus;
    /**
     * 商品名称
     */
    private String goodName;

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public Long getThirdCreateTime() {
        return thirdCreateTime;
    }

    public void setThirdCreateTime(Long thirdCreateTime) {
        this.thirdCreateTime = thirdCreateTime;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    public String getOrderTerm() {
        return orderTerm;
    }

    public void setOrderTerm(String orderTerm) {
        this.orderTerm = orderTerm;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getIsInstall() {
        return isInstall;
    }

    public void setIsInstall(String isInstall) {
        this.isInstall = isInstall;
    }

    public Long getMonthAmt() {
        return monthAmt;
    }

    public void setMonthAmt(Long monthAmt) {
        this.monthAmt = monthAmt;
    }

    public String getGoodCate() {
        return goodCate;
    }

    public void setGoodCate(String goodCate) {
        this.goodCate = goodCate;
    }

    public Long getGoodPurPrice() {
        return goodPurPrice;
    }

    public void setGoodPurPrice(Long goodPurPrice) {
        this.goodPurPrice = goodPurPrice;
    }

    public Long getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Long goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Long getOrderGrossRent() {
        return orderGrossRent;
    }

    public void setOrderGrossRent(Long orderGrossRent) {
        this.orderGrossRent = orderGrossRent;
    }

    public String getOrderComeForm() {
        return orderComeForm;
    }

    public void setOrderComeForm(String orderComeForm) {
        this.orderComeForm = orderComeForm;
    }

    public ThirdOrderStatusEnum getThirdOrderStatus() {
        return thirdOrderStatus;
    }

    public void setThirdOrderStatus(ThirdOrderStatusEnum thirdOrderStatus) {
        this.thirdOrderStatus = thirdOrderStatus;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}
