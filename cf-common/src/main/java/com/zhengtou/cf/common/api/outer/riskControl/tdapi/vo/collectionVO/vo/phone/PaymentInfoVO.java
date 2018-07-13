package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 缴费信息
 *
 * @author 葛文镇
 */
public class PaymentInfoVO {
    //日期
    private String pay_date;
    //金额
    private String pay_fee;
    //缴费渠道
    private String pay_channel;
    //缴费方式
    private String pay_type;

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(String pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
}
