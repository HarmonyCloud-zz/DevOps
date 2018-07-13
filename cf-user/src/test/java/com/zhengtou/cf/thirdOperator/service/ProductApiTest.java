package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.api.cl.ProductApi;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.WithDrawReq;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TNTWithdrawRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Api测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class ProductApiTest {

    @Autowired
    ProductApi productApi;

    @Test
    public void testGetProductList() throws Exception {

    }

    @Test
    public void testLoanCalculation() throws Exception {
        TNTWithdrawRequest tntWithdraw = new TNTWithdrawRequest();
        tntWithdraw.setRange("B");
        tntWithdraw.setProductCd("CASH");
        WithDrawReq drawReq = new WithDrawReq();
        drawReq.setRepayMethod("AI");
        drawReq.setLoanTerm(3);
        drawReq.setWithdrawAmt(1000);
//        AgreementReq agreementReq = new AgreementReq();
//        agreementReq.setInterestRate(0.28);
//        agreementReq.setInterestRatePeriod("Y");
//        tntWithdraw.setAgreementReq(agreementReq);
        tntWithdraw.setWithdrawReq(drawReq);
    }
}
