package com.zhengtou.cf.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpPositionEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工作信息vo
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-工作信息vo")
public class EmpVO extends PeakBaseVo {
    public EmpVO() {
    }

    //展示
    public EmpVO(long empId,String empStandFrom,Integer empYears, String empUnitName, String empWorkStandFrom, String empDepartment, EmpPositionEnum empPost, String empType,
                 EmpTypeEnum empStructure) {
        this.empId=empId;
        this.empStandFrom=empStandFrom;
        this.empYears = empYears;
        this.empUnitName = empUnitName;
        this.empWorkStandFrom = empWorkStandFrom;
        this.empDepartment = empDepartment;
        this.empPost = empPost;
        this.empType = empType;
        this.empStructure = empStructure;
    }

    @ApiModelProperty(value="工作信息id",name="empId")
    private Long empId;
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
    @ApiModelProperty(value="职位：" +
            "   *农_林_牧_渔_水利业生产人员(\"E\"),\n" +
            "    *行政机构_企事业单位管理人员(\"B\"),\n" +
            "    *军人(\"G\"),\n" +
            "    *个体工商户(\"N\"),\n" +
            "    *商业_服务人员(\"D\"),\n" +
            "    *行政机构_企事业单位负责人或高层管理人员(\"A\"),\n" +
            "    *保安_防损(\"M\"),\n" +
            "    *专业技术人员(\"C\"),\n" +
            "    *经济_金融_法律_教育从业人员(\"J\"),\n" +
            "    *销售_中介_业务代表_促销(\"L\"),\n" +
            "    *其他从业人员(\"H\"),\n" +
            "    *工人(\"F\"),\n" +
            "    *公务员_行政机构办事人员和有关人员(\"K\");",name="empPost")
    private EmpPositionEnum empPost;
    @ApiModelProperty(value="单位行业类别",name="empType")
    private String empType;
    @ApiModelProperty(value="行业从业年限（年）",name="empObtainYear")
    private String empObtainYear;
    @ApiModelProperty(value="单位性质： " +
            "   *农_林_牧_渔业(\"A\"),\n" +
            "    *卫生_社会保障和社会福利业(\"Q\"),\n" +
            "    *电力_燃气及水的生产和供应业(\"D\"),\n" +
            "    *科学研究_技术服务业和地质勘察业(\"M\"),\n" +
            "    *批发和零售业(\"H\"),\n" +
            "    *其他(\"Z\"),\n" +
            "    *教育(\"P\"),\n" +
            "    *制造业(\"C\"),\n" +
            "    *租赁和商务服务业(\"L\"),\n" +
            "    *信息传输_计算机服务和软件业(\"G\"),\n" +
            "    *国际组织(\"T\"),\n" +
            "    *居民服务和其他服务业(\"O\"),\n" +
            "    *采掘业(\"B\"),\n" +
            "    *房地产业(\"K\"),\n" +
            "    *公共管理和社会组织(\"S\"),\n" +
            "    *交通运输_仓储和邮政业(\"F\"),\n" +
            "    *水利_环境和公共设施管理业(\"N\"),\n" +
            "    *金融业(\"J\"),\n" +
            "    *文化_体育和娱乐业(\"R\"),\n" +
            "    *建筑业(\"E\"),\n" +
            "    *住宿和餐饮业(\"I\");",name="empStructure")
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

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
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
