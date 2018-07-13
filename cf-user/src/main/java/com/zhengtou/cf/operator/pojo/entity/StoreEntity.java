package com.zhengtou.cf.operator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.*;

/**
 * 门店实体
 * @author 葛文镇
 */
@Entity
@Table(name = "t_org_store")
public class StoreEntity extends BaseEntity{
    public StoreEntity() {
    }

    public StoreEntity(String storeName, String telephone, String contactName, String address, StoreStatus status, String storeNo) {
        this.storeName = storeName;
        this.telephone = telephone;
        this.contactName = contactName;
        this.address = address;
        this.status = status;
        this.storeNo = storeNo;
    }

    //上层机构
    @ManyToOne
    private OrganizationEntity organiza;

    /**
     * 门店名称
     */
    @Column(length = 32,unique = true)
    private String storeName;

    /**
     * 联系电话
     */
    @Column(length = 32)
    private String telephone;
    /**
     * 联系人
     */
    @Column(length = 32)
    private String contactName;
    /**
     * 地址
     */
    @Column(length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    /**
     * 门店系统编号
     */
    private String storeNo;

    public enum StoreStatus {
        启用, 废弃, 未开通;
        public String toPrintString() {
            return this.name();
        }
    }

    public OrganizationEntity getOrganiza() {
        return organiza;
    }

    public void setOrganiza(OrganizationEntity organiza) {
        this.organiza = organiza;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreStatus status) {
        this.status = status;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
}
