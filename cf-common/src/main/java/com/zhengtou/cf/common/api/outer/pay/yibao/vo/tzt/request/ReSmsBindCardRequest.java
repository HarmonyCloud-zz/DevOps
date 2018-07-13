package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;

/**
 * 有短验绑卡请求 重发
 * @author 葛文镇
 */
public class ReSmsBindCardRequest extends YiBaoBaseRequest{
   public String merchantno;
   public String requestno;
   public AdviceSmsTypeEnum advicesmstype;
   public String sign;

    public ReSmsBindCardRequest(String requestno,AdviceSmsTypeEnum advicesmstype) {
        this.requestno = requestno;
        this.advicesmstype=advicesmstype;
    }

    public ReSmsBindCardRequest() {
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

    public AdviceSmsTypeEnum getAdvicesmstype() {
        return advicesmstype;
    }

    public void setAdvicesmstype(AdviceSmsTypeEnum advicesmstype) {
        this.advicesmstype = advicesmstype;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
