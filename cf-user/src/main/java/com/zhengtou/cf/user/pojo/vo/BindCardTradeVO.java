package com.zhengtou.cf.user.pojo.vo;

import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.enums.BindCardStatusEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 绑卡记录
 */
public class BindCardTradeVO extends PeakBaseVo{
    public BindCardTradeVO(String requestno, String consumerRealName, String phone,String idnum, String cardNo, BankCodeEnum bankName, String requesttime,
                           BindCardStatusEnum status) {
        this.requestno = requestno;
        this.consumerRealName = consumerRealName;
        this.phone = phone;
        this.idnum=idnum;
        this.cardNo = cardNo;
        this.bankName = bankName;
        this.requesttime = requesttime;
        this.status = status;
    }

    //绑卡流水号
    private String requestno;
    //用户真实姓名
    private String consumerRealName;
    //用户手机号
    private String phone;
    //身份证
    private String idnum;
    //卡号
    private String cardNo;
    /**
     * 银行名称
     */
    private BankCodeEnum bankName;
    //请求时间
    private String requesttime;
    /**
     * 卡状态
     */
    private BindCardStatusEnum status;

    public String getRequestno() {
        return requestno;
    }

    public void setRequestno(String requestno) {
        this.requestno = requestno;
    }

    public String getConsumerRealName() {
        return consumerRealName;
    }

    public void setConsumerRealName(String consumerRealName) {
        this.consumerRealName = consumerRealName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BankCodeEnum getBankName() {
        return bankName;
    }

    public void setBankName(BankCodeEnum bankName) {
        this.bankName = bankName;
    }

    public String getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(String requesttime) {
        this.requesttime = requesttime;
    }

    public BindCardStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BindCardStatusEnum status) {
        this.status = status;
    }
}
