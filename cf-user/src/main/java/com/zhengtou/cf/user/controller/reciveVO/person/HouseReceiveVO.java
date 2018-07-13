package com.zhengtou.cf.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.HousePlanUseEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.HouseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房产信息
 * @author 葛文镇
 */
@ApiModel(discriminator = "郑投-房产信息vo")
public class HouseReceiveVO extends PeakBaseVo {
    public HouseReceiveVO() {
    }

    @ApiModelProperty(value="房产证编号",name="propertyNo")
    private String propertyNo;
    @ApiModelProperty(value="房屋地址",name="address")
    private String address;
    @ApiModelProperty(value="房屋性质",name="propertyType")
    private HouseTypeEnum propertyType;
    @ApiModelProperty(value="规划用途",name="plannedUse")
    private HousePlanUseEnum plannedUse;
    @ApiModelProperty(value="建筑面积",name="area")
    private Double area;
    @ApiModelProperty(value="所在省",name="propertyProvince")
    private String propertyProvince;
    @ApiModelProperty(value="所在城市",name="propertyCity")
    private String propertyCity;
    @ApiModelProperty(value="所在区",name="propertyDistrict")
    private String propertyDistrict;
    @ApiModelProperty(value="房产评估值",name="accessedValue")
    private Double accessedValue;
    @ApiModelProperty(value="最大可贷款金额",name="availLoanAmount")
    private Double availLoanAmount;

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

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
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
