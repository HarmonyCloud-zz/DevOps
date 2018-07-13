package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 有短验充值请求
 * @author 葛文镇
 */
@ApiModel(discriminator = "有短验充值确认返回对象")
public class SmsPayCheckResponse extends YiBaoBaseResponse{
    @ApiModelProperty(value="商户编号",name="merchantno")
    public String merchantno;
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="易宝流水号",name="yborderid")
    public String yborderid;
    @ApiModelProperty(value="充值金额",name="amount")
    public String amount;
    //PayStatusEnum
    @ApiModelProperty(value="充值状态：ACCEPT(\"已接收\"),TO_VALIDATE(\"待短验\"),PAY_FAIL(\"支付失败\"),PROCESSING(\"处理中\"),PAY_SUCCESS(\"支付成功\"),TIME_OUT" +
            "(\"超时失败\"),FAIL(\"系统异常\");",name="status")
    public String status;
    @ApiModelProperty(value="短信发送方：默认为易宝",name="codesender")
    public String codesender;
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

    public String getCodesender() {
        return codesender;
    }

    public void setCodesender(String codesender) {
        this.codesender = codesender;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
