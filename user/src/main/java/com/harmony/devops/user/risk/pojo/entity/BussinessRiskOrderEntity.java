package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 电商订单列表
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_buss_order")
public class BussinessRiskOrderEntity  extends BaseEntity {
    @ManyToOne
    private BaseBussinessRiskEntity baseBussinessRisk;
    //订单号
    private String order_id;
    //订单金额
    private String order_amount;
    //订单类型
    private String order_type;
    //订单时间
    private String order_time;
    //订单状态
    private String order_status;
    //商铺名称
    private String order_shop;
    //收货人
    private String receiver_name;
    //收获地址
    private String receiver_addr;
    //手机号码
    private String receiver_mobile;
    //固定电话
    private String receiver_telephone;
    //发票抬头
    private String receipt_title;
    //发票类型
    private String receipt_type;
    //发票内容
    private String receipt_content;
    //订单收获右边
    private String receiver_zipCode;


    public BaseBussinessRiskEntity getBaseBussinessRisk() {
        return baseBussinessRisk;
    }

    public void setBaseBussinessRisk(BaseBussinessRiskEntity baseBussinessRisk) {
        this.baseBussinessRisk = baseBussinessRisk;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_shop() {
        return order_shop;
    }

    public void setOrder_shop(String order_shop) {
        this.order_shop = order_shop;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_addr() {
        return receiver_addr;
    }

    public void setReceiver_addr(String receiver_addr) {
        this.receiver_addr = receiver_addr;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_telephone() {
        return receiver_telephone;
    }

    public void setReceiver_telephone(String receiver_telephone) {
        this.receiver_telephone = receiver_telephone;
    }

    public String getReceipt_title() {
        return receipt_title;
    }

    public void setReceipt_title(String receipt_title) {
        this.receipt_title = receipt_title;
    }

    public String getReceipt_type() {
        return receipt_type;
    }

    public void setReceipt_type(String receipt_type) {
        this.receipt_type = receipt_type;
    }

    public String getReceipt_content() {
        return receipt_content;
    }

    public void setReceipt_content(String receipt_content) {
        this.receipt_content = receipt_content;
    }

    public String getReceiver_zipCode() {
        return receiver_zipCode;
    }

    public void setReceiver_zipCode(String receiver_zipCode) {
        this.receiver_zipCode = receiver_zipCode;
    }
}
