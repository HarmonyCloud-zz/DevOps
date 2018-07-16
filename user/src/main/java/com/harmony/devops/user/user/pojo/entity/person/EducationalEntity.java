package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.person.enums.EducationEnum;

import javax.persistence.*;

/**
 * 教育信息
 */
@Entity
@Table(name = "t_person_education")
public class EducationalEntity extends BaseEntity {
    @ManyToOne
    private PersonalEntity personal;
    @Enumerated(EnumType.STRING)
    private EducationEnum qualification;
    //学制
    private String schoolLength;
    @Enumerated(EnumType.STRING)
    private EducationEnum schoolEducationType;
    //学校类型
    private String schoolType;
    //学校名称
    private String schoolName;
    //毕业院校
    private String schoolGraduated;
    //入学时间    YYYY-MM-DD
    private String schoolStartDate;
    //毕业时间    YYYY-MM-DD
    private String schoolGraduationTime;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
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
