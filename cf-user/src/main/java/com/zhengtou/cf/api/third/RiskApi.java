package com.zhengtou.cf.api.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.ResultDescVO;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import com.zhengtou.cf.enums.TdTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 风控服务api
 * @author 葛文镇
 */
@Service
public class RiskApi {
    private static final Logger logger = LoggerFactory.getLogger(RiskApi.class);

    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;
/*************************************************同盾********************************************************/
    /**
     * 贷前审批结果
     */
    public ResultDescVO risk(String id_number , String account_mobile, String account_name, TdTypeEnum tdTypeEnum) throws BaseException {
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("id_number",id_number);
        params.add("account_mobile",account_mobile);
        params.add("account_name",account_name);
        params.add("tdTypeEnum",tdTypeEnum.name());
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf()+"/risk/getTdApproval", params, String.class).getBody();
        logger.info("贷前审批结果:" + entity);
        JSONObject jo = JSON.parseObject(entity);
        if ("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),TDApiResponse.class).getResult_desc();
        } else {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
