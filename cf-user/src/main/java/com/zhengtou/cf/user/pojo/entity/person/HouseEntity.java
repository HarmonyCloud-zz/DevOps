package com.zhengtou.cf.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.person.enums.HousePlanUseEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.HouseTypeEnum;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 房产信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_person_house")
public class HouseEntity extends BaseEntity {
    @ManyToOne
    private ConsumerUserEntity consumer;
    //房产证编号
    private String propertyNo;
    //房屋地址
    private String address;
    //房屋性质
    private HouseTypeEnum propertyType;
    //规划用途
    private HousePlanUseEnum plannedUse;
    //建筑面积
    private double area;
    //所在省市
    private String propertyProvince;
    //所在城市
    private String propertyCity;
    //所在区
    private String propertyDistrict;
    //房产评估值
    private Double accessedValue;
    //最大可贷款金额
    private Double availLoanAmount;

    public ConsumerUserEntity getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerUserEntity consumer) {
        this.consumer = consumer;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HouseTypeEnum getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(HouseTypeEnum propertyType) {
        this.propertyType = propertyType;
    }

    public HousePlanUseEnum getPlannedUse() {
        return plannedUse;
    }

    public void setPlannedUse(HousePlanUseEnum plannedUse) {
        this.plannedUse = plannedUse;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getPropertyProvince() {
        return propertyProvince;
    }

    public void setPropertyProvince(String propertyProvince) {
        this.propertyProvince = propertyProvince;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyDistrict() {
        return propertyDistrict;
    }

    public void setPropertyDistrict(String propertyDistrict) {
        this.propertyDistrict = propertyDistrict;
    }

    public Double getAccessedValue() {
        return accessedValue;
    }

    public void setAccessedValue(Double accessedValue) {
        this.accessedValue = accessedValue;
    }

    public Double getAvailLoanAmount() {
        return availLoanAmount;
    }

    public void setAvailLoanAmount(Double availLoanAmount) {
        this.availLoanAmount = availLoanAmount;
    }
}
