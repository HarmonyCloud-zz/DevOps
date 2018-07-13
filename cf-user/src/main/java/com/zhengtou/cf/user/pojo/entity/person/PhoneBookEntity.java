package com.zhengtou.cf.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 通讯录联系人信息
 */
@Entity
@Table(name = "t_person_phonebook")
public class PhoneBookEntity extends BaseEntity {
    @ManyToOne
    private PersonalEntity personal;
    //姓名
    private String name;
    //手机号
    private String mobile;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
