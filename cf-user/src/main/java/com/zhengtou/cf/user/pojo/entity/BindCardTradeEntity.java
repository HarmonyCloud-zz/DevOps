package com.zhengtou.cf.user.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import com.zhengtou.cf.user.pojo.entity.enums.BindCardStatusEnum;

import javax.persistence.*;

/**
 * 绑卡记录订单
 * @author 葛文镇
 */
@Entity
@Table(name = "t_trade_bindcard")
public class BindCardTradeEntity extends BaseEntity{
    //绑卡流水号
    private String requestno;
    //用户
    @ManyToOne
    private ConsumerUserEntity consumerUser;
    //卡
    @ManyToOne
    private BankCardEntity bankCard;
    //请求时间
    private String requesttime;
    /**
     * 绑卡状态
     */
    @Enumerated(EnumType.STRING)
    private BindCardStatusEnum status;

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public ConsumerUserEntity getConsumerUser() {
        return consumerUser;
    }

    public void setConsumerUser(ConsumerUserEntity consumerUser) {
        this.consumerUser = consumerUser;
    }

    public BankCardEntity getBankCard() {
        return bankCard;
    }

    public void setBankCard(BankCardEntity bankCard) {
        this.bankCard = bankCard;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public BindCardStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BindCardStatusEnum status) {
        this.status = status;
    }
}
