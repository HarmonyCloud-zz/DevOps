package com.zhengtou.cf.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 通话记录实体
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_phone_call")
public class PhoneRiskCallEntity  extends BaseEntity {
    @ManyToOne
    private BasePhoneRiskEntity basePhoneRisk;
    //起始时间
    private String call_start_time;
    //通话地点
    private String call_address;
    //呼叫类型
    private String call_type_name;
    //对方号码
    private String call_other_number;
    //通话市场
    private String call_time;
    //通话类型
    private String call_land_type;
    //本地非或漫游费
    private String call_roam_cost;
    //长途费
    private String call_long_distance;
    //见面
    private String call_discount;
    //费用小计
    private String call_cost;


    public BasePhoneRiskEntity getBasePhoneRisk() {
        return basePhoneRisk;
    }

    public void setBasePhoneRisk(BasePhoneRiskEntity basePhoneRisk) {
        this.basePhoneRisk = basePhoneRisk;
    }

    public String getCall_start_time() {
        return call_start_time;
    }

    public void setCall_start_time(String call_start_time) {
        this.call_start_time = call_start_time;
    }

    public String getCall_address() {
        return call_address;
    }

    public void setCall_address(String call_address) {
        this.call_address = call_address;
    }

    public String getCall_type_name() {
        return call_type_name;
    }

    public void setCall_type_name(String call_type_name) {
        this.call_type_name = call_type_name;
    }

    public String getCall_other_number() {
        return call_other_number;
    }

    public void setCall_other_number(String call_other_number) {
        this.call_other_number = call_other_number;
    }

    public String getCall_time() {
        return call_time;
    }

    public void setCall_time(String call_time) {
        this.call_time = call_time;
    }

    public String getCall_land_type() {
        return call_land_type;
    }

    public void setCall_land_type(String call_land_type) {
        this.call_land_type = call_land_type;
    }

    public String getCall_roam_cost() {
        return call_roam_cost;
    }

    public void setCall_roam_cost(String call_roam_cost) {
        this.call_roam_cost = call_roam_cost;
    }

    public String getCall_long_distance() {
        return call_long_distance;
    }

    public void setCall_long_distance(String call_long_distance) {
        this.call_long_distance = call_long_distance;
    }

    public String getCall_discount() {
        return call_discount;
    }

    public void setCall_discount(String call_discount) {
        this.call_discount = call_discount;
    }

    public String getCall_cost() {
        return call_cost;
    }

    public void setCall_cost(String call_cost) {
        this.call_cost = call_cost;
    }
}
