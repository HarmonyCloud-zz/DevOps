package com.zhengtou.cf.operator.controller.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.operator.pojo.entity.StoreEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class FetchStoreVO extends PeakBaseVo{
    public FetchStoreVO() {
    }
    @ApiModelProperty(value="机构id",name="orgId")
    private Long orgId;
    @ApiModelProperty(value="门店名称|模糊",name="storeName")
    private String storeName;
    @ApiModelProperty(value="门店联系电话",name="telephone")
    private String telephone;
    @ApiModelProperty(value="门店联系人",name="contactName")
    private String contactName;
    @ApiModelProperty(value="门店地址",name="address")
    private String address;
    @ApiModelProperty(value="门店状态",name="status")
    private StoreEntity.StoreStatus status;
    @ApiModelProperty(value="门店编号",name="storeNo")
    private String storeNo ;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
