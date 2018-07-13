package com.zhengtou.cf.user.pojo.entity;

import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.enums.BindCardStatusEnum;
import com.zhengtou.cf.user.pojo.entity.enums.ChannelTypeEnum;

import javax.persistence.*;

/**
 * 银行卡信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_user_bankcard")
public class BankCardEntity extends BaseEntity{
    public BankCardEntity() {
    }
    /**
     * userId
     */
    @ManyToOne
    private UserEntity user;
    /**
     * 银行卡号
     */
    @Column(unique = true)
    private String cardNo;
    /**
     * 行号
     */
    private String bankCode;
    /**
     * 银行名称
     */
    @Enumerated(EnumType.STRING)
    private BankCodeEnum bankName;
    /**
     * 是否默认收款卡
     */
    private Boolean isDefault=false;
    /**
     * 是否默认还款卡
     */
    private Boolean isRepayDefault=false;
    /**
     * 绑卡状态
     */
    @Enumerated(EnumType.STRING)
    private BindCardStatusEnum status;
    /**
     * 通道类型
     */
    @Enumerated(EnumType.STRING)
    private ChannelTypeEnum channelType;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public BankCodeEnum getBankName() {
        return bankName;
    }

    public void setBankName(BankCodeEnum bankName) {
        this.bankName = bankName;
    }

    public Boolean getIsRepayDefault() {
        return isRepayDefault;
    }

    public void setIsRepayDefault(Boolean repayDefault) {
        isRepayDefault = repayDefault;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public BindCardStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BindCardStatusEnum status) {
        this.status = status;
    }

    public ChannelTypeEnum getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelTypeEnum channelType) {
        this.channelType = channelType;
    }
}
