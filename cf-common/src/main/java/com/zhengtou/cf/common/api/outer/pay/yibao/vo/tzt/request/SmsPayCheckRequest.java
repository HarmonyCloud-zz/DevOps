package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

/**
 * 有短验充值请求
 * @author 葛文镇
 */
public class SmsPayCheckRequest  extends YiBaoBaseRequest{
    public String merchantno;
    public String requestno;
    public String validatecode;

    public String sign;

    public SmsPayCheckRequest(String requestno, String validatecode) {
        this.requestno = requestno;
        this.validatecode = validatecode;
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

    public String getValidatecode() {
        return validatecode;
    }

    public void setValidatecode(String validatecode) {
        this.validatecode = validatecode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
