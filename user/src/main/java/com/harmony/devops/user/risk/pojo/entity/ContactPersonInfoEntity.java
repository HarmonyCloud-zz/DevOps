package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_risk_contactPerson")
public class ContactPersonInfoEntity extends BaseEntity{
    /**
     * userId
     */
    @ManyToOne
    private ConsumerUserEntity consumerUser;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人手机
     */
    private String contactPhone;
    /**
     * 联系人关系
     */
    private String contactRelation;


    public ConsumerUserEntity getConsumerUser() {
        return consumerUser;
    }

    public void setConsumerUser(ConsumerUserEntity consumerUser) {
        this.consumerUser = consumerUser;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactRelation() {
        return contactRelation;
    }

    public void setContactRelation(String contactRelation) {
        this.contactRelation = contactRelation;
    }
}
