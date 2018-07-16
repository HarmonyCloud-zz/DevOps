package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.enums.CertificateTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.GenderEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.HouseConditionEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.MarryStatusEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialIdTypeEnum;

import javax.persistence.*;

/**
 * 个人基础信息
 */
@Entity
@Table(name = "t_person")
public class PersonalEntity extends BaseEntity {
    //姓名
    @Column(unique = true)
    private String name;
    //性别
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    //身份信息
    @Enumerated(EnumType.STRING)
    private SocialIdTypeEnum socialTypeEnum;
    //年龄
    private Integer age;
    //证件类型
    @Enumerated(EnumType.STRING)
    private CertificateTypeEnum idType=CertificateTypeEnum.身份证;
    //证件号码    参见《数据字典文档》
    @Column(unique = true)
    private String idNo;
    //证件到期日
    private String idLastDate;
    //证件长期有效    日期格式YYYY-MM-DD
    private String idLongEffective;
    //身份证地址（省code)
    private String idcardState;
    //身份证地址（市code）
    private String idcardCity;
    //身份证地址（区code）
    private String idcardZone;
    //移动电话
    private String cellphone;
    //电子邮箱
    private String email;
    //婚姻状况
    @Enumerated(EnumType.STRING)
    private MarryStatusEnum maritalStatus;
    //配偶姓名
    private String mateName;
    //配偶身份证号
    private String matePersonalId;
    //住房状况
    @Enumerated(EnumType.STRING)
    private HouseConditionEnum houseCondition;
    //住宅电话
    private String homePhone;
    //居住地址（省code）
    private String abodeState;
    //居住地址（市code）
    private String abodeCity;
    //居住地址（区/县）
    private String abodeZone;
    //详细地址
    private String abodeDetail;
    //户籍省CODE
    private String residenceState;
    //户籍市CODE
    private String residenceCity;
    //户籍区CODE
    private String residenceZone;
    //户籍地址
    private String residenceAddress;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CertificateTypeEnum getIdType() {
        return idType;
    }

    public void setIdType(CertificateTypeEnum idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdLastDate() {
        return idLastDate;
    }

    public void setIdLastDate(String idLastDate) {
        this.idLastDate = idLastDate;
    }

    public String getIdLongEffective() {
        return idLongEffective;
    }

    public void setIdLongEffective(String idLongEffective) {
        this.idLongEffective = idLongEffective;
    }

    public String getIdcardState() {
        return idcardState;
    }

    public void setIdcardState(String idcardState) {
        this.idcardState = idcardState;
    }

    public String getIdcardCity() {
        return idcardCity;
    }

    public void setIdcardCity(String idcardCity) {
        this.idcardCity = idcardCity;
    }

    public String getIdcardZone() {
        return idcardZone;
    }

    public void setIdcardZone(String idcardZone) {
        this.idcardZone = idcardZone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MarryStatusEnum getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MarryStatusEnum maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMatePersonalId() {
        return matePersonalId;
    }

    public void setMatePersonalId(String matePersonalId) {
        this.matePersonalId = matePersonalId;
    }

    public HouseConditionEnum getHouseCondition() {
        return houseCondition;
    }

    public void setHouseCondition(HouseConditionEnum houseCondition) {
        this.houseCondition = houseCondition;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getAbodeState() {
        return abodeState;
    }

    public void setAbodeState(String abodeState) {
        this.abodeState = abodeState;
    }

    public String getAbodeCity() {
        return abodeCity;
    }

    public void setAbodeCity(String abodeCity) {
        this.abodeCity = abodeCity;
    }

    public String getAbodeZone() {
        return abodeZone;
    }

    public void setAbodeZone(String abodeZone) {
        this.abodeZone = abodeZone;
    }

    public String getAbodeDetail() {
        return abodeDetail;
    }

    public void setAbodeDetail(String abodeDetail) {
        this.abodeDetail = abodeDetail;
    }

    public String getResidenceState() {
        return residenceState;
    }

    public void setResidenceState(String residenceState) {
        this.residenceState = residenceState;
    }

    public String getResidenceCity() {
        return residenceCity;
    }

    public void setResidenceCity(String residenceCity) {
        this.residenceCity = residenceCity;
    }

    public String getResidenceZone() {
        return residenceZone;
    }

    public void setResidenceZone(String residenceZone) {
        this.residenceZone = residenceZone;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public SocialIdTypeEnum getSocialTypeEnum() {
        return socialTypeEnum;
    }

    public void setSocialTypeEnum(SocialIdTypeEnum socialTypeEnum) {
        this.socialTypeEnum = socialTypeEnum;
    }
}
