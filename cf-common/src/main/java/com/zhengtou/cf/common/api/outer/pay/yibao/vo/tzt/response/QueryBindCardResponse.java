package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 绑卡查询接口
 * @author 葛文镇
 */
@ApiModel(discriminator = "绑卡查询接口返回")
public class QueryBindCardResponse extends YiBaoBaseResponse{

    @ApiModelProperty(value="商户编号",name="merchantno")
    public String merchantno;
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="易宝流水号",name="yborderid")
    public String yborderid;
    public String amount;
    @ApiModelProperty(value="卡号前六位",name="cardtop")
    public String cardtop;
    @ApiModelProperty(value="卡号后四位",name="cardlast")
    public String cardlast;
    @ApiModelProperty(value="银行编号",name="bankcode")
    public String bankcode;
    //BindCardEnum
    @ApiModelProperty(value="绑卡状态：ACCEPT(\"已接收\"),TO_ENHANCED(\"待补充鉴权\"),TO_VALIDATE(\"待短验\"),BIND_SUCCESS(\"绑卡成功\"),BIND_FAIL(\"绑卡失败\"),BIND_ERROR" +
            "(\"绑卡异常\"),TIME_OUT(\"超时失败\"),WAIT_BIND(\"待绑卡\"),FAIL(\"系统异常\");",name="status")
    public String status;
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

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
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
