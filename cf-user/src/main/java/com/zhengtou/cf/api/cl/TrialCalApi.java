package com.zhengtou.cf.api.cl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.PayMethodEnum;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TNTWithdrawRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNTWithdrawResponse;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 试算api功能
 */
@Service
public class TrialCalApi {
    private static final Logger logger = LoggerFactory.getLogger(TrialCalApi.class);

    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 借款试算
     */
    public LoanItem getLoanCalcuation(String productCd, PayMethodEnum payMethodEnum, int loanTerm, String withDrawAmt) throws BaseException {
        TNTWithdrawRequest tntWithdrawRequest=new TNTWithdrawRequest(productCd,payMethodEnum,loanTerm,withDrawAmt);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/core/loanCalculation", tntWithdrawRequest, String
                .class).getBody();
        logger.info("getLoanCalcuation:"+entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),TNTWithdrawResponse.class).getLoanItem();
        } else{
            if(jo.getString("status").equals("E_001")){
                throw new BaseException(RtnResultEnum.E220000);
            }
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
