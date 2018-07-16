package com.harmony.devops.user.operator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.operator.pojo.entity.StoreEntity;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreVO extends PeakBaseVo{
    public StoreVO() {
    }

    public StoreVO(Long storeId, String storeName) {
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public StoreVO(long storeId, String storeName, String telephone, String contactName, String address, StoreEntity.StoreStatus status, String
            storeNo) {
        this.storeId=storeId;
        this.storeName = StringUtils.isNotBlank(storeName)?storeName:"";
        this.telephone = StringUtils.isNotBlank(telephone)?telephone:"";
        this.contactName = StringUtils.isNotBlank(contactName)?contactName:"";
        this.address = StringUtils.isNotBlank(address)?address:"";
        this.status = status;
        this.storeNo = storeNo;
    }

    private Long storeId;
    private String storeName;
    private String telephone;
    private String contactName;
    private String address;
    private StoreEntity.StoreStatus status;
    private String storeNo ;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public StoreEntity.StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreEntity.StoreStatus status) {
        this.status = status;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
}
