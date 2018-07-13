package com.zhengtou.cf.user.pojo.entity;

import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.user.pojo.entity.enums.AnnexStatusEnum;

import javax.persistence.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_annex")
//TODO 分表操作
public class AnnexEntity extends BaseEntity {
    //人
    @ManyToOne
    private UserEntity user;
    //附件类型
    @Enumerated(EnumType.STRING)
    private AnnexTypeEnum annexTypeEnum;
    //地址
    private String url;
    //图片编号
    private String annexCode;
    @ManyToOne
    private OrganizationEntity org;

    //附件状态
    @Enumerated(EnumType.STRING)
    private AnnexStatusEnum annexStatus;

    //对应贷款产品
    @Enumerated(EnumType.STRING)
    private ZtProductEnum ztProductEnum;

    @ManyToOne
    private TradeEntity trade;


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AnnexTypeEnum getAnnexTypeEnum() {
        return annexTypeEnum;
    }

    public void setAnnexTypeEnum(AnnexTypeEnum annexTypeEnum) {
        this.annexTypeEnum = annexTypeEnum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnnexCode() {
        return annexCode;
    }

    public void setAnnexCode(String annexCode) {
        this.annexCode = annexCode;
    }

    public OrganizationEntity getOrg() {
        return org;
    }

    public void setOrg(OrganizationEntity org) {
        this.org = org;
    }

    public AnnexStatusEnum getAnnexStatus() {
        return annexStatus;
    }

    public void setAnnexStatus(AnnexStatusEnum annexStatus) {
        this.annexStatus = annexStatus;
    }

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public TradeEntity getTrade() {
        return trade;
    }

    public void setTrade(TradeEntity trade) {
        this.trade = trade;
    }
}
