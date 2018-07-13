package com.zhengtou.cf.common.api.outer.pay.yibao.vo;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 绑卡请求vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "有短验绑卡请求对象")
public class SmsBindCardRequestVO {
    public SmsBindCardRequestVO() {
    }

    public SmsBindCardRequestVO(String requestno, String cardno, String idcardno, String username, String phone, String requesttime) {
        this.requestno = requestno;
        this.identityid = idcardno;
        this.cardno = cardno;
        this.idcardno = idcardno;
        this.username = username;
        this.phone = phone;
        this.avaliabletime = "3";
        this.requesttime = requesttime;
    }

    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="自定义用户标识：用户唯一标识",name="identityid")
    public String identityid;
    @ApiModelProperty(value="用户标识类型:MAC(\"网卡地址\"),IMEI(\"国际移动设备标识\"),ID_CARD(\"用户身份证号\"),PHONE(\"手机号\"),EMAIL(\"邮箱\"),USER_ID(\"用户id\")," +
            "AGREEMENT_NO(\"用户纸质订单协议号\")",name="identitytype")
    public IdentityTypeEnum identitytype=IdentityTypeEnum.ID_CARD;
    @ApiModelProperty(value="卡号",name="cardno")
    public String cardno;
    @ApiModelProperty(value="证件号",name="idcardno")
    public String idcardno;
    @ApiModelProperty(value="姓名",name="username")
    public String username;
    @ApiModelProperty(value="手机号",name="phone")
    public String phone;
    @ApiModelProperty(value="验证码发送方式（短信、语音）：MESSAGE(\"短信形式发送\"),VOICE(\"语音方式发送\")",name="advicesmstype")
    public AdviceSmsTypeEnum advicesmstype;
    @ApiModelProperty(value="订单有效时间：交易时效",name="avaliabletime")
    public String avaliabletime;
    @ApiModelProperty(value="回调地址:回调通知地址",name="callbackurl")
    public String callbackurl;
    @ApiModelProperty(value="请求时间（yyyy-MM-dd HH:mm:ss）",name="requesttime")
    public String requesttime;

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
}
