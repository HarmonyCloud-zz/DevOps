package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;

/**
 * 有短验充值请求
 * @author 葛文镇
 */
public class SmsPayRequest  extends YiBaoBaseRequest{
    public String merchantno;
    public String requestno;
    public String identityid;
    public IdentityTypeEnum identitytype;
    public String cardtop;
    public String cardlast;
    public double amount;
    public AdviceSmsTypeEnum advicesmstype=AdviceSmsTypeEnum.MESSAGE;
    //短信有效期3分钟
    public String avaliabletime="3";

    public String productname;
    public String callbackurl;
    public String requesttime;
    public String terminalid="192.168.1.51";
    public String registtime;
    public String lastloginterminalid="192.168.1.51";
    public String issetpaypwd="0";

    public String free1;
    public String free2;
    public String free3;

    public String sign;
    public String registip="192.168.1.51";

    public SmsPayRequest(String requestno, String identityid, IdentityTypeEnum identitytype, String cardtop, String cardlast, double amount, String
            productname, String requesttime, String registtime,  String issetpaypwd,String registip) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.identitytype = identitytype;
        this.cardtop = cardtop;
        this.cardlast = cardlast;
        this.amount = amount;
        this.productname = productname;
        this.requesttime = requesttime;
        this.registtime = registtime;
        this.issetpaypwd = issetpaypwd;
        this.registip=registip;
    }

    public SmsPayRequest() {
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public String getIdentityid() {
        return identityid;
    }

    public void setIdentityid(String identityid) {
        this.identityid = identityid;
    }

    public IdentityTypeEnum getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(IdentityTypeEnum identitytype) {
        this.identitytype = identitytype;
    }

    public String getCardtop() {
        return cardtop;
    }

    public void setCardtop(String cardtop) {
        this.cardtop = cardtop;
    }

    public String getCardlast() {
        return cardlast;
    }

    public void setCardlast(String cardlast) {
        this.cardlast = cardlast;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AdviceSmsTypeEnum getAdvicesmstype() {
        return advicesmstype;
    }

    public void setAdvicesmstype(AdviceSmsTypeEnum advicesmstype) {
        this.advicesmstype = advicesmstype;
    }

    public String getAvaliabletime() {
        return avaliabletime;
    }

    public void setAvaliabletime(String avaliabletime) {
        this.avaliabletime = avaliabletime;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    public String getLastloginterminalid() {
        return lastloginterminalid;
    }

    public void setLastloginterminalid(String lastloginterminalid) {
        this.lastloginterminalid = lastloginterminalid;
    }

    public String getIssetpaypwd() {
        return issetpaypwd;
    }

    public void setIssetpaypwd(String issetpaypwd) {
        this.issetpaypwd = issetpaypwd;
    }

    public String getFree1() {
        return free1;
    }

    public void setFree1(String free1) {
        this.free1 = free1;
    }

    public String getFree2() {
        return free2;
    }

    public void setFree2(String free2) {
        this.free2 = free2;
    }

    public String getFree3() {
        return free3;
    }

    public void setFree3(String free3) {
        this.free3 = free3;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRegistip() {
        return registip;
    }

    public void setRegistip(String registip) {
        this.registip = registip;
    }
}
