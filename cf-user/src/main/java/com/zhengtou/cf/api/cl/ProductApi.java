package com.zhengtou.cf.api.cl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.api.outer.cl.cls.request.QueryProductRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.request.TNQProductGroupRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNQProductGroupResponse;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNQProductResponse;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import com.zhengtou.cf.enums.ZtProductEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 产品功能
 * @author 葛文镇
 */
@Service
public class ProductApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductApi.class);

    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取产品组列表
     */
    public TNQProductGroupResponse queryProductGroup(ZtProductEnum productEnum) throws BaseException {
        TNQProductGroupRequest tnqProductGroupRequest=new TNQProductGroupRequest(productEnum.getCode());
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf()+"/core/queryProductGroup", tnqProductGroupRequest, String.class).getBody();
        logger.info("queryProductGroup:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),TNQProductGroupResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 获取产品列表
     */
    public TNQProductResponse getProductList(String productCd) throws BaseException {
        QueryProductRequest request = new QueryProductRequest(productCd);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/core/queryProduct", request, String.class).getBody();
        logger.info(entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),TNQProductResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

}
