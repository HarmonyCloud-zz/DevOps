package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.api.cl.TrialCalApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.ExSystemEnum;
import com.zhengtou.cf.common.api.outer.cl.cls.request.RepayOnlineRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TFCAdvSettleRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TNTAdvSettleRequest;
import com.zhengtou.cf.common.utils.DBUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class PayApiTest {

    @Autowired
    TrialCalApi api;

    /**
     * 在线还款
     *
     * @throws Exception
     */
    @Test
    public void repayOnline() throws Exception {

        RepayOnlineRequest repayOnlineRequest = new RepayOnlineRequest( "",
                "", DBUtil.generateID(),"","");
//        TNQContractResponse contractResponse = (TNQContractResponse) map.get("contract");
//        RepayOnlineRequest repayOnlineRequest = (RepayOnlineRequest) map.get("repayOnlineRequest");

        repayOnlineRequest.setBillNo("20180118693000783516");
        repayOnlineRequest.setContrNo("1907855218574361605");
        repayOnlineRequest.setRepayAmt("200");
    }


    @Test
    public void settle() throws Exception {

        TFCAdvSettleRequest advSettleRequest = new TFCAdvSettleRequest("1907855218574361605", "20180118693000783516", ExSystemEnum.BMP,
                "20180118613800577679");

    }

    @Test
    public void settleCalcu() throws Exception {

        TNTAdvSettleRequest advSettleRequest = new TNTAdvSettleRequest();
        advSettleRequest.setBillNo("20180118693000783516");
        advSettleRequest.setContrNo("1907855218574361605");
    }

}
