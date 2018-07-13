package com.zhengtou.cf.common.api.outer.pay.yibao.vo;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.DrawTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 提现请求vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "提现对象")
public class WithDrawRequestVO {
    public WithDrawRequestVO() {
    }

    public WithDrawRequestVO(String requestno, String identityid, String cardtop, String cardlast, double amount, String
            requesttime) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.cardtop = cardtop;
        this.cardlast = cardlast;
        this.amount = amount;
        this.drawtype = DrawTypeEnum.NATRALDAY_URGENT;
        this.requesttime = requesttime;
        this.userip="192.168.1.51";
    }

    //体现请求好
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="自定义用户标识",name="identityid")
    public String identityid;
    @ApiModelProperty(value="用户标识类型:MAC(\"网卡地址\"),IMEI(\"国际移动设备标识\"),ID_CARD(\"用户身份证号\"),PHONE(\"手机号\"),EMAIL(\"邮箱\"),USER_ID(\"用户id\")," +
            "AGREEMENT_NO(\"用户纸质订单协议号\")",name="identitytype")
    public IdentityTypeEnum identitytype;
    @ApiModelProperty(value="卡号前六位",name="cardtop")
    public String cardtop;
    @ApiModelProperty(value="卡号后四位",name="cardlast")
    public String cardlast;
    @ApiModelProperty(value="体现金额",name="amount")
    public double amount;
    @ApiModelProperty(value="体现类型:NATRALDAY_NORMAL(\"自然日t+1\"),NATRALDAY_URGENT(\"自然日t+0\");",name="drawtype")
    public DrawTypeEnum drawtype;
    //回调地址
    @ApiModelProperty(value="回调地址:回调通知地址",name="callbackurl")
    public String callbackurl;
    @ApiModelProperty(value="用户ip",name="userip")
    public String userip;
    @ApiModelProperty(value="请求时间（yyyy-MM-dd HH:mm:ss）",name="requesttime")
    public String requesttime;
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

    public DrawTypeEnum getDrawtype() {
        return drawtype;
    }

    public void setDrawtype(DrawTypeEnum drawtype) {
        this.drawtype = drawtype;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public String getFree1() {
        return free1;
    }

    public void setFree1(String free1) {
        this.free1 = free1;
    }
}
