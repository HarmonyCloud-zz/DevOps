package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Entity
@Table(name = "t_third_credit")
public class ThirdCreditScore extends BaseEntity{
    @ManyToOne
    private ThirdUserEntity thirdUser;

    /**
     * 信用分
     */
    private String score;
    /**
     * 订单来源
     */
    private String orderComeForm;

    public ThirdUserEntity getThirdUser() {
        return thirdUser;
    }

    public void setThirdUser(ThirdUserEntity thirdUser) {
        this.thirdUser = thirdUser;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOrderComeForm() {
        return orderComeForm;
    }

    public void setOrderComeForm(String orderComeForm) {
        this.orderComeForm = orderComeForm;
    }
}
