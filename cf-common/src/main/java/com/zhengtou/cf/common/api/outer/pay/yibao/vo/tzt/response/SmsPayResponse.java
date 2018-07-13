package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 有短验充值请求
 * @author 葛文镇
 */
@ApiModel(discriminator = "有短验充值返回对象")
public class SmsPayResponse extends YiBaoBaseResponse{
    @ApiModelProperty(value="商户编号",name="merchantno")
    public String merchantno;
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="短信验证码",name="smscode")
    public String smscode;
    @ApiModelProperty(value="短信发送方：默认为易宝",name="codesender")
    public String codesender="YEEPAY";
    @ApiModelProperty(value="验证码发送方式（短信、语音）：MESSAGE(\"短信形式发送\"),VOICE(\"语音方式发送\")",name="smstype")
    public String smstype;
    @ApiModelProperty(value="发送短信手机号",name="phone")
    public String phone;
    //PayStatusEnum
    @ApiModelProperty(value="充值状态：ACCEPT(\"已接收\"),TO_VALIDATE(\"待短验\"),PAY_FAIL(\"支付失败\"),PROCESSING(\"处理中\"),PAY_SUCCESS(\"支付成功\"),TIME_OUT" +
            "(\"超时失败\"),FAIL(\"系统异常\");",name="status")
    public String status;
    @ApiModelProperty(value="错误码",name="errorcode")
    public String errorcode;
    @ApiModelProperty(value="错误信息",name="errormsg")
    public String errormsg;
    @ApiModelProperty(value="自由项1",name="free1")
    public String free1;
    @ApiModelProperty(value="自由项2",name="free2")
    public String free2;
    @ApiModelProperty(value="自由项3",name="free3")
    public String free3;
    @ApiModelProperty(value="签名验证字段",name="sign")
    public String sign;

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

    public String getSmscode() {
        return smscode;
    }

    public void setSmscode(String smscode) {
        this.smscode = smscode;
    }

    public String getCodesender() {
        return codesender;
    }

    public void setCodesender(String codesender) {
        this.codesender = codesender;
    }

    public String getSmstype() {
        return smstype;
    }

    public void setSmstype(String smstype) {
        this.smstype = smstype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
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
