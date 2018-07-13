package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.CertificateTypeEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.PayOrgEnum;
import com.zhengtou.cf.common.api.outer.cl.pay.request.AuthApplyToChannelSmsRequest;
import com.zhengtou.cf.common.api.outer.cl.pay.request.AuthConfirmCheckSmsRequest;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request.SmsBindCardRequest;
import com.zhengtou.cf.common.utils.DateUtils;
import com.zhengtou.cf.common.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class BankcardApiTest {

    @Test
    public void authBankCard() throws Exception {
        AuthApplyToChannelSmsRequest request = new AuthApplyToChannelSmsRequest();

        request.setBankCode(BankCodeEnum.中国银行.getCode());
        request.setPayOrg(PayOrgEnum.BOLT);
        request.setCardNo("6214833801798170");
        request.setIdNo("410181199811146535");
        request.setIdType(CertificateTypeEnum.I);
        request.setMobile("13393705178");
        request.setCardName("马孝辉");

    }

    @Test
    public void authCheck() throws Exception {
        AuthConfirmCheckSmsRequest request = new AuthConfirmCheckSmsRequest();
        request.setBankCode(BankCodeEnum.中国银行.getCode());
        request.setPayOrg(PayOrgEnum.BOLT);
        request.setCardNo("6214833801798170");
        request.setIdNo("410181199811146535");
        request.setIdType(CertificateTypeEnum.I);
        request.setMobile("13393705178");
        request.setCardName("马孝辉");
        request.setSmsCode("123456");

    }

    @Test
    public void authBankCardY(){
        String requestno = StringUtils.getExtSerialNum();
        System.out.println(requestno);
        SmsBindCardRequest smsBindCardRequest = new SmsBindCardRequest(requestno, "111",
                "6216613400005898140", "410881199012263158", "葛文镇",
                "18521785060", DateUtils.getSysDateTimeString());
    }
}
