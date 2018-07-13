package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;

/**
 * 有短验绑卡请求
 * @author 葛文镇
 */
public class SmsBindCardRequest  extends YiBaoBaseRequest{
    public String merchantno;
    public String requestno;
    public String identityid;
    public IdentityTypeEnum identitytype=IdentityTypeEnum.ID_CARD;
    public String cardno;
    public String idcardno;
    public String idcardtype="ID";
    public String username;
    public String phone;
    public AdviceSmsTypeEnum advicesmstype=AdviceSmsTypeEnum.MESSAGE;
    public String avaliabletime;
    public String callbackurl;
    public String requesttime;
    public String sign;

    public SmsBindCardRequest(String requestno, String identityid,  String cardno, String
            idcardno, String username, String phone, String requesttime) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.cardno = cardno;
        this.idcardno = idcardno;
        this.username = username;
        this.phone = phone;
        this.requesttime = requesttime;
        this.avaliabletime="3";
    }

    public SmsBindCardRequest() {
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

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
