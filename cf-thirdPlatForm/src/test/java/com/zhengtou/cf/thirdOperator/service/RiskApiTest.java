package com.zhengtou.cf.thirdOperator.service;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.risk.riskApi.jxlapi.JxlApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdTypeEnum;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.BussinessTaskResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.PhoneTaskResponse;
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
public class RiskApiTest {

    @Autowired
    TdApi tdApi;
    @Autowired
    JxlApi jxlApi;
    @Before
    public void setUp() {
    }
    /******************************************************同盾**************************************/
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

    /**
     * 获取电商数据
     */
    @Test
    public void collectionPhoneTaskTest(){
        PhoneTaskResponse phoneTaskResponse =tdApi.collectionPhoneTask("");
    }

    /**
     * 获取电商数据
     */
    @Test
    public void collectionBussinessTask(){
        BussinessTaskResponse phoneTaskResponse =tdApi.collectionBussinessTask("");
    }

    /******************************************************聚信立接口测试**************************************/
    /***********************************蜜蜂**************************************/
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
        jxlApi.getBaseInfo("郝亚龙","41272119941212507X","15136197546");
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

}
