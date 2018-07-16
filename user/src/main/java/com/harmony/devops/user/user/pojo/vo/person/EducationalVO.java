package com.harmony.devops.user.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.user.pojo.entity.person.enums.EducationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 教育信息VO
 *
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-教育信息VO")
public class EducationalVO extends PeakBaseVo {
    public EducationalVO() {
    }

    //展示
    public EducationalVO(long educationalId,EducationEnum qualification, String schoolLength, EducationEnum schoolEducationType, String schoolGraduated, String
            schoolStartDate, String schoolGraduationTime) {
        this.educationalId=educationalId;
        this.qualification = qualification;
        this.schoolLength = schoolLength;
        this.schoolEducationType = schoolEducationType;
        this.schoolGraduated = schoolGraduated;
        this.schoolStartDate = schoolStartDate;
        this.schoolGraduationTime = schoolGraduationTime;
    }

    @ApiModelProperty(value="教育信息id",name="educationalId")
    private Long educationalId;
    //    参见《数据字典文档》
    @ApiModelProperty(value="教育状况：" +
            "   *大学本科(\"C\"),\n" +
            "    *大学专科专科学校(\"D\"),\n" +
            "    *高中_中专_技校(\"E\"),\n" +
            "    *初中(\"F\"),\n" +
            "    *初中以下(\"G\"),\n" +
            "    *博士及以上(\"A\"),\n" +
            "    *硕士(\"B\");",name="qualification")
    private EducationEnum qualification;
    @ApiModelProperty(value="学制",name="schoolLength")
    private String schoolLength;
    //    参见《数据字典文档》
    @ApiModelProperty(value="学历类型：" +
            "   *大学本科(\"C\"),\n" +
            "    *大学专科专科学校(\"D\"),\n" +
            "    *高中_中专_技校(\"E\"),\n" +
            "    *初中(\"F\"),\n" +
            "    *初中以下(\"G\"),\n" +
            "    *博士及以上(\"A\"),\n" +
            "    *硕士(\"B\");",name="schoolEducationType")
    private EducationEnum schoolEducationType;
    @ApiModelProperty(value="学校类型",name="schoolType")
    private String schoolType;
    @ApiModelProperty(value="学校名称",name="schoolName")
    private String schoolName;
    @ApiModelProperty(value="毕业院校",name="schoolGraduated")
    private String schoolGraduated;
    @ApiModelProperty(value="入学时间    YYYY-MM-DD",name="schoolStartDate")
    private String schoolStartDate;
    @ApiModelProperty(value="毕业时间    YYYY-MM-DD",name="schoolGraduationTime")
    private String schoolGraduationTime;

    public Long getEducationalId() {
        return educationalId;
    }

    public void setEducationalId(Long educationalId) {
        this.educationalId = educationalId;
    }

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

    public EducationEnum getSchoolEducationType() {
        return schoolEducationType;
    }

    public void setSchoolEducationType(EducationEnum schoolEducationType) {
        this.schoolEducationType = schoolEducationType;
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
