package com.zhengtou.cf.thirdOperator.controller.lvyue;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class RepayItemReceiveVO extends PeakBaseVo {
    public RepayItemReceiveVO() {
    }

    private String stages_id;//分期id
    private String order_id;//订单号
    private String out_order_id;//第三方单号

    private String now_term;//当前期数
    private String payable;//应付金额
    private String payment;//支付金额
    private String repayment_time;//账单日
    private String pay_time;//支付时间

    public String getStages_id() {
        return stages_id;
    }

    public void setStages_id(String stages_id) {
        this.stages_id = stages_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOut_order_id() {
        return out_order_id;
    }

    public void setOut_order_id(String out_order_id) {
        this.out_order_id = out_order_id;
    }

    public String getNow_term() {
        return now_term;
    }

    public void setNow_term(String now_term) {
        this.now_term = now_term;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRepayment_time() {
        return repayment_time;
    }

    public void setRepayment_time(String repayment_time) {
        this.repayment_time = repayment_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }
}
