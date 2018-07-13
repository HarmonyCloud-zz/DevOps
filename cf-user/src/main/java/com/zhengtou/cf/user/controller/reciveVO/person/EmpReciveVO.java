package com.zhengtou.cf.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpPositionEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工作信息vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "郑投-工作信息vo")
public class EmpReciveVO extends PeakBaseVo {
    public EmpReciveVO() {
    }
    @ApiModelProperty(value="入职日期",name="empStandFrom")
    private String empStandFrom;
    @ApiModelProperty(value="工作年限",name="empYears")
    private Integer empYears;
    @ApiModelProperty(value="工作单位",name="empUnitName")
    private String empUnitName;
    @ApiModelProperty(value="参加工作日期",name="empWorkStandFrom")
    private String empWorkStandFrom;
    @ApiModelProperty(value="任职部门",name="empDepartment")
    private String empDepartment;
    @ApiModelProperty(value="职位",name="empPost")
    private EmpPositionEnum empPost;
    @ApiModelProperty(value="单位行业类别",name="empType")
    private String empType;
    @ApiModelProperty(value="行业从业年限（年）",name="empObtainYear")
    private String empObtainYear;
    @ApiModelProperty(value="单位性质",name="empStructure")
    private EmpTypeEnum empStructure;
    @ApiModelProperty(value="单位电话",name="empPhone")
    private String empPhone;
    @ApiModelProperty(value="单位电话区号",name="empPhoneArea")
    private String empPhoneArea;
    @ApiModelProperty(value="单位电话分机号",name="empPhoneExtNum")
    private String empPhoneExtNum;
    @ApiModelProperty(value="单位所在省",name="empState")
    private String empState;
    @ApiModelProperty(value="单位所在市",name="empCity")
    private String empCity;
    @ApiModelProperty(value="单位所在区/县",name="empZone")
    private String empZone;
    @ApiModelProperty(value="单位详细地址",name="empAddress")
    private String empAddress;

    public String getEmpUnitName() {
        return empUnitName;
    }

    public void setEmpUnitName(String empUnitName) {
        this.empUnitName = empUnitName;
    }

    public EmpPositionEnum getEmpPost() {
        return empPost;
    }

    public void setEmpPost(EmpPositionEnum empPost) {
        this.empPost = empPost;
    }

    public EmpTypeEnum getEmpStructure() {
        return empStructure;
    }

    public void setEmpStructure(EmpTypeEnum empStructure) {
        this.empStructure = empStructure;
    }

    public String getEmpStandFrom() {
        return empStandFrom;
    }

    public void setEmpStandFrom(String empStandFrom) {
        this.empStandFrom = empStandFrom;
    }

    public Integer getEmpYears() {
        return empYears;
    }

    public void setEmpYears(Integer empYears) {
        this.empYears = empYears;
    }

    public String getEmpWorkStandFrom() {
        return empWorkStandFrom;
    }

    public void setEmpWorkStandFrom(String empWorkStandFrom) {
        this.empWorkStandFrom = empWorkStandFrom;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpObtainYear() {
        return empObtainYear;
    }

    public void setEmpObtainYear(String empObtainYear) {
        this.empObtainYear = empObtainYear;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpPhoneArea() {
        return empPhoneArea;
    }

    public void setEmpPhoneArea(String empPhoneArea) {
        this.empPhoneArea = empPhoneArea;
    }

    public String getEmpPhoneExtNum() {
        return empPhoneExtNum;
    }

    public void setEmpPhoneExtNum(String empPhoneExtNum) {
        this.empPhoneExtNum = empPhoneExtNum;
    }

    public String getEmpState() {
        return empState;
    }

    public void setEmpState(String empState) {
        this.empState = empState;
    }

    public String getEmpCity() {
        return empCity;
    }

    public void setEmpCity(String empCity) {
        this.empCity = empCity;
    }

    public String getEmpZone() {
        return empZone;
    }

    public void setEmpZone(String empZone) {
        this.empZone = empZone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }
}
