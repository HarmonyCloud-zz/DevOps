package com.zhengtou.cf.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_risk")
public class RiskEntity extends BaseEntity {
    public RiskEntity(String name, String phone, String idNo, Long lastVerificationTime) {
        this.name = name;
        this.phone = phone;
        this.idNo = idNo;
        this.lastVerificationTime = lastVerificationTime;
    }

    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 最近验证时间
     */
    private Long lastVerificationTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Long getLastVerificationTime() {
        return lastVerificationTime;
    }

    public void setLastVerificationTime(Long lastVerificationTime) {
        this.lastVerificationTime = lastVerificationTime;
    }

}
