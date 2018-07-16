package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "t_risk_job")
public class JobInfoEntity extends BaseEntity{
    /**
     * userId
     */
    @ManyToOne
    private ConsumerUserEntity consumerUser;
    /**
     * 从事行业
     */
    private String industry;
    /**
     * 从事职业
     */
    private String career;
    /**
     * 单位名称
     */
    private String companyName;
    /**
     * 单位所在地
     */
    private String companyLocal;
    /**
     * 单位地址
     */
    private String companyAddress;
    /**
     * 单位电话
     */
    private String companyPhone;
    /**
     * 年收入
     */
    private String income;

    public ConsumerUserEntity getConsumerUser() {
        return consumerUser;
    }

    public void setConsumerUser(ConsumerUserEntity consumerUser) {
        this.consumerUser = consumerUser;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocal() {
        return companyLocal;
    }

    public void setCompanyLocal(String companyLocal) {
        this.companyLocal = companyLocal;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
