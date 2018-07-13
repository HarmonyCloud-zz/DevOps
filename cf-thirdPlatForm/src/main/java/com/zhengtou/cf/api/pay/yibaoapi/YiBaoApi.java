package com.zhengtou.cf.api.pay.yibaoapi;

import com.zhengtou.cf.api.pay.yibaoapi.util.tzt.TZTService;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request.*;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 易宝接口实现类
 *
 * @author 葛文镇
 */
@Service
public class YiBaoApi {
    @Autowired
    TZTService tztService;
    /************************************************************投资通**************************************************************************************/
    //有短验绑卡申请
    public SmsBindCardResponse smsBindCard(SmsBindCardRequest smsBindCardRequest) throws Exception {
        SmsBindCardResponse smsBindCardResponse = new SmsBindCardResponse();
        smsBindCardResponse.parseMap(tztService.bindCardRequest(smsBindCardRequest.parseMap()));
        return smsBindCardResponse;
    }

    //有短验确认
    public SmsBindCardCheckResponse smsBindCardCheck(SmsBindCardCheckRequest smsBindCardCheckRequest) throws Exception {
        SmsBindCardCheckResponse smsBindCardCheckResponse = new SmsBindCardCheckResponse();
        smsBindCardCheckResponse.parseMap(tztService.bindCardConfirm(smsBindCardCheckRequest.parseMap()));
        return smsBindCardCheckResponse;
    }

    //有短验绑卡申请 重发
    public ReSmsBindCardResponse reSmsBindCard(ReSmsBindCardRequest reSmsBindCardRequest) throws Exception {
        ReSmsBindCardResponse reSmsBindCardResponse = new ReSmsBindCardResponse();
        reSmsBindCardResponse.parseMap(tztService.bindCardResendsms(reSmsBindCardRequest.parseMap()));
        return reSmsBindCardResponse;
    }

    //无短验充值
    public NoSmsPayResponse noSmsPay(NoSmsPayRequest noSmsPayRequest) throws IllegalAccessException {
        NoSmsPayResponse smsPayResponse = new NoSmsPayResponse();
        smsPayResponse.parseMap(tztService.unSendBindPayRequest(noSmsPayRequest.parseMap()));
        return smsPayResponse;
    }

    //有短验充值 请求
    public SmsPayResponse smsPay(SmsPayRequest smsPayRequest) throws Exception {
        SmsPayResponse smsPayResponse = new SmsPayResponse();
        smsPayResponse.parseMap(tztService.bindPayRequest(smsPayRequest.parseMap()));
        return smsPayResponse;
    }

    //有短验充值确认
    public SmsPayCheckResponse smsPayCheck(SmsPayCheckRequest smsPayCheckRequest) throws Exception {
        SmsPayCheckResponse smsPayCheckResponse = new SmsPayCheckResponse();
        smsPayCheckResponse.parseMap(tztService.bindPayConfirm(smsPayCheckRequest.parseMap()));
        return smsPayCheckResponse;
    }

    //有短验充值确认 重发
    public SmsPayCheckResponse reSmsPay(ReSmsBindCardRequest reSmsBindCardRequest) throws Exception {
        SmsPayCheckResponse smsPayCheckResponse = new SmsPayCheckResponse();
        smsPayCheckResponse.parseMap(tztService.bindPayResendsms(reSmsBindCardRequest.parseMap()));
        return smsPayCheckResponse;
    }

    //首次充值
    public SmsPayResponse firstPay(FirstPayRequest firstPayRequest) throws Exception {
        SmsPayResponse smsPayResponse = new SmsPayResponse();
        smsPayResponse.parseMap(tztService.firstPayRequest(firstPayRequest.parseMap()));
        return smsPayResponse;
    }

    //首次充值短验确认
    public SmsPayCheckResponse firstPayCheck(SmsPayCheckRequest smsPayCheckRequest) throws Exception {
        SmsPayCheckResponse smsPayCheckResponse = new SmsPayCheckResponse();
        smsPayCheckResponse.parseMap(tztService.fisrstPayConfirm(smsPayCheckRequest.parseMap()));
        return smsPayCheckResponse;
    }

    //首次充值短验重发
    public ReSmsBindCardResponse reSmsFirstPay( ReSmsBindCardRequest reSmsBindCardRequest) throws IllegalAccessException {
        ReSmsBindCardResponse reSmsBindCardResponse=new ReSmsBindCardResponse();
        reSmsBindCardResponse.parseMap(tztService.firstPayResendsms(reSmsBindCardRequest.parseMap()));
        return reSmsBindCardResponse;
    }

    //体现请求
    public WithDrawResponse withDraw(WithDrawRequest withDrawRequest) throws IllegalAccessException {
        WithDrawResponse withDrawResponse=new WithDrawResponse();
        Map<String,String> map=tztService.withdraw(withDrawRequest.parseMap());
        withDrawResponse.parseMap(map);
        return withDrawResponse;
    }

    /*******************************查询类接口*************************************/
    //绑卡记录查询
    public QueryBindCardResponse queryBindCardStatus(String requestno,String yborderid){
        QueryBindCardResponse queryBindCardResponse=new QueryBindCardResponse();
        queryBindCardResponse.parseMap(tztService.queryBindCardRecord(requestno,yborderid));
        return queryBindCardResponse;
    }
    //首次充值记录查询
    public QueryFirstPayResponse firstpayrecord(String requestno, String yborderid) {
        QueryFirstPayResponse queryFirstPayResponse=new QueryFirstPayResponse();
        queryFirstPayResponse.parseMap(tztService.firstpayrecord(requestno,yborderid));
        return queryFirstPayResponse;
    }

    //绑卡充值记录查询
    public QueryPayResponse queryBindPayRecord(String requestno,String yborderid){
        QueryPayResponse queryPayResponse=new QueryPayResponse();
        queryPayResponse.parseMap(tztService.queryBindPayRecord(requestno,yborderid));
        return queryPayResponse;
    }

    //体现记录查询
    public QueryWithDrawResponse queryDrawRecord(String requestno,String yborderid){
        QueryWithDrawResponse queryWithDrawResponse=new QueryWithDrawResponse();

        queryWithDrawResponse.parseMap(tztService.queryDrawRecord(requestno,yborderid));
        return queryWithDrawResponse;
    }

    //可提现余额查询
    public QueryDrawValidAmountResponse drawValidAmount(){
        QueryDrawValidAmountResponse queryDrawValidAmountResponse=new QueryDrawValidAmountResponse();
        Map<String,String> map=tztService.drawValidAmount();
        queryDrawValidAmountResponse.parseMap(map);
        return queryDrawValidAmountResponse;
    }

    //绑卡列表查询
    public Map<String, String> queryBindCard(String idNo){
        Map<String, String> map=tztService.queryBindCardList(idNo,"ID_CARD");
        return map;
    }

    /************************************************************商户代付代发**************************************************************************************/

}
