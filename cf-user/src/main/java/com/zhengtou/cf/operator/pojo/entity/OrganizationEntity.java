package com.zhengtou.cf.operator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.operator.pojo.entity.enums.OrganizaTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "t_org")
public class OrganizationEntity extends BaseEntity {
    //组织名称
    @Column(unique = true)
    private String name;
    //负责人
    private String principal;
    //负责人联系方式
    private String phone;
    //描述
    private String descript;
    //类型
    @Enumerated(EnumType.STRING)
    private OrganizaTypeEnum type=OrganizaTypeEnum.合作结构;
    /**
     * 地址
     */
    @Column(length = 255)
    private String address;
    /**
     * 加密key
     */
    private String partnerKey;
    /**
     * 社会信用码
     */
    private String socialCreditCode;

    @Enumerated(EnumType.STRING)
    private OrgStatus status=OrgStatus.启用;

    /**
     * 商户系统编号
     */
    private String orgNo;

    public enum OrgStatus {
        启用, 废弃, 未开通;
        public String toPrintString() {
            return this.name();
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public OrganizaTypeEnum getType() {
        return type;
    }

    public void setType(OrganizaTypeEnum type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrgStatus getStatus() {
        return status;
    }

    public void setStatus(OrgStatus status) {
        this.status = status;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }
}
