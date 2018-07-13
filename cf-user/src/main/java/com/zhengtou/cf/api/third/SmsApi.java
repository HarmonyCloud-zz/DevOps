package com.zhengtou.cf.api.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
 * 短信服务api
 * @author 葛文镇
 */
@Service
public class SmsApi {
    private static final Logger logger = LoggerFactory.getLogger(SmsApi.class);

    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 亿美短信
     */
    public boolean sendSms(String phone ,String content) throws BaseException {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("phone",phone);
        params.add("content",content);
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf()+"/sms/yimei/sendSms", params, String.class).getBody();
        logger.info("亿美短信:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return true;
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
