package com.zhengtou.cf.common.api.outer.pay.yibao.vo;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 有短验充值请求vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "有短验收款对象")
public class SmsPayRequestVO {
    public SmsPayRequestVO() {
    }

    public SmsPayRequestVO(String requestno, String identityid, String cardtop, String cardlast, double amount, String
            productname, String requesttime, String registtime ) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.cardtop = cardtop;
        this.cardlast = cardlast;
        this.amount = amount;
        this.productname = productname;
        this.requesttime = requesttime;
        this.registtime = registtime;
        this.avaliabletime="3";
    }

    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="自定义用户标识",name="identityid")
    public String identityid;
    @ApiModelProperty(value="用户标识类型:MAC(\"网卡地址\"),IMEI(\"国际移动设备标识\"),ID_CARD(\"用户身份证号\"),PHONE(\"手机号\"),EMAIL(\"邮箱\"),USER_ID(\"用户id\")," +
            "AGREEMENT_NO(\"用户纸质订单协议号\")",name="identitytype")
    public IdentityTypeEnum identitytype=IdentityTypeEnum.ID_CARD;
    @ApiModelProperty(value="卡号前六位",name="cardtop")
    public String cardtop;
    @ApiModelProperty(value="卡号后四位",name="cardlast")
    public String cardlast;
    @ApiModelProperty(value="金额",name="amount")
    public double amount;
    @ApiModelProperty(value="验证码发送方式（短信、语音）：MESSAGE(\"短信形式发送\"),VOICE(\"语音方式发送\")",name="advicesmstype")
    public AdviceSmsTypeEnum advicesmstype=AdviceSmsTypeEnum.MESSAGE;
    @ApiModelProperty(value="订单有效期:交易时效",name="avaliabletime")
    public String avaliabletime;
    @ApiModelProperty(value="产品名称",name="productname")
    public String productname;
    @ApiModelProperty(value="回调地址:交易通知回调地址",name="callbackurl")
    public String callbackurl;
    @ApiModelProperty(value="请求时间（yyyy-MM-dd HH:mm:ss）",name="requesttime")
    public String requesttime;
    @ApiModelProperty(value="用户注册时间（yyyy-MM-dd HH:mm:ss）",name="registtime")
    public String registtime;
    @ApiModelProperty(value="备注",name="free1")
    public String free1;

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

    public String getRegisttime() {
        return registtime;
    }

    public void setRegisttime(String registtime) {
        this.registtime = registtime;
    }

    public String getFree1() {
        return free1;
    }

    public void setFree1(String free1) {
        this.free1 = free1;
    }
}
