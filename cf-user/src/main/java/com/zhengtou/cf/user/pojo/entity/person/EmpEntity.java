package com.zhengtou.cf.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpPositionEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpTypeEnum;

import javax.persistence.*;

/**
 * 工作信息
 */
@Entity
@Table(name = "t_person_employee")
public class EmpEntity extends BaseEntity {
    @ManyToOne
    private PersonalEntity personal;
    //入职日期
    private String empStandFrom;
    //工作年限
    private Integer empYears;
    //工作单位
    private String empUnitName;
    //参加工作日期
    private String empWorkStandFrom;
    //任职部门
    private String empDepartment;
    //职位
    @Enumerated(EnumType.STRING)
    private EmpPositionEnum empPost;
    //单位行业类别
    private String empType;
    //行业从业年限（年）
    private String empObtainYear;
    //单位性质
    @Enumerated(EnumType.ORDINAL)
    private EmpTypeEnum empStructure;
    //单位电话
    private String empPhone;
    //单位电话区号
    private String empPhoneArea;
    //单位电话分机号
    private String empPhoneExtNum;
    //单位所在省
    private String empState;
    //单位所在市
    private String empCity;
    //单位所在区/县
    private String empZone;
    //单位详细地址
    private String empAddress;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
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

    public String getEmpUnitName() {
        return empUnitName;
    }

    public void setEmpUnitName(String empUnitName) {
        this.empUnitName = empUnitName;
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

    public EmpPositionEnum getEmpPost() {
        return empPost;
    }

    public void setEmpPost(EmpPositionEnum empPost) {
        this.empPost = empPost;
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

    public EmpTypeEnum getEmpStructure() {
        return empStructure;
    }

    public void setEmpStructure(EmpTypeEnum empStructure) {
        this.empStructure = empStructure;
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
