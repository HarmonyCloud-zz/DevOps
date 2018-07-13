package com.zhengtou.cf.trade.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.trade.pojo.entity.order.OrderEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class OrderVO extends PeakBaseVo{
    //订单编号
    private String orderId= DBUtil.getOrderNo();
    //商户订单号
    @Column(unique = true)
    private String referenceId;
    //商户编号
    private String companyId;
    //终端编号
    private String terminalId;
    //销售人员编号
    private String raId;
    //客户编号
    private String customerId;
    //营销活动编号
    private String promotionId;
    //商品总金额
    private Double totalAmount;
    //商品总件数
    private Integer totalQuantity;
    //商品总分期金额
    private Double totalStageAmount;
    //收件人
    private String consignee;
    //收件人电话
    private String consigneePhone;
    //配送地址
    private String consigneeAddress;
    //内部代码
    private String internalCode;
    //是否代收首付
    private String collectionDownpayment;
    //首付金额
    private Double downPayment;
    //补贴比例
    private Double subsidyRatio;
    //支付方式
    @Enumerated(EnumType.ORDINAL)
    private OrderEntity.PayTypeEnum paymentType;

    public enum PayTypeEnum{
        内部支付,在线支付,在线代理支付,POS支付,POS代理支付;
    }
    //下单时间
    private String orderCreateTime;
    //交易时间
    private String transactionTime;
    //商品列表
    private List<OrderProductVO> orderProductInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getRaId() {
        return raId;
    }

    public void setRaId(String raId) {
        this.raId = raId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

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

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getCollectionDownpayment() {
        return collectionDownpayment;
    }

    public void setCollectionDownpayment(String collectionDownpayment) {
        this.collectionDownpayment = collectionDownpayment;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public Double getSubsidyRatio() {
        return subsidyRatio;
    }

    public void setSubsidyRatio(Double subsidyRatio) {
        this.subsidyRatio = subsidyRatio;
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

    public List<OrderProductVO> getOrderProductInfo() {
        return orderProductInfo;
    }

    public void setOrderProductInfo(List<OrderProductVO> orderProductInfo) {
        this.orderProductInfo = orderProductInfo;
    }
}
