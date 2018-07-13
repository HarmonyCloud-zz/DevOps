package com.zhengtou.cf.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.person.enums.EmpTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.SocialRelationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系人vo
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-联系人vo")
public class ContactVO extends PeakBaseVo {
    public ContactVO() {
    }

    //展示
    public ContactVO(long contactId,String contactName, SocialRelationEnum contactRelation, String contactMobile) {
        this.contactId=contactId;
        this.contactName = contactName;
        this.contactRelation = contactRelation;
        this.contactMobile = contactMobile;
    }

    @ApiModelProperty(value="联系人信息id",name="contactId")
    private Long contactId;
    @ApiModelProperty(value="联系人中文姓名",name="contactName")
    private String contactName;
    @ApiModelProperty(value="联系人与申请人关系：" +
            "   *母亲(\"M\"),\n" +
            "    *朋友(\"Y\"),\n" +
            "    *本人(\"I\"),\n" +
            "    *同事(\"W\"),\n" +
            "    *同学(\"T\"),\n" +
            "    *其它(\"O\"),\n" +
            "    *配偶(\"C\"),\n" +
            "    *父亲(\"F\"),\n" +
            "    *子女(\"H\"),\n" +
            "    *兄弟(\"B\"),\n" +
            "    *姐妹(\"S\");",name="contactRelation")
    private SocialRelationEnum contactRelation;
    @ApiModelProperty(value="联系人移动电话",name="contactMobile")
    private String contactMobile;
    @ApiModelProperty(value="联系人工作性质:" +
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
            "   * 公共管理和社会组织(\"S\"),\n" +
            "    *交通运输_仓储和邮政业(\"F\"),\n" +
            "    *水利_环境和公共设施管理业(\"N\"),\n" +
            "    *金融业(\"J\"),\n" +
            "    *文化_体育和娱乐业(\"R\"),\n" +
            "    *建筑业(\"E\"),\n" +
            "    *住宿和餐饮业(\"I\");",name="contactWorknature")
    private EmpTypeEnum contactWorknature;
    @ApiModelProperty(value="联系人工作名称",name="contactWorkname")
    private String contactWorkname;
    @ApiModelProperty(value="联系人地址",name="contactAddress")
    private String contactAddress;
    @ApiModelProperty(value="联系人子女数",name="contactChildNumber")
    private String contactChildNumber;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public SocialRelationEnum getContactRelation() {
        return contactRelation;
    }

    public void setContactRelation(SocialRelationEnum contactRelation) {
        this.contactRelation = contactRelation;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public EmpTypeEnum getContactWorknature() {
        return contactWorknature;
    }

    public void setContactWorknature(EmpTypeEnum contactWorknature) {
        this.contactWorknature = contactWorknature;
    }

    public String getContactWorkname() {
        return contactWorkname;
    }

    public void setContactWorkname(String contactWorkname) {
        this.contactWorkname = contactWorkname;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getContactChildNumber() {
        return contactChildNumber;
    }

    public void setContactChildNumber(String contactChildNumber) {
        this.contactChildNumber = contactChildNumber;
    }
}
