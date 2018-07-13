package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;
import com.zhengtou.cf.common.utils.DateUtils;

/**
 * 无短验充值请求
 * @author 葛文镇
 */
public class NoSmsPayRequest extends YiBaoBaseRequest{
    public String merchantno;
    public String requestno;
    public String identityid;
    public IdentityTypeEnum identitytype=IdentityTypeEnum.ID_CARD;
    public String requesttime= DateUtils.getSysDateTimeString();
    public double amount;
    public String productname;
    public String avaliabletime="3";
    public String cardtop;
    public String cardlast;
    public String callbackurl;
    public String terminalid="192.168.1.51";
    public String registtime;
    public String registip;
    public String lastloginip;
    public String lastlogintime;
    public String lastloginterminalid="192.168.1.51";
    public String issetpaypwd="0";

    //备注
    public String free1;
    public String free2;
    public String free3;

    public String sign;

    public NoSmsPayRequest(String requestno, String identityid, IdentityTypeEnum identitytype, String requesttime, double amount, String
            productname, String cardtop, String cardlast, String registtime, String issetpaypwd,String registip) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.identitytype = identitytype;
        this.requesttime = requesttime;
        this.amount = amount;
        this.productname = productname;
        this.cardtop = cardtop;
        this.cardlast = cardlast;
        this.registtime = registtime;
        this.issetpaypwd = issetpaypwd;
        this.registip=registip;
    }

    public NoSmsPayRequest() {
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

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getAvaliabletime() {
        return avaliabletime;
    }

    public void setAvaliabletime(String avaliabletime) {
        this.avaliabletime = avaliabletime;
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

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
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

    public String getRegistip() {
        return registip;
    }

    public void setRegistip(String registip) {
        this.registip = registip;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
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
}
