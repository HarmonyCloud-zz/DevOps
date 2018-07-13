package com.zhengtou.cf.thirdOperator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 还款记录
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdRepaymentVO extends PeakBaseVo {
    public ThirdRepaymentVO(String thirdRepaymentId, String number, Long amountNeed, Long amountAlready, Long createTime, Long thirdCreateTime,
                            Long thirdLastPaytime, Long payTime, String payDetail, Long totalAmt, Integer
                                    overDueDay, Long overDueAmt) {
        this.thirdRepaymentId = thirdRepaymentId;
        this.number = number;
        this.amountNeed = MoneyUtil.moneyToString(amountNeed);
        this.amountAlready = MoneyUtil.moneyToString(amountAlready);
        this.createTime = TimeUtil.timeToString(createTime);
        this.thirdCreateTime = TimeUtil.timeToString(thirdCreateTime);
        this.thirdLastPaytime = thirdLastPaytime==0?"":TimeUtil.timeToString(thirdLastPaytime);
        this.payTime = TimeUtil.timeToString(payTime);
        this.payDetail = payDetail.equals("1")?"已付款":"未付款";
        this.totalAmt = MoneyUtil.moneyToString(totalAmt);
        this.overDueDay = overDueDay+"";
        this.overDueAmt = overDueAmt==0l?"":MoneyUtil.moneyToString(overDueAmt);
    }

    /**
     * 商户还款订单号
     */
    private String thirdRepaymentId;
    /**
     * 还款期数
     */
    private String number;
    /**
     * 应付金额
     */
    private String amountNeed;
    /**
     * 实付金额
     */
    private String amountAlready;
    private String createTime;
    /**
     * 三方还款订单创建时间
     */
    private String thirdCreateTime;
    /**
     * 最后还款时间
     */
    private String thirdLastPaytime;
    /**
     * 用户支付时间
     */
    private String payTime;
    /**
     * 支付状态（0未付款 1已付款）
     */
    private String payDetail;
    /**
     * 全额付款金额
     */
    private String totalAmt;
    /**
     * 逾期天数
     */
    private String overDueDay;
    /**
     * 逾期罚款
     */
    private String overDueAmt;

    public String getThirdRepaymentId() {
        return thirdRepaymentId;
    }

    public void setThirdRepaymentId(String thirdRepaymentId) {
        this.thirdRepaymentId = thirdRepaymentId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmountNeed() {
        return amountNeed;
    }

    public void setAmountNeed(String amountNeed) {
        this.amountNeed = amountNeed;
    }

    public String getAmountAlready() {
        return amountAlready;
    }

    public void setAmountAlready(String amountAlready) {
        this.amountAlready = amountAlready;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getThirdCreateTime() {
        return thirdCreateTime;
    }

    public void setThirdCreateTime(String thirdCreateTime) {
        this.thirdCreateTime = thirdCreateTime;
    }

    public String getThirdLastPaytime() {
        return thirdLastPaytime;
    }

    public void setThirdLastPaytime(String thirdLastPaytime) {
        this.thirdLastPaytime = thirdLastPaytime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getOverDueDay() {
        return overDueDay;
    }

    public void setOverDueDay(String overDueDay) {
        this.overDueDay = overDueDay;
    }

    public String getOverDueAmt() {
        return overDueAmt;
    }

    public void setOverDueAmt(String overDueAmt) {
        this.overDueAmt = overDueAmt;
    }
}
