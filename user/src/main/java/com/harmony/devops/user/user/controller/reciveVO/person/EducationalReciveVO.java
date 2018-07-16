package com.harmony.devops.user.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.user.pojo.entity.person.enums.EducationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 教育信息VO
 *
 * @author 葛文镇
 */
@ApiModel(discriminator = "郑投-教育信息VO")
public class EducationalReciveVO extends PeakBaseVo {
    public EducationalReciveVO() {
    }
    @ApiModelProperty(value="教育状况",name="qualification")
    private EducationEnum qualification;
    @ApiModelProperty(value="学制",name="schoolLength")
    private String schoolLength;
    @ApiModelProperty(value="学校类型",name="schoolType")
    private String schoolType;
    @ApiModelProperty(value="学校名称",name="schoolName")
    private String schoolName;
    @ApiModelProperty(value="毕业院校",name="schoolGraduated")
    private String schoolGraduated;
    @ApiModelProperty(value="入学时间",name="schoolStartDate")
    private String schoolStartDate;
    @ApiModelProperty(value="毕业时间",name="schoolGraduationTime")
    private String schoolGraduationTime;

    public EducationEnum getQualification() {
        return qualification;
    }

    public void setQualification(EducationEnum qualification) {
        this.qualification = qualification;
    }

    public String getSchoolLength() {
        return schoolLength;
    }

    public void setSchoolLength(String schoolLength) {
        this.schoolLength = schoolLength;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolGraduated() {
        return schoolGraduated;
    }

    public void setSchoolGraduated(String schoolGraduated) {
        this.schoolGraduated = schoolGraduated;
    }

    public String getSchoolStartDate() {
        return schoolStartDate;
    }

    public void setSchoolStartDate(String schoolStartDate) {
        this.schoolStartDate = schoolStartDate;
    }

    public String getSchoolGraduationTime() {
        return schoolGraduationTime;
    }

    public void setSchoolGraduationTime(String schoolGraduationTime) {
        this.schoolGraduationTime = schoolGraduationTime;
    }
}
