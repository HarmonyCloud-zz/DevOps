package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 业务信息实体
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_phone_package")
public class PhoneRiskPackageEntity  extends BaseEntity {
    @ManyToOne
    private BasePhoneRiskEntity basePhoneRisk;
    //类型
    private String category;
    //业务名称
    private String package_name;
    //费用
    private String package_fee;
    //费用周期
    private String fee_cycle;
    //生效时间
    private String effect_time;
    //失效时间
    private String invalid_time;

    public BasePhoneRiskEntity getBasePhoneRisk() {
        return basePhoneRisk;
    }

    public void setBasePhoneRisk(BasePhoneRiskEntity basePhoneRisk) {
        this.basePhoneRisk = basePhoneRisk;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_fee() {
        return package_fee;
    }

    public void setPackage_fee(String package_fee) {
        this.package_fee = package_fee;
    }

    public String getFee_cycle() {
        return fee_cycle;
    }

    public void setFee_cycle(String fee_cycle) {
        this.fee_cycle = fee_cycle;
    }

    public String getEffect_time() {
        return effect_time;
    }

    public void setEffect_time(String effect_time) {
        this.effect_time = effect_time;
    }

    public String getInvalid_time() {
        return invalid_time;
    }

    public void setInvalid_time(String invalid_time) {
        this.invalid_time = invalid_time;
    }
}
