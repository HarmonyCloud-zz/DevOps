package com.zhengtou.cf.api.cl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.ApplyFormTypeEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.request.*;
import com.zhengtou.cf.common.api.outer.cl.apply.response.*;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import com.zhengtou.cf.trade.pojo.entity.enums.TradeModelEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 借款申请api功能
 */
@Service
public class ApplyApi {
    private static final Logger logger = LoggerFactory.getLogger(ApplyApi.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApiConfig apiConfig;

    /**
     * 提交申请单
     */
    public ApplyResponse applyLoan(ApplyRequest applyVO) throws BaseException {
        logger.info(JSONObject.toJSONString(applyVO));
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/apply", applyVO, String.class).getBody();
        logger.info("applyLoan:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            ApplyResponse applyResponse=JSON.parseObject(jo.getString("data"),ApplyResponse.class);
            return applyResponse;
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 获取申请单编号
     */
    public String getAppNo(TradeModelEnum tradeModelEnum) throws BaseException {
        BuildAppNoRequest request = new BuildAppNoRequest(ApplyFormTypeEnum.getEnum(tradeModelEnum.name()));
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/buildAppNo", request, String.class).getBody();
        logger.info("getAppNo:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            BuildAppNoResponse buildAppNoResponse=JSON.parseObject(jo.getString("data"),BuildAppNoResponse.class);
            return buildAppNoResponse.getAppNo();
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
    /**
     * 申请单取消
     */
    public boolean cancelApply(String appNo) throws BaseException {
        ApplyCancelRequest applyCancelRequest = new ApplyCancelRequest(appNo);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/applyCancel", applyCancelRequest, String.class).getBody();
        logger.info("applyCancel:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return true;
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
    /**
     * 申请单状态查询
     */
    public ApplyStatusQueryResponse queryApplyStatus(String appNo) throws BaseException {
        ApplyStatusQueryRequest applyStatusQueryRequest = new ApplyStatusQueryRequest(appNo);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/queryApplyStatus", applyStatusQueryRequest, String.class).getBody();
        logger.info("queryApplyStatus:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),ApplyStatusQueryResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 申请单详情查询
     */
    public ApplyDetailQueryResponse queryApplyDetail(String appNo) throws BaseException {
        ApplyDetailQueryRequest applyDetailQueryRequest = new ApplyDetailQueryRequest(appNo);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/applyDetailQuery", applyDetailQueryRequest, String.class).getBody();
        logger.info("queryApplyDetail:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),ApplyDetailQueryResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 申请单列表查询
     */
    public ApplyListQueryResponse queryApplyList(String appNo, String userId) throws BaseException {
        ApplyListQueryRequest applyListQueryRequest = new ApplyListQueryRequest(appNo,userId);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/applyListQuery", applyListQueryRequest, String.class).getBody();
        logger.info("queryApplyList:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),ApplyListQueryResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 白名单查询
     */
    public WhiteCheckResponse queryWhite(String idNo) throws BaseException {
        WhiteCheckRequest whiteCheckRequest = new WhiteCheckRequest(idNo);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/whiteCheck", whiteCheckRequest, String.class).getBody();
        logger.info("queryWhite:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return JSON.parseObject(jo.getString("data"),WhiteCheckResponse.class);
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 确认合同
     */
    public boolean contract(String appNo) throws BaseException {
        ContractRequest contractRequest = new ContractRequest(appNo);
        String entity = restTemplate.postForEntity(apiConfig.getClApiConf() + "/apply/contract", contractRequest, String.class).getBody();
        logger.info("contract:" + entity);
        JSONObject jo= JSON.parseObject(entity);
        if("200".equals(jo.getString("code")) && "SUCCESS".equals(jo.getString("status"))) {
            return true;
        } else{
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
