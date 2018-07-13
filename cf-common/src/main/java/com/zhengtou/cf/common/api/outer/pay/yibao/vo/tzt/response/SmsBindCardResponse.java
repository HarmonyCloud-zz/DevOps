package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(discriminator = "有短验绑卡返回对象")
public class SmsBindCardResponse extends YiBaoBaseResponse{
    @ApiModelProperty(value="商户编号",name="merchantno")
    public String merchantno;
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="易宝流水号",name="yborderid")
    public String yborderid;
    @ApiModelProperty(value="绑卡状态：ACCEPT(\"已接收\"),TO_ENHANCED(\"待补充鉴权\"),TO_VALIDATE(\"待短验\"),BIND_SUCCESS(\"绑卡成功\"),BIND_FAIL(\"绑卡失败\"),BIND_ERROR" +
            "(\"绑卡异常\"),TIME_OUT(\"超时失败\"),WAIT_BIND(\"待绑卡\"),FAIL(\"系统异常\");",name="status")
    public String status;
    @ApiModelProperty(value="短信验证码",name="smscode")
    public String smscode;
    @ApiModelProperty(value="短信发送方：默认为易宝",name="codesender")
    public String codesender="YEEPAY";
    @ApiModelProperty(value="支持得补充鉴权类型",name="enhancedtype")
    public String enhancedtype;
    @ApiModelProperty(value="验证码发送方式（短信、语音）：MESSAGE(\"短信形式发送\"),VOICE(\"语音方式发送\")",name="smstype")
    public String smstype;
    @ApiModelProperty(value="错误码",name="errorcode")
    public String errorcode;
    @ApiModelProperty(value="错误信息",name="errormsg")
    public String errormsg;
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

    public String getYborderid() {
        return yborderid;
    }

    public void setYborderid(String yborderid) {
        this.yborderid = yborderid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSmstype() {
        return smstype;
    }

    public void setSmstype(String smstype) {
        this.smstype = smstype;
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

    public String getEnhancedtype() {
        return enhancedtype;
    }

    public void setEnhancedtype(String enhancedtype) {
        this.enhancedtype = enhancedtype;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
