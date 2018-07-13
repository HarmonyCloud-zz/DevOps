package com.zhengtou.cf.operator.controller.vo;

import com.zhengtou.cf.common.annotation.validator.Name;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.enums.OrganizaTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class FetchOrganizaVO extends PeakBaseVo{
    public FetchOrganizaVO() {
    }

    //组织名称
    @Name
    @ApiModelProperty(value="组织名称（不可为空，支持中英文）",name="name")
    private String name;
    @Name
    @ApiModelProperty(value="组织名称（不可为空，支持中英文）",name="name")
    private String principal;
    @Phone
    @ApiModelProperty(value="负责人联系方式（不可为空，手机号格式）",name="phone")
    private String phone;
    @ApiModelProperty(value="企业描述",name="descript")
    private String descript;
    @ApiModelProperty(value="企业类型（默认为：合作机构）",name="type")
    private OrganizaTypeEnum type;
    @ApiModelProperty(value="企业地址",name="address")
    private String address;
    @ApiModelProperty(value="企业状态",name="status")
    private OrganizationEntity.OrgStatus status;
    @ApiModelProperty(value="企业编号",name="orgNo")
    private String orgNo;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public OrganizationEntity.OrgStatus getStatus() {
        return status;
    }

    public void setStatus(OrganizationEntity.OrgStatus status) {
        this.status = status;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
}
