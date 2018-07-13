package com.zhengtou.cf.thirdOperator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 三方订单数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdOrderVO extends PeakBaseVo {
    public ThirdOrderVO(Long id, String thirdOrderId, String goodId, Long thirdCreateTime, String thirdOrderNo, String orderTerm, String isNew,
                        String isInstall, Long monthAmt,Long createTime) {
        this.id = id;
        this.thirdOrderId = thirdOrderId;
        this.goodId = goodId;
        this.thirdCreateTime = TimeUtil.timeToString(thirdCreateTime);
        this.thirdOrderNo = thirdOrderNo;
        this.orderTerm = orderTerm;
        this.isNew = isNew;
        this.isInstall = isInstall;
        this.monthAmt = MoneyUtil.moneyToString(monthAmt);
        this.createTime=TimeUtil.timeToString(createTime);
    }

    private String createTime;
    private Long id;
    private String thirdOrderId;
    private String goodId;
    private String thirdCreateTime;
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
    private String monthAmt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getThirdCreateTime() {
        return thirdCreateTime;
    }

    public void setThirdCreateTime(String thirdCreateTime) {
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

    public String getMonthAmt() {
        return monthAmt;
    }

    public void setMonthAmt(String monthAmt) {
        this.monthAmt = monthAmt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
