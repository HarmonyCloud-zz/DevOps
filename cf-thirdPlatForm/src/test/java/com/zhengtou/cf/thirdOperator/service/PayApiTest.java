package com.zhengtou.cf.thirdOperator.service;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.api.pay.yibaoapi.YiBaoApi;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.IdentityTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request.*;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response.*;
import com.zhengtou.cf.common.utils.DateUtils;
import com.zhengtou.cf.common.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * 支付Api测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApiTest {
    @Autowired
    YiBaoApi yiBaoApi;

    //绑卡请求，有短验
    @Test
    public void smsBindCardTest() throws Exception {
        String requestno = StringUtils.getExtSerialNum();
        System.out.println(requestno);
        SmsBindCardRequest smsBindCardRequest = new SmsBindCardRequest(requestno, "111",
                "6216613400005898140", "410881199012263158", "葛文镇",
                "18521785060", DateUtils.getSysDateTimeString());
        System.out.println(JSON.toJSONString(smsBindCardRequest));
        SmsBindCardResponse smsBindCardResponse = yiBaoApi.smsBindCard(smsBindCardRequest);
    }

    //确认绑卡请求
    @Test
    public void smsBindCardCheckTst() throws Exception {
        SmsBindCardCheckRequest smsBindCardCheckRequest = new SmsBindCardCheckRequest("5345aa74f0944587957a3ad9e47a6571", "930582");
        SmsBindCardCheckResponse smsBindCardCheckResponse = yiBaoApi.smsBindCardCheck(smsBindCardCheckRequest);
        System.out.println(JSON.toJSONString(smsBindCardCheckResponse));
    }

    //重发绑卡请求短验
    @Test
    public void reSmsBindCardTest() throws Exception {
        ReSmsBindCardRequest reSmsBindCardRequest = new ReSmsBindCardRequest("ZT15180739925194058", AdviceSmsTypeEnum.MESSAGE);
        ReSmsBindCardResponse reSmsBindCardResponse = yiBaoApi.reSmsBindCard(reSmsBindCardRequest);
        System.out.println(JSON.toJSONString(reSmsBindCardResponse));
    }

    //查账户余额
    @Test
    public void drawValidAmountTest() {
        QueryDrawValidAmountResponse queryDrawValidAmountResponse = yiBaoApi.drawValidAmount();
        System.out.println(JSON.toJSONString(queryDrawValidAmountResponse));
    }

    //有短验充值请求
    @Test
    public void smsPaTest() throws Exception {
        String requestno = StringUtils.getExtSerialNum();
        System.out.println(requestno);
        SmsPayRequest smsPayRequest = new SmsPayRequest(requestno, "111", IdentityTypeEnum.ID_CARD, "621661", "8140", 10, "测试", DateUtils
                .getSysDateTimeString(), "2018-02-08 23:32:43", "0", "192.168.1.51");
        SmsPayResponse smsPayResponse = yiBaoApi.smsPay(smsPayRequest);
        System.out.println(JSON.toJSONString(smsPayResponse));
    }

    //有短验充值请求确认
    @Test
    public void smsPayCheckTest() throws Exception {
        SmsPayCheckRequest smsPayCheckRequest = new SmsPayCheckRequest("ZT15181042591274620", "734205");
        SmsPayCheckResponse smsPayCheckResponse = yiBaoApi.smsPayCheck(smsPayCheckRequest);
        System.out.println(JSON.toJSONString(smsPayCheckResponse));
    }

    //无短验充值
    @Test
    public void noSmsPay() throws IllegalAccessException {
        String requestno = StringUtils.getExtSerialNum();
        System.out.println(requestno);
        NoSmsPayRequest noSmsPayRequest = new NoSmsPayRequest(requestno, "111", IdentityTypeEnum.ID_CARD, DateUtils.getSysDateTimeString(), 1, "测试",
                "621661", "8140", "2018-02-08 23:32:43","0", "192.168.1.51");
        NoSmsPayResponse noSmsPayResponse = yiBaoApi.noSmsPay(noSmsPayRequest);
        System.out.println(JSON.toJSONString(noSmsPayResponse));
    }

    //首次充值
    @Test
    public void firstPayTest() throws Exception {
        String requestno = StringUtils.getExtSerialNum();
        System.out.println(requestno);
        FirstPayRequest firstPayRequest = new FirstPayRequest(requestno, "123", IdentityTypeEnum.ID_CARD, "6228270711228084774",
                "410221199508020839", "高来松", "15225470606", 1, "2018-02-08 15:19:43", "2018-02-08 15:19:43", "0", "192.168.1.51");
        SmsPayResponse smsPayResponse = yiBaoApi.firstPay(firstPayRequest);
        System.out.println(JSON.toJSONString(smsPayResponse));
    }

    //首冲短验确认
    @Test
    public void firstPayCheckTest() throws Exception {
        SmsPayCheckRequest smsPayCheckRequest = new SmsPayCheckRequest("ZT15180764477097873", "805614");
        SmsPayCheckResponse smsPayCheckResponse = yiBaoApi.firstPayCheck(smsPayCheckRequest);
        System.out.println(JSON.toJSONString(smsPayCheckResponse));
    }

    //首充记录查询
    @Test
    public void firstpayrecordTest() {
        yiBaoApi.firstpayrecord("ZT15180764477097873", "");
    }

    @Test
    //绑卡充值查询
    public void queryBindPayRecordTest() {
        QueryPayResponse queryPayResponse = yiBaoApi.queryBindPayRecord("ZT15181040323796300", "");
        System.out.println(JSON.toJSONString(queryPayResponse));
    }
    //提现

    //绑卡列表查询
    @Test
    public void queryBindCardList(){
        Map<String, String> map=yiBaoApi.queryBindCard("410881199012263158");
        System.out.println(map.toString());
    }
}
