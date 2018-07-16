package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.person.enums.EmpTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialRelationEnum;

import javax.persistence.*;

/**
 * 联系人信息
 */
@Entity
@Table(name = "t_person_contact")
public class ContactEntity extends BaseEntity {
    @ManyToOne
    private PersonalEntity personal;
    //联系人中文姓名
    private String contactName;
    //联系人与申请人关系
    @Enumerated(EnumType.STRING)
    private SocialRelationEnum contactRelation;
    //联系人移动电话
    private String contactMobile;
    //联系人工作性质
    @Enumerated(EnumType.STRING)
    private EmpTypeEnum contactWorknature;
    //联系人工作名称
    private String contactWorkname;
    //联系人地址
    private String contactAddress;
    //联系人子女数
    private String contactChildNumber;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
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
