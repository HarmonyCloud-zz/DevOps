package com.harmony.devops.user.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户车辆信息
 * @author 葛文镇
 */
@ApiModel(discriminator = "郑投-用户车辆信息vo")
public class CarReciveVO extends PeakBaseVo {
    public CarReciveVO() {
    }

    @ApiModelProperty(value="制造商",name="vehicleCompany")
    private String vehicleCompany;
    @ApiModelProperty(value="车系",name="vehicleBrand")
    private String vehicleBrand;
    @ApiModelProperty(value="车辆型号",name="vehicleModel")
    private String vehicleModel;
    @ApiModelProperty(value="车辆售价",name="vehiclePrice")
    private double vehiclePrice;
    @ApiModelProperty(value="首次上牌时间",name="firstRegister")
    private long firstRegister;
    @ApiModelProperty(value="车辆所在地-省",name="vehicleLocProvince")
    private String vehicleLocProvince;
    @ApiModelProperty(value="车辆所在地-市",name="vehicleLocCity")
    private String vehicleLocCity;
    @ApiModelProperty(value="车辆归属地-省",name="vehicleRegProvince")
    private String vehicleRegProvince;
    @ApiModelProperty(value="车辆归属地-市",name="vehicleRegCity")
    private String vehicleRegCity;
    @ApiModelProperty(value="车况",name="vehicleCondition")
    private String vehicleCondition;
    @ApiModelProperty(value="行驶里程",name="vehicleUsage")
    private Double vehicleUsage;
    @ApiModelProperty(value="外观磨损情况",name="exterior")
    private String exterior;
    @ApiModelProperty(value="内饰磨损情况",name="interior")
    private String interior;
    @ApiModelProperty(value="车辆颜色",name="vehicleColor")
    private String vehicleColor;

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
