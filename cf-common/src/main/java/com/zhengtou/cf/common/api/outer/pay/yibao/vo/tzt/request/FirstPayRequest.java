package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;

/**
 * 首次充值请求
 * @author 葛文镇
 */
public class FirstPayRequest extends YiBaoBaseRequest{
   public String merchantno;
   public String requestno;
   public String identityid;
   public IdentityTypeEnum identitytype;
   public String cardno;
   public String idcardno;
   public String idcardtype="ID";
   public String username;
   public String phone;

    public double amount;
    public AdviceSmsTypeEnum advicesmstype;

    //有效期
   public String avaliabletime="3";
   public String callbackurl;
   public String requesttime;
   public String productname;
   public String terminalid="192.168.1.51";
   public String registtime;
   public String registip;
   public String issetpaypwd;

   public String sign;

    public FirstPayRequest(String requestno, String identityid, IdentityTypeEnum identitytype, String cardno, String idcardno, String username,
                           String phone, double amount, String requesttime, String registtime, String issetpaypwd,String registip) {
        this.requestno = requestno;
        this.identityid = identityid;
        this.identitytype = identitytype;
        this.cardno = cardno;
        this.idcardno = idcardno;
        this.username = username;
        this.phone = phone;
        this.amount = amount;
        this.requesttime = requesttime;
        this.registtime = registtime;
        this.issetpaypwd = issetpaypwd;
        this.registip=registip;
    }
}
