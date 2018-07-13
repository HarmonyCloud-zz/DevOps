package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;
import com.zhengtou.cf.util.StringUtils;

import javax.persistence.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_zt_order")
public class ZtOrderEntity extends BaseEntity {
    public ZtOrderEntity() {
    }

    public ZtOrderEntity(ThirdOrderEntity thirdOrder, String note) {
        this.thirdOrderId = thirdOrder.getThirdOrderId();
        this.goodId = thirdOrder.getGoodId();
        this.thirdCreateTime = thirdOrder.getThirdCreateTime();
        this.thirdOrderNo = thirdOrder.getThirdOrderNo();
        this.orderAmt = Integer.parseInt(thirdOrder.getOrderTerm())*thirdOrder.getMonthAmt();
        this.note= StringUtils.isNotBlank(note)?note:"";
        this.orderTerm=thirdOrder.getOrderTerm();
        this.signStatus=SignStatusEnum.预发标;
    }

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
     * 订单金额
     */
    private long orderAmt;
    /**
     * 订单期数
     */
    private String orderTerm;
    /**
     * 备注
     */
    private String note;
    /**
     * 发标日期
     */
    private Long signTime;
    /**
     * 最新还款日期
     */
    private Long lastRepayTime;
    /**
     * 发标状态
     */
    @Enumerated(EnumType.STRING)
    private SignStatusEnum signStatus;
    /**
     * 当前还款期数
     */
    private Integer repayTerm;

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

    public long getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(long orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getOrderTerm() {
        return orderTerm;
    }

    public void setOrderTerm(String orderTerm) {
        this.orderTerm = orderTerm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
    }

    public Long getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Long lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public SignStatusEnum getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(SignStatusEnum signStatus) {
        this.signStatus = signStatus;
    }

    public Integer getRepayTerm() {
        return repayTerm;
    }

    public void setRepayTerm(Integer repayTerm) {
        this.repayTerm = repayTerm;
    }
}
