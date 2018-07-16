package com.harmony.devops.user.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.user.pojo.entity.person.enums.EmpTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialRelationEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 联系人vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "郑投-联系人vo")
public class ContactReciveVO extends PeakBaseVo {
    public ContactReciveVO() {
    }
    @ApiModelProperty(value="联系人中文姓名",name="contactName")
    private String contactName;
    @ApiModelProperty(value="联系人与申请人关系",name="contactRelation")
    private SocialRelationEnum contactRelation;
    @ApiModelProperty(value="联系人移动电话",name="contactMobile")
    private String contactMobile;
    @ApiModelProperty(value="联系人工作性质",name="contactWorknature")
    private EmpTypeEnum contactWorknature;
    @ApiModelProperty(value="联系人工作名称",name="contactWorkname")
    private String contactWorkname;
    @ApiModelProperty(value="联系人地址",name="contactAddress")
    private String contactAddress;
    @ApiModelProperty(value="联系人子女数",name="contactChildNumber")
    private String contactChildNumber;

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
