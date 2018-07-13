package com.zhengtou.cf.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.enums.CertificateTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.GenderEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.HouseConditionEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.MarryStatusEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.SocialIdTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 个人基础信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-个人基础信息")
public class PersonalVO extends PeakBaseVo {
    public PersonalVO() {
    }

    //展示用
    public PersonalVO(long personalId,String name, GenderEnum gender, Integer age, CertificateTypeEnum idType, String idNo, String cellphone,HouseConditionEnum houseCondition) {
        this.personalId=personalId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.idType = idType;
        this.idNo = idNo;
        this.cellphone = cellphone;
        this.houseCondition=houseCondition;
    }

    @ApiModelProperty(value="用户信息id",name="personalId")
    private Long personalId;
    @ApiModelProperty(value="用户真实姓名",name="name")
    private String name;
    @ApiModelProperty(value="性别：" +
            "   *男(\"M\"),\n" +
            "    *女(\"F\");",name="gender")
    private GenderEnum gender;
    @ApiModelProperty(value="身份信息：" +
            "   *学生(\"SI01\"),\n" +
            "    *在职人员(\"SI02\"),\n" +
            "    *自由职业(\"SI03\"),\n" +
            "    *企业负责人(\"SI04\"),\n" +
            "    *无业(\"SI05\"),\n" +
            "    *退休(\"SI06\");",name="socialTypeEnum")
    private SocialIdTypeEnum socialTypeEnum;
    @ApiModelProperty(value="年龄",name="age")
    private Integer age;
    @ApiModelProperty(value="证件类型:" +
            "   *身份证(\"I\"),\n" +
            "    *临时身份证(\"T\"),\n" +
            "    *军官证_士兵证(\"S\"),\n" +
            "    *护照(\"P\"),\n" +
            "    *营业执照(\"L\"),\n" +
            "    *其他有效证件(\"O\"),\n" +
            "    *户口簿(\"R\"),\n" +
            "    *港澳居民来往内地通行证(\"H\"),\n" +
            "    *台湾同胞来往内地通行证(\"W\"),\n" +
            "    *外国人居留证(\"F\"),\n" +
            "    *警官证(\"C\");",name="idType")
    private CertificateTypeEnum idType;
    @ApiModelProperty(value="证件号码",name="idNo")
    private String idNo;
    @ApiModelProperty(value="证件到期日",name="idLastDate")
    private String idLastDate;
    //证件长期有效    日期格式YYYY-MM-DD
    @ApiModelProperty(value="证件长期有效",name="idLongEffective")
    private String idLongEffective;
    @ApiModelProperty(value="身份证地址（省code)",name="idcardState")
    private String idcardState;
    @ApiModelProperty(value="身份证地址（市code)",name="idcardCity")
    private String idcardCity;
    @ApiModelProperty(value="身份证地址（区code)",name="idcardZone")
    private String idcardZone;
    @ApiModelProperty(value="移动电话",name="cellphone")
    private String cellphone;
    @ApiModelProperty(value="电子邮箱",name="email")
    private String email;
    @ApiModelProperty(value="婚姻状况：" +
            "   *已婚(\"C\"),\n" +
            "    *未婚(\"S\"),\n" +
            "    *其他(\"O\")",name="maritalStatus")
    private MarryStatusEnum maritalStatus;
    @ApiModelProperty(value="配偶姓名",name="mateName")
    private String mateName;
    @ApiModelProperty(value="配偶身份证号",name="matePersonalId")
    private String matePersonalId;
    @ApiModelProperty(value="住房状况：" +
            "   *亲属楼宇(\"D\"),\n" +
            "    *自置无按揭(\"A\"),\n" +
            "    *自置有按揭(\"B\"),\n" +
            "    *租房(\"C\"),\n" +
            "    *集体宿舍(\"E\"),\n" +
            "    *其他(\"Z\");",name="houseCondition")
    private HouseConditionEnum houseCondition;
    @ApiModelProperty(value="住宅电话",name="homePhone")
    private String homePhone;
    @ApiModelProperty(value="居住地址（省code）",name="abodeState")
    private String abodeState;
    @ApiModelProperty(value="居住地址（市code）",name="abodeCity")
    private String abodeCity;
    @ApiModelProperty(value="居住地址（区/县）",name="abodeZone")
    private String abodeZone;
    @ApiModelProperty(value="详细地址",name="abodeDetail")
    private String abodeDetail;
    @ApiModelProperty(value="户籍省CODE",name="residenceState")
    private String residenceState;
    @ApiModelProperty(value="户籍市CODE",name="residenceCity")
    private String residenceCity;
    @ApiModelProperty(value="户籍区CODE",name="residenceZone")
    private String residenceZone;
    @ApiModelProperty(value="户籍地址",name="residenceAddress")
    private String residenceAddress;

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

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
