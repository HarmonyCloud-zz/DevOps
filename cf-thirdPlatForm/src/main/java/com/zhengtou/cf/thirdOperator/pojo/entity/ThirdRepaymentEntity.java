package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 还款记录
 */
@Entity
@Table(name = "t_third_repayment")
public class ThirdRepaymentEntity extends BaseEntity {
    @ManyToOne
    private ThirdOrderEntity thirdOrder;
    /**
     * 商户还款订单号
     */
    private String thirdRepaymentId;
    /**
     * 还款期数
     */
    private String number;
    /**
     * 应付金额
     */
    private Long amountNeed;
    /**
     * 实付金额
     */
    private Long amountAlready;
    /**
     * 三方还款订单创建时间
     */
    private Long thirdCreateTime;
    /**
     * 最后还款时间
     */
    private Long thirdLastPaytime;
    /**
     * 用户支付时间
     */
    private Long payTime;
    /**
     * 支付状态（0未付款 1已付款）
     */
    private String payDetail;
    /**
     * 订单期数 优惠券id
     */
    private String couponOid;
    /**
     * 商品id
     */
    private String goodId;
    /**
     * 全额付款金额
     */
    private Long totalAmt;
    /**
     * 逾期天数
     */
    private Integer overDueDay;
    /**
     * 逾期罚款
     */
    private Long overDueAmt;

    public ThirdOrderEntity getThirdOrder() {
        return thirdOrder;
    }

    public void setThirdOrder(ThirdOrderEntity thirdOrder) {
        this.thirdOrder = thirdOrder;
    }

    public String getThirdRepaymentId() {
        return thirdRepaymentId;
    }

    public void setThirdRepaymentId(String thirdRepaymentId) {
        this.thirdRepaymentId = thirdRepaymentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getAmountNeed() {
        return amountNeed;
    }

    public void setAmountNeed(Long amountNeed) {
        this.amountNeed = amountNeed;
    }

    public Long getAmountAlready() {
        return amountAlready;
    }

    public void setAmountAlready(Long amountAlready) {
        this.amountAlready = amountAlready;
    }

    public Long getThirdCreateTime() {
        return thirdCreateTime;
    }

    public void setThirdCreateTime(Long thirdCreateTime) {
        this.thirdCreateTime = thirdCreateTime;
    }

    public Long getThirdLastPaytime() {
        return thirdLastPaytime;
    }

    public void setThirdLastPaytime(Long thirdLastPaytime) {
        this.thirdLastPaytime = thirdLastPaytime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public void setTotalAmt(Long totalAmt) {
        this.totalAmt = totalAmt;
    }

    public void setOverDueDay(Integer overDueDay) {
        this.overDueDay = overDueDay;
    }

    public void setOverDueAmt(Long overDueAmt) {
        this.overDueAmt = overDueAmt;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
    }

    public String getCouponOid() {
        return couponOid;
    }

    public void setCouponOid(String couponOid) {
        this.couponOid = couponOid;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public Long getTotalAmt() {
        return totalAmt;
    }

    public Integer getOverDueDay() {
        return overDueDay;
    }

    public Long getOverDueAmt() {
        return overDueAmt;
    }
}
