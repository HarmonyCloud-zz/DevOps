package com.zhengtou.cf.trade.pojo.vo.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppBillVO extends PeakBaseVo {
    public AppBillVO(Long billId, String termNo, Long pmtDueDate, Long schdAmt, Long paidOutDate, BillStatusEnum billStatusEnum) {
        BillId = billId;
        this.termNo = termNo;
        this.pmtDueDate = TimeUtil.timeToString(pmtDueDate);
        this.schdAmt = MoneyUtil.moneyToString(schdAmt);
        this.paidOutDate = paidOutDate==null?"":TimeUtil.timeToString(paidOutDate);
        this.billStatusEnum = billStatusEnum;
    }

    private Long BillId;
    private String termNo;//当前期数
    private String pmtDueDate;//到期还款日
    private String schdAmt;//应还金额
    private String paidOutDate;//结清日期
    private BillStatusEnum billStatusEnum;//借据状态

    public Long getBillId() {
        return BillId;
    }

    public void setBillId(Long billId) {
        BillId = billId;
    }

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo;
    }

    public String getPmtDueDate() {
        return pmtDueDate;
    }

    public void setPmtDueDate(String pmtDueDate) {
        this.pmtDueDate = pmtDueDate;
    }

    public String getSchdAmt() {
        return schdAmt;
    }

    public void setSchdAmt(String schdAmt) {
        this.schdAmt = schdAmt;
    }

    public String getPaidOutDate() {
        return paidOutDate;
    }

    public void setPaidOutDate(String paidOutDate) {
        this.paidOutDate = paidOutDate;
    }

    public BillStatusEnum getBillStatusEnum() {
        return billStatusEnum;
    }

    public void setBillStatusEnum(BillStatusEnum billStatusEnum) {
        this.billStatusEnum = billStatusEnum;
    }
}
