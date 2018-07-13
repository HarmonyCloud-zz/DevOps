package com.zhengtou.cf.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.person.enums.SocialAccountEnum;

import javax.persistence.*;

/**
 * 社交账号
 */
@Entity
@Table(name = "t_person_socialaccount")
public class SocialAccountEntity extends BaseEntity {
    @ManyToOne
    private PersonalEntity personal;
    //社交账号类型
    @Enumerated(EnumType.STRING)
    private SocialAccountEnum socialAccountType;
    //社交账号
    private String socialAccountNo;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
    }

    public SocialAccountEnum getSocialAccountType() {
        return socialAccountType;
    }

    public void setSocialAccountType(SocialAccountEnum socialAccountType) {
        this.socialAccountType = socialAccountType;
    }

    public String getSocialAccountNo() {
        return socialAccountNo;
    }

    public void setSocialAccountNo(String socialAccountNo) {
        this.socialAccountNo = socialAccountNo;
    }
}
