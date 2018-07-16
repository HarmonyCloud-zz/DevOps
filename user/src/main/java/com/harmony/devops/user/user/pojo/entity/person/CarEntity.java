package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户车辆信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_person_car")
public class CarEntity extends BaseEntity {
    @ManyToOne
    private ConsumerUserEntity consumer;
    //制造商
    private String vehicleCompany;
    //车系
    private String vehicleBrand;
    //车辆型号
    private String vehicleModel;
    //车辆售价
    private double vehiclePrice;
    //首次上牌时间
    private long firstRegister;
    //车辆所在地-省
    private String vehicleLocProvince;
    //车辆所在地-市
    private String vehicleLocCity;
    //车辆归属地-省
    private String vehicleRegProvince;
    //车辆归属地-市
    private String vehicleRegCity;
    //车况
    private String vehicleCondition;
    //行驶里程
    private double vehicleUsage;
    //外观磨损情况
    private String exterior;
    //内饰磨损情况
    private String interior;
    //车辆颜色
    private String vehicleColor;

    public ConsumerUserEntity getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerUserEntity consumer) {
        this.consumer = consumer;
    }

    public String getVehicleCompany() {
        return vehicleCompany;
    }

    public void setVehicleCompany(String vehicleCompany) {
        this.vehicleCompany = vehicleCompany;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public long getFirstRegister() {
        return firstRegister;
    }

    public void setFirstRegister(long firstRegister) {
        this.firstRegister = firstRegister;
    }

    public String getVehicleLocProvince() {
        return vehicleLocProvince;
    }

    public void setVehicleLocProvince(String vehicleLocProvince) {
        this.vehicleLocProvince = vehicleLocProvince;
    }

    public String getVehicleLocCity() {
        return vehicleLocCity;
    }

    public void setVehicleLocCity(String vehicleLocCity) {
        this.vehicleLocCity = vehicleLocCity;
    }

    public String getVehicleRegProvince() {
        return vehicleRegProvince;
    }

    public void setVehicleRegProvince(String vehicleRegProvince) {
        this.vehicleRegProvince = vehicleRegProvince;
    }

    public String getVehicleRegCity() {
        return vehicleRegCity;
    }

    public void setVehicleRegCity(String vehicleRegCity) {
        this.vehicleRegCity = vehicleRegCity;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public double getVehicleUsage() {
        return vehicleUsage;
    }

    public void setVehicleUsage(double vehicleUsage) {
        this.vehicleUsage = vehicleUsage;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
}
