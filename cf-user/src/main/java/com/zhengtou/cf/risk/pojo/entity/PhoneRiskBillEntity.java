package com.zhengtou.cf.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 账单记录
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_phone_bill")
public class PhoneRiskBillEntity  extends BaseEntity {
    @ManyToOne
    private BasePhoneRiskEntity basePhoneRisk;
    //费用名称
    private String fee_name;
    //费用类别
    private String fee_category;
    //金额
    private String fee_amount;
    //用户号码
    private String user_number;

    public BasePhoneRiskEntity getBasePhoneRisk() {
        return basePhoneRisk;
    }

    public void setBasePhoneRisk(BasePhoneRiskEntity basePhoneRisk) {
        this.basePhoneRisk = basePhoneRisk;
    }

    public String getFee_name() {
        return fee_name;
    }

    public void setFee_name(String fee_name) {
        this.fee_name = fee_name;
    }

    public String getFee_category() {
        return fee_category;
    }

    public void setFee_category(String fee_category) {
        this.fee_category = fee_category;
    }

    public String getFee_amount() {
        return fee_amount;
    }

    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }
}
