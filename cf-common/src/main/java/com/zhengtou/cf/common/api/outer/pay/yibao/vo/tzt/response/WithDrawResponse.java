package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 体现返回
 * @author 葛文镇
 */
@ApiModel(discriminator = "体现返回对象")
public class WithDrawResponse extends YiBaoBaseResponse{
    @ApiModelProperty(value="商户编号",name="merchantno")
    public String merchantno;
    @ApiModelProperty(value="交易流水号",name="requestno")
    public String requestno;
    @ApiModelProperty(value="易宝流水号",name="yborderid")
    public String yborderid;
    @ApiModelProperty(value="订单状态：ACCEPT(\"已接收\"),PROCESSING(\"处理中\"),WITHDRAW_SUCCESS(\"提现成功\"),WITHDRAW_FAIL(\"提现失败\"),FAIL(\"系统异常\");",name="status")
    public String status;
    @ApiModelProperty(value="提现金额",name="amount")
    public String amount;
    @ApiModelProperty(value="卡号前六位",name="cardtop")
    public String cardtop;
    @ApiModelProperty(value="卡号后四位",name="cardlast")
    public String cardlast;
    @ApiModelProperty(value="银行编号",name="bankcode")
    public String bankcode;
    @ApiModelProperty(value="自由项1",name="free1")
    public String free1;
    @ApiModelProperty(value="自由项2",name="free2")
    public String free2;
    @ApiModelProperty(value="自由项3",name="free3")
    public String free3;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
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
