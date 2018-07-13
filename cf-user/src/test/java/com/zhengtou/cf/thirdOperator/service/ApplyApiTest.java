package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.api.cl.ProductApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.*;
import com.zhengtou.cf.common.api.outer.cl.apply.infoVO.*;
import com.zhengtou.cf.common.api.outer.cl.apply.request.ApplyRequest;
import com.zhengtou.cf.risk.pojo.entity.rule.RuleEntity;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;
import com.zhengtou.cf.risk.service.RuleService;
import com.zhengtou.cf.trade.pojo.entity.enums.TradeModelEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ApplyApiTest {

    @Autowired
    ApplyApi api;
    @Autowired
    ProductApi productApi;

    /**
     * 获取审批金额
     * @throws Exception
     * \
     */
    @Test
    public void getCount() throws Exception {
        System.out.println(api.getAppNo(TradeModelEnum.一般进件审批单号));
    }


    @Autowired
    RuleService ruleService;
    @Test
    public void getRule() throws Exception {
        RuleEntity rule = new RuleEntity();
        rule.setRuleType(ApprovalRuleTypeEnum.关注项);
        rule.setRuleName("gg");
    }



    @Test
    public void testJob() throws Exception {

//        System.out.println(api.getApprovalAmount("PA201801181600000401"));
    }


    @Test
    public void getApplyList() throws Exception {
    }

    /**
     * 提交申请单
     * @throws Exception
     */
    @Test
    public void apply() throws Exception {


//        System.out.println(JSONObject.toJSONString(applyInfoDao.findAll()));

//        PersonalReciveVO personalInfo = new PersonalReciveVO("马孝辉",
//                GenderEnum.男, 28, CertificateTypeEnum.身份证, "410181198511196577", "13393705178");
//
//        //银行卡信息
//        CardVO cardInfo_s = new CardVO(BankCodeEnum.中国银行, "6214833801798170", "13393705178");
//        List<CardVO> cardInfoList = new ArrayList<>();
//        cardInfoList.add(cardInfo_s);
//
//        //商品信息
//        OrderProductInfo orderProductInfo = new OrderProductInfo("xiaogou001", "小狗", "吸尘器", 999, 3);
//        List<OrderProductInfo> orderProductInfoList = new ArrayList<>();
//        orderProductInfoList.add(orderProductInfo);
//        //订单信息
//        OrderInfo orderInfo = new OrderInfo("4646479111", "100001", "zt001", "1866482794507535363"
//                , 2997, 3, 2500, "小芳", "13000000000", "郑州市东风南路互联网金融大厦", 300, 0, "0", DateUtils.getSysDateTimeString(), DateUtils
//                .getSysDateTimeString(), orderProductInfoList);
//
//        //贷款信息
//        BillEntity loanInfo = new BillEntity("CASH", 3000, 12, "AI");
//        loanInfo.setPremiumInd("N");
//        ApplyRequest applyRequest = new ApplyRequest("PA201801231600000533", "20180111", "123", "123", SocialIdentityEnum.在职人员, "2018011899", personalInfo, cardInfoList,loanInfo);
//        applyRequest.setOrderInfo(orderInfo);
//        applyRequest.setCardInfo(null);
//
//        productApi.applyLoan(applyRequest);
//
//        System.out.println(JSONObject.toJSONString(applyInfoDao.findAll()));

        /***************************房抵贷数据***************************/

                PersonalInfo personalInfo = new PersonalInfo("马孝辉",
                GenderEnum.M, 28, CertificateTypeEnum.I, "410181198511196577", "13393705178");

        //        //银行卡信息
        CardInfo cardInfo_s = new CardInfo(BankCodeEnum.中国银行, "6214833801798170", "13393705178");
        List<CardInfo> cardInfoList = new ArrayList<>();
        cardInfoList.add(cardInfo_s);

        LoanInfo loanInfo = new LoanInfo("MORTGAGE", 100000, 12, PayMethodEnum.AI, "N");
        /*
         房贷信息
        */
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setPropertyNo("168100002");
        houseInfo.setAddress("郑州市互联网金融大厦");
//        houseInfo.setPropertyType(HouseTypeEnum.公寓);
//        houseInfo.setPlannedUse(HousePlanUseEnum.住宅);
//        houseInfo.setArea(600);
        houseInfo.setPropertyProvince("河南省");
        houseInfo.setPropertyCity("郑州市");
        houseInfo.setPropertyDistrict("郑东新区");
        houseInfo.setAccessedValue(18000000);
        houseInfo.setAvailLoanAmount(15000000);
        //经纪人信息
        AgentChannelInfo channelInfo = new AgentChannelInfo();
        channelInfo.setAgentCompanyId("001");
        channelInfo.setAgentCompanyName("大河");
        channelInfo.setAgentId("001");
        channelInfo.setAgentName("张三");
        channelInfo.setAgentMobile("13888888888");
        channelInfo.setChannelId("001");
        channelInfo.setChannelName("大河");
        channelInfo.setChannelPhone("13666666666");
        channelInfo.setGpsProvince("河南省");
        channelInfo.setGpsCity("郑州市");
        channelInfo.setGpsLocation("112,231");
        houseInfo.setAgentChannelInfo(channelInfo);

        /**
         * 门店网点信息
         */
        StoreInfo storeInfo = new StoreInfo("601030101", "org_601030101", "郑州市东风南路");
        storeInfo.setStoreRiskercode("risk001");
        storeInfo.setStoreCity("410100");
        storeInfo.setStoreState("410000");
        storeInfo.setStoreZone("410104");
        storeInfo.setStoreLocation("192.245,231.78");
        storeInfo.setStoreRiskfingerprinting("zhiwen");
        storeInfo.setStoreRisklocation("192.245,231.78");

        ApplyRequest applyRequest = new ApplyRequest("PA201801191600000513", "20180111", "123", "123", SocialIdentityEnum.SI02, "2018011899", personalInfo, cardInfoList,loanInfo);
        applyRequest.setHouseInfo(houseInfo);
        applyRequest.setStoreInfo(storeInfo);
//        api.applyLoan(applyRequest);

//        applyRequest.setOrderInfo(orderInfo);
    }
}
