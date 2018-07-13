package com.zhengtou.cf.operator.controller.vo;

import com.zhengtou.cf.common.annotation.validator.Name;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.operator.pojo.entity.enums.OrganizaTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class ReciveOrganizaVO {
    public ReciveOrganizaVO() {
    }

    //组织名称
    @Name
    @ApiModelProperty(value="组织名称（不可为空，支持中英文）",name="name")
    private String name;
    //负责人
    @Name
    @ApiModelProperty(value="负责人姓名（不可为空，支持中英文）",name="principal")
    private String principal;
    //负责人联系方式
    @Phone
    @ApiModelProperty(value="负责人联系方式（不可为空，手机号格式）",name="phone")
    private String phone;
    //描述
    @ApiModelProperty(value="企业描述",name="descript")
    private String descript;
    //类型
    @ApiModelProperty(value="企业类型（默认为：合作机构）",name="type")
    private OrganizaTypeEnum type;
    /**
     * 地址
     */
    @ApiModelProperty(value="企业地址",name="address")
    private String address;
    /**
     * 社会信用码
     */
    @ApiModelProperty(value="企业社会信用码",name="socialCreditCode")
    private String socialCreditCode;

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

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }
}
