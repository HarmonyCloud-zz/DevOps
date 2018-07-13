package com.zhengtou.cf.trade.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 账户收款汇总信息
 */
public class AccountReceiptSummaryVO extends PeakBaseVo{
    public AccountReceiptSummaryVO(long receiptNum, long receiptAmt, long orgId, String orgName,String orgNo) {
        this.receiptNum = receiptNum+"";
        this.receiptAmt = MoneyUtil.moneyToString(receiptAmt);
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgNo=orgNo;
    }

    /**
     * 收款条数
     */
    private String receiptNum;
    /**
     * 收款总金额
     */
    private String receiptAmt;
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

    public String getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(String receiptNum) {
        this.receiptNum = receiptNum;
    }

    public String getReceiptAmt() {
        return receiptAmt;
    }

    public void setReceiptAmt(String receiptAmt) {
        this.receiptAmt = receiptAmt;
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
