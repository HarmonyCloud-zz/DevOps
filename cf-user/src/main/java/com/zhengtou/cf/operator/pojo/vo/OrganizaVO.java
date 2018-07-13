package com.zhengtou.cf.operator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.enums.OrganizaTypeEnum;

/**
 * 组织列表查询vo
 *
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizaVO extends PeakBaseVo {
    public OrganizaVO(long orgId, String name, String principal, String phone, String descript, OrganizaTypeEnum type, String address,
                      OrganizationEntity.OrgStatus status, String orgNo) {
        this.orgId=orgId;
        this.name = StringUtils.isBlank(name)?"":name;
        this.principal = StringUtils.isNotBlank(principal)?principal:"";
        this.phone = StringUtils.isNotBlank(phone)?phone:"";
        this.descript = StringUtils.isNotBlank(descript)?descript:"";
        this.type = type;
        this.address = StringUtils.isNotBlank(address)?address:"";
        this.status = status;
        this.orgNo=orgNo;
    }

    public OrganizaVO(Long orgId, String name) {
        this.orgId = orgId;
        this.name = StringUtils.isBlank(name)?"":name;
    }

    public OrganizaVO() {
    }

    //id
    private Long orgId;
    //组织名称
    private String name;
    //负责人
    private String principal;
    //负责人联系方式
    private String phone;
    //描述
    private String descript;
    //类型
    private OrganizaTypeEnum type;
    /**
     * 地址
     */
    private String address;
    private OrganizationEntity.OrgStatus status;
    /**
     * 系统商户编号
     */
    private String orgNo;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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
