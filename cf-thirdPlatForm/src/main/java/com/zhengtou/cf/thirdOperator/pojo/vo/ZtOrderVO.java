package com.zhengtou.cf.thirdOperator.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class ZtOrderVO extends PeakBaseVo {
    public ZtOrderVO(long createTime, Long id, String thirdOrderId, String goodId, Long thirdCreateTime, Long lastRepayTime, Integer
            repayTerm, String thirdOrderNo, long orderAmt, String orderTerm, Long signTime, String note, SignStatusEnum signStatus) {
        this.createTime = TimeUtil.timeToStringYMD(createTime);
        this.id = id;
        this.thirdOrderId = thirdOrderId;
        this.goodId = goodId;
        this.thirdCreateTime = TimeUtil.timeToString(thirdCreateTime);
        this.lastRepayTime = lastRepayTime == null ? "" : TimeUtil.timeToString(lastRepayTime);
        if (lastRepayTime != null && lastRepayTime > TimeUtil.dateToLong(TimeUtil.timeToStringYMD(TimeUtil.dateToLongAddN(0))) && lastRepayTime <=
                TimeUtil.dateToLong(TimeUtil.timeToStringYMD(TimeUtil.dateToLongAddN(1)))) {
            this.hasRepayDay = true;
        }
        this.repayTerm = repayTerm == null ? "" : repayTerm + "";
        this.thirdOrderNo = thirdOrderNo;
        this.orderAmt = MoneyUtil.moneyToString(orderAmt);
        this.orderTerm = orderTerm;
        this.signTime = signTime == null ? "" : TimeUtil.timeToString(signTime);
        this.note = note;
        this.signStatus = signStatus;
    }

    //创建日期
    private String createTime;
    private Long id;
    private String thirdOrderId;
    private String goodId;
    private String thirdCreateTime;
    //最新还款时间
    private String lastRepayTime;
    //当天是否有还款
    private Boolean hasRepayDay = false;
    //当前还款期数
    private String repayTerm;
    /**
     * 三方订单号
     */
    private String thirdOrderNo;
    //订单金额
    private String orderAmt;
    //发标日期
    private String signTime;
    //备注
    private String note;
    //订单发标状态
    private SignStatusEnum signStatus;
    /**
     * 订单期数
     */
    private String orderTerm;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getThirdCreateTime() {
        return thirdCreateTime;
    }

    public void setThirdCreateTime(String thirdCreateTime) {
        this.thirdCreateTime = thirdCreateTime;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(String lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public String getRepayTerm() {
        return repayTerm;
    }

    public void setRepayTerm(String repayTerm) {
        this.repayTerm = repayTerm;
    }

    public String getOrderTerm() {
        return orderTerm;
    }

    public void setOrderTerm(String orderTerm) {
        this.orderTerm = orderTerm;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SignStatusEnum getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(SignStatusEnum signStatus) {
        this.signStatus = signStatus;
    }

    public Boolean getHasRepayDay() {
        return hasRepayDay;
    }

    public void setHasRepayDay(Boolean hasRepayDay) {
        this.hasRepayDay = hasRepayDay;
    }
}
