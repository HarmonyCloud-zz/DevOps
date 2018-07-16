package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 基础运营商数据
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_phone")
public class BasePhoneRiskEntity  extends BaseEntity {
    @OneToOne
    private ConsumerUserEntity user;
    //姓名
    private String user_name;
    //性别
    private String user_sex;
    //手机号
    private String user_number;
    //身份证
    private String cert_num;
    //联系地址
    private String cert_addr;
    //联系电话
    private String user_contact_no;
    //邮箱地址
    private String user_email;
    //邮政编码
    private String post_code;
    //账单周期
    private String bill_cycle;
    //账单费用
    private String bill_fee;
    //减免
    private String bill_discount;
    //费用合计
    private String bill_total;
    //违约金
    private String breach_amount;
    //已支付
    private String paid_amount;
    //未支付
    private String unpaid_amount;


    public ConsumerUserEntity getUser() {
        return user;
    }

    public void setUser(ConsumerUserEntity user) {
        this.user = user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getCert_num() {
        return cert_num;
    }

    public void setCert_num(String cert_num) {
        this.cert_num = cert_num;
    }

    public String getCert_addr() {
        return cert_addr;
    }

    public void setCert_addr(String cert_addr) {
        this.cert_addr = cert_addr;
    }

    public String getUser_contact_no() {
        return user_contact_no;
    }

    public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getBill_cycle() {
        return bill_cycle;
    }

    public void setBill_cycle(String bill_cycle) {
        this.bill_cycle = bill_cycle;
    }

    public String getBill_fee() {
        return bill_fee;
    }

    public void setBill_fee(String bill_fee) {
        this.bill_fee = bill_fee;
    }

    public String getBill_discount() {
        return bill_discount;
    }

    public void setBill_discount(String bill_discount) {
        this.bill_discount = bill_discount;
    }

    public String getBill_total() {
        return bill_total;
    }

    public void setBill_total(String bill_total) {
        this.bill_total = bill_total;
    }

    public String getBreach_amount() {
        return breach_amount;
    }

    public void setBreach_amount(String breach_amount) {
        this.breach_amount = breach_amount;
    }

    public String getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(String paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getUnpaid_amount() {
        return unpaid_amount;
    }

    public void setUnpaid_amount(String unpaid_amount) {
        this.unpaid_amount = unpaid_amount;
    }
}
