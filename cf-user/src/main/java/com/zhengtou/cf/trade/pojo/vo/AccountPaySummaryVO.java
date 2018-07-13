package com.zhengtou.cf.trade.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 账户付款汇总信息
 */
public class AccountPaySummaryVO extends PeakBaseVo{
    public AccountPaySummaryVO(long paymentNum, long paymentAmt, long orgId, String orgName,String orgNo) {
        this.paymentNum = paymentNum+"";
        this.paymentAmt = MoneyUtil.moneyToString(paymentAmt);
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgNo=orgNo;
    }

    /**
     * 收款条数
     */
    private String paymentNum;
    /**
     * 收款总金额
     */
    private String paymentAmt;
    /**
     * 机构id
     */
    private long orgId;
    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构编号
     */
    private String orgNo;

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(String paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
}
