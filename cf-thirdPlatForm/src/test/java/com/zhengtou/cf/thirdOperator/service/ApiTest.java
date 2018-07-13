package com.zhengtou.cf.thirdOperator.service;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.api.phone.controller.ShjApi;
import com.zhengtou.cf.risk.riskApi.jxlapi.JxlApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdTypeEnum;
import com.zhengtou.cf.api.sms.controller.SmsApi;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Api测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Autowired
    TdApi tdApi;
    @Autowired
    JxlApi jxlApi;
    @Autowired
    ShjApi shjApi;
    @Autowired
    SmsApi smsApi;
    @Before
    public void setUp() {
    }

    /**
     * 同盾接口测试
     */
    @Test
    public void tdApiTest(){
        TDApiRequest bodyGuardApiRequest=new TDApiRequest("370404199006301915","15961718173","皮晴晴");
        TDApiResponse bodyGuardApiResponse=tdApi.tdApi(TdTypeEnum.android,bodyGuardApiRequest);
        System.out.println(JSON.toJSONString(bodyGuardApiResponse));
        System.out.println(bodyGuardApiResponse.getResult_desc().getCREDITSCORE().getCredit_score());
    }

    /******************************************************聚信立接口测试**************************************/
    /******************************************************蜜蜂**************************************/
    /**
     * 获取token
     */
    @Test
    public void getTokenTest() throws Exception {
        String bodyGuardApiResponse= jxlApi.getToken();
        System.out.println(bodyGuardApiResponse);
    }

    /**
     * 获取用户基本信息
     */
    @Test
    public void getBaseInfoTest() throws Exception {
        jxlApi.getBaseInfo("马孝辉","41018198511146535","13393705178");
    }

    /**
     * 获取用户运营商信息
     */
    @Test
    public void getPhoneInfoTest() throws Exception {
        jxlApi.getPhoneInfo("郝亚龙","41272119941212507X","15136197546");
    }

    /**
     * 获取用户京东信息
     */
    @Test
    public void getBuessinesJdInfoTest() throws Exception {
        jxlApi.getBussinesJdInfo("郝亚龙","41272119941212507X","15136197546");
    }

    /**
     * 获取用户淘宝信息
     */
    @Test
    public void getBuessinesTbInfoTest() throws Exception {
        jxlApi.getBussinesTbInfo("郝亚龙","41272119941212507X","15136197546");
    }

    /*******************数据采集*********************/
    /**************运营商、京东********必须先采集运营商******/
    /**
     * 获取token
     */
    @Test
    public void getPhoneToken() throws Exception {
        jxlApi.getPhoneToken("马孝辉","410181198511146535","13393705178");
    }
    /**
     * 采集运营商
     */
    @Test
    public void authPhoneCollection(){
        jxlApi.authPhoneCollection("郝亚龙","41272119941212507X","15136197546","941226","8843", "");
    }
    /**
     * 采集JD
     */
    @Test
    public void authJDCollection(){
        jxlApi.authJDCollection("郝亚龙","41272119941212507X","15136197546","15136197546","sclc1226", "448755");
    }
    @Test
    public void getDataSourceList() throws Exception {
        jxlApi.getDataSourceList();
    }
    /******************淘宝******************/
    @Test
    public void authTBCollection(){
        jxlApi.authTBCollection("葛文镇","410881199012263158","18521785060","18521785060","gw901226");
    }

    /******************************************************蜜罐**************************************/
    /**
     * 获取token
     */
    @Test
    public void getHoneyTokenTest() throws Exception {
        String bodyGuardApiResponse= jxlApi.getHoneyToken();
        System.out.println(bodyGuardApiResponse);
    }
    /**
     * 获取用户基本信息
     */
    @Test
    public void getHoneyInfoTest() throws Exception {
        jxlApi.getHoneyInfo("葛文镇","410881199012263158","18521785060");
    }

    /**
     * 呼叫中心电话呼出测试
     * @throws Exception
     */
    @Test
    public void dail() throws Exception {
        //获取手机号码归属地网页爬取
//        int i = 0;
//        StringBuffer sb = new StringBuffer("");
//        URL url;
//        try {
//            url = new URL("http://www.ip138.com:8080/search.asp?mobile=15939876679&action=mobile");
//            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
//            String s = "";
//            while ((s = br.readLine()) != null) {
//                i++;
//                sb.append(s + "\r\n");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(sb.toString());

        //电话呼出测试
        shjApi.dail("123","13393705178");
    }

    /**
     * 发送短消息测试
     * @throws Exception
     */
    @Test
    public void sendSms() throws Exception {
        System.out.println(smsApi.sendMsg("13393705178", "【郑投网】您的验证码为123456"));
    }
}
