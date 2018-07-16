package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 手机缴费实体
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_phone_pay")
public class PhoneRiskPaymentEntity  extends BaseEntity {
    @ManyToOne
    private BasePhoneRiskEntity basePhoneRisk;
    //日期
    private String pay_date;
    //金额
    private String pay_fee;
    //缴费渠道
    private String pay_channel;
    //缴费方式
    private String pay_type;

    public BasePhoneRiskEntity getBasePhoneRisk() {
        return basePhoneRisk;
    }

    public void setBasePhoneRisk(BasePhoneRiskEntity basePhoneRisk) {
        this.basePhoneRisk = basePhoneRisk;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
}
