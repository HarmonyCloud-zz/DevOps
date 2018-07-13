package com.zhengtou.cf.api.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.BindCardEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.DrawStatusEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.PayStatusEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response.*;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.NoSmsPayRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.SmsBindCardRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.SmsPayRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.WithDrawRequestVO;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 支付服务功能
 *
 * @author 葛文镇
 */
@Service
public class PayApi {
    private static final Logger logger = LoggerFactory.getLogger(PayApi.class);

    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;

    /******************************************易宝****************************************/
    /**
     * 有短验绑卡申请
     */
    public BindCardEnum authBankCard(String requestno, String cardno, String
            idcardno, String username, String phone, String requesttime) throws BaseException {
        SmsBindCardRequestVO smsBindCardRequest = new SmsBindCardRequestVO(requestno, cardno, idcardno, username, phone, requesttime);
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/smsBindCard", smsBindCardRequest,
                String.class).getBody();
        logger.info("有短验绑卡申请:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return BindCardEnum.valueOf(JSON.parseObject(jo.getString("data"), SmsBindCardResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 有短验绑卡确认
     */
    public BindCardEnum authCheck(String requestNo, String code) throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestno", requestNo);
        params.add("validatecode", code);
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/smsBindCardCheck",
                params, String.class).getBody();
        logger.info("有短验绑卡确认:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return BindCardEnum.valueOf(JSON.parseObject(jo.getString("data"), SmsBindCardCheckResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 绑卡结果查询
     */
    public BindCardEnum queryCheck(String requestNo) throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestno", requestNo);
        params.add("yborderid", "");
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/queryBindCardStatus",
                params, String.class).getBody();
        logger.info("绑卡结果查询:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return BindCardEnum.valueOf(JSON.parseObject(jo.getString("data"), QueryBindCardResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 有短验充值
     */
    public PayStatusEnum smsPay(String requestno, String idCard, String cardtop, String cardlast, String amount, String
            productname, String requesttime, String registtime) throws BaseException {
        SmsPayRequestVO smsPayRequestVO = new SmsPayRequestVO(requestno, idCard, cardtop, cardlast, Double.parseDouble(amount), productname,
                requesttime, registtime);
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/smsPay",
                smsPayRequestVO, String.class).getBody();
        logger.info("有短验充值:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return PayStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), SmsPayResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 有短验充值确认
     */
    public PayStatusEnum smsPayCheck(String requestno) throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestno", requestno);
        params.add("yborderid", "");
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/smsPayCheck",
                params, String.class).getBody();
        logger.info("有短验充值确认:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return PayStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), SmsPayCheckResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 无短验充值
     */
    public PayStatusEnum noSmsPay(String requestno, String idCard, String cardtop, String cardlast, String amount, String
            productname, String requesttime, String registtime) throws BaseException {
        NoSmsPayRequestVO smsPayRequestVO = new NoSmsPayRequestVO(requestno, idCard, Double.parseDouble(amount), productname, cardtop, cardlast,
                registtime, requesttime,"192.168.1.254");
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/noSmsPay",
                smsPayRequestVO, String.class).getBody();
        logger.info("无短验充值:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return PayStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), NoSmsPayResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 充值状态查询
     */
    public PayStatusEnum queryPayStatus(String requestno) throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestno", requestno);
        params.add("yborderid", "");
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/queryBindPayRecord",
                params, String.class).getBody();
        logger.info("充值状态查询:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return PayStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), QueryPayResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 提现
     */
    public DrawStatusEnum withDraw(String requestno, String idCard, String cardtop, String cardlast, String amount, String requesttime) throws
            BaseException {
        WithDrawRequestVO smsPayRequestVO = new WithDrawRequestVO(requestno, idCard, cardtop, cardlast, Double.parseDouble(amount), requesttime);
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/withDraw",
                smsPayRequestVO, String.class).getBody();
        logger.info("提现:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return DrawStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), WithDrawResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 提现状态查询
     */
    public DrawStatusEnum queryDrawStatus(String requestno) throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("requestno", requestno);
        params.add("yborderid", "");
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/queryDrawRecord",
                params, String.class).getBody();
        logger.info("提现状态查询:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return DrawStatusEnum.valueOf(JSON.parseObject(jo.getString("data"), QueryWithDrawResponse.class).getStatus());
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 查询账户余额
     */
    public String drawValidAmount() throws BaseException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/pay/yibao/drawValidAmount",
                params, String.class).getBody();
        logger.info("查询账户余额:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"), QueryDrawValidAmountResponse.class).getValidamount();
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
