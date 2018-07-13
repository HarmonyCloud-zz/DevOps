package com.zhengtou.cf.trade.pojo.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 扣款定时任务vo
 */
public class ReceiptTaskVO extends PeakBaseVo {
    public ReceiptTaskVO(Long termId, String paymentTransNo, String idCard, String cardNo, String orgName, long userCretime, long receiptAmt, long
            loanPrin, TermStatusEnum termStatusEnum,String userNo) {
        this.termId = termId;
        this.paymentTransNo = paymentTransNo;
        this.idCard = idCard;
        this.cardNo = cardNo;
        this.orgName = orgName;
        this.userCretime = userCretime;
        this.receiptAmt = receiptAmt;
        this.loanPrin = loanPrin;
        this.termStatusEnum=termStatusEnum;
        this.userNo=userNo;
    }

    /**
     * 期供id
     */
    private Long termId;
    private String userNo;//用户no
    /**
     * 支付交易流水号
     */
    private String paymentTransNo;
    private String idCard;//身份证号
    private String cardNo;//卡号
    private String orgName;//机构名-产品名称
    private long userCretime;//用户注册时间
    private long receiptAmt;//扣款金额
    private long loanPrin;//当期应还本金
    private TermStatusEnum termStatusEnum;

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getPaymentTransNo() {
        return paymentTransNo;
    }

    public void setPaymentTransNo(String paymentTransNo) {
        this.paymentTransNo = paymentTransNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getUserCretime() {
        return userCretime;
    }

    public void setUserCretime(long userCretime) {
        this.userCretime = userCretime;
    }

    public long getReceiptAmt() {
        return receiptAmt;
    }

    public void setReceiptAmt(long receiptAmt) {
        this.receiptAmt = receiptAmt;
    }

    public long getLoanPrin() {
        return loanPrin;
    }

    public void setLoanPrin(long loanPrin) {
        this.loanPrin = loanPrin;
    }

    public TermStatusEnum getTermStatusEnum() {
        return termStatusEnum;
    }

    public void setTermStatusEnum(TermStatusEnum termStatusEnum) {
        this.termStatusEnum = termStatusEnum;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
