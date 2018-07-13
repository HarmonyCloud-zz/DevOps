package com.zhengtou.cf.trade.controller.receiveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.trade.pojo.entity.order.OrderEntity;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单vo
 */
public class OrderReciveVO extends PeakBaseVo{
    public OrderReciveVO() {
    }
    //商品总金额
    private Double totalAmount;
    //商品总件数
    private Integer totalQuantity;
    //商品总分期金额
    private Double totalStageAmount;
    //首付金额
    private Double downPayment;
    //支付方式
    private OrderEntity.PayTypeEnum paymentType;
    //下单时间
    private String orderCreateTime;
    //交易时间
    private String transactionTime;
    //商品列表
    private List<OrderProductReciveVO> orderProductInfo;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getTotalStageAmount() {
        return totalStageAmount;
    }

    public void setTotalStageAmount(Double totalStageAmount) {
        this.totalStageAmount = totalStageAmount;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public OrderEntity.PayTypeEnum getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(OrderEntity.PayTypeEnum paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public List<OrderProductReciveVO> getOrderProductInfo() {
        return orderProductInfo;
    }

    public void setOrderProductInfo(List<OrderProductReciveVO> orderProductInfo) {
        this.orderProductInfo = orderProductInfo;
    }
}
