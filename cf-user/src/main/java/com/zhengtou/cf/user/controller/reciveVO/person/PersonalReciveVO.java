package com.zhengtou.cf.user.controller.reciveVO.person;

import com.zhengtou.cf.common.annotation.validator.IdNo;
import com.zhengtou.cf.common.annotation.validator.Name;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 个人基础信息
 */
@ApiModel(discriminator = "郑投-个人基础信息vo")
public class PersonalReciveVO extends PeakBaseVo {
    public PersonalReciveVO() {
    }
    //姓名
    @ApiModelProperty(value="真实姓名",name="name")
    @Name
    private String name;
    //性别
    @ApiModelProperty(value="性别",name="gender")
    private GenderEnum gender;
    //身份信息
    @ApiModelProperty(value="社会身份",name="socialTypeEnum")
    private SocialIdTypeEnum socialTypeEnum;
    //年龄
    @ApiModelProperty(value="年龄",name="age")
    private int age;
    //证件号码    参见《数据字典文档》
    @ApiModelProperty(value="身份证号",name="idNo")
    @IdNo
    private String idNo;
    //婚姻状况
    @ApiModelProperty(value="婚姻状况",name="maritalStatus")
    private MarryStatusEnum maritalStatus;
    //住房状况
    @ApiModelProperty(value="住房状况",name="houseCondition")
    private HouseConditionEnum houseCondition;
    @ApiModelProperty(value="常驻地址",name="address")
    private String address;
    @ApiModelProperty(value="最高学历",name="educationEnum")
    private EducationEnum educationEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public SocialIdTypeEnum getSocialTypeEnum() {
        return socialTypeEnum;
    }

    public void setSocialTypeEnum(SocialIdTypeEnum socialTypeEnum) {
        this.socialTypeEnum = socialTypeEnum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public MarryStatusEnum getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MarryStatusEnum maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public HouseConditionEnum getHouseCondition() {
        return houseCondition;
    }

    public void setHouseCondition(HouseConditionEnum houseCondition) {
        this.houseCondition = houseCondition;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EducationEnum getEducationEnum() {
        return educationEnum;
    }

    public void setEducationEnum(EducationEnum educationEnum) {
        this.educationEnum = educationEnum;
    }
}
