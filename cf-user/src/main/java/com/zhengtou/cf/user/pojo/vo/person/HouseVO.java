package com.zhengtou.cf.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.HousePlanUseEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.HouseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房产vo
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-房产vo")
public class HouseVO extends PeakBaseVo {
    public HouseVO() {
    }

    //展示
    public HouseVO(long houseId, String propertyNo, String address, HouseTypeEnum propertyType, HousePlanUseEnum plannedUse, Double area, Double
            accessedValue, Double availLoanAmount) {
        this.houseId = houseId;
        this.propertyNo = propertyNo;
        this.address = address;
        this.propertyType = propertyType;
        this.plannedUse = plannedUse;
        this.area = area;
        this.accessedValue = accessedValue;
        this.availLoanAmount = availLoanAmount;
    }

    @ApiModelProperty(value="房产id",name="houseId")
    private Long houseId;
    @ApiModelProperty(value="房产证编号",name="propertyNo")
    private String propertyNo;
    @ApiModelProperty(value="房屋地址",name="address")
    private String address;
    @ApiModelProperty(value="房屋性质：" +
            "   *普通住宅(\"F101\"),\n" +
            "    *经济适用房(\"F102\"),\n" +
            "    *成本价房(\"F103\"),\n" +
            "    *央产房(\"F104\"),\n" +
            "    *商住(\"F201\"),\n" +
            "    *公寓(\"F202\"),\n" +
            "    *商业(\"F301\"),\n" +
            "    *别墅(\"F401\"),\n" +
            "    *写字楼(\"F402\");",name="propertyType")
    private HouseTypeEnum propertyType;
    @ApiModelProperty(value="规划用途：" +
            "   *住宅(\"F1\"),\n" +
            "    *商住_公寓(\"F2\"),\n" +
            "    *商业(\"F3\"),\n" +
            "    *别墅_写字楼_办公(\"F4\");",name="plannedUse")
    private HousePlanUseEnum plannedUse;
    @ApiModelProperty(value="建筑面积",name="area")
    private double area;
    @ApiModelProperty(value="所在省市",name="propertyProvince")
    private String propertyProvince;
    @ApiModelProperty(value="所在城市",name="propertyCity")
    private String propertyCity;
    @ApiModelProperty(value="所在区",name="propertyDistrict")
    private String propertyDistrict;
    @ApiModelProperty(value="房产评估值",name="accessedValue")
    private Double accessedValue;
    @ApiModelProperty(value="最大可贷款金额",name="availLoanAmount")
    private Double availLoanAmount;


    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
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
