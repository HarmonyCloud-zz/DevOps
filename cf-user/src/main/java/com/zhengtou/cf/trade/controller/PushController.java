package com.zhengtou.cf.trade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("push")
public class PushController{
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);
    @Autowired
    BaseDao dao;
    @Autowired
    SmsApi smsApi;

    /**
     * 保存审批信息
     * @return
     */
    @RequestMapping(value = "approvel", method = RequestMethod.POST)
    public Object saveApprovelPush(@RequestParam String param) {
        logger.info("approvel push info:" + param);
        try {
            JSONObject object = JSONObject.parseObject(param);
            String applyNo = object.getString("reqId");
            TradeEntity tradeEntity=dao.get(" from TradeEntity t where t.appNO=?0",new Object[]{applyNo});
            String status=object.getString("status");
            if (status.equals("U")) {
                return JSON.parseObject("{\n" +
                        "  \"status\": \"200\",\n" +
                        "  \"msg\": \"请求成功\",\n" +
                        "  \"data\": {}\n" +
                        "}");
            } else if (status.equals("A")) {
            } else if (status.equals("J")) {
                tradeEntity.setTradeStatus(TradeStatusEnum.放弃);
            } else {
                tradeEntity.setTradeStatus(TradeStatusEnum.拒绝);
            }
            dao.modify(tradeEntity);
            //保存审批信息
            return JSON.parseObject("{\n" +
                    "  \"status\": \"200\",\n" +
                    "  \"msg\": \"请求成功\",\n" +
                    "  \"data\": {}\n" +
                    "}");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorFessionVO();
        }
    }


    @RequestMapping(value = "approvalResult", method = RequestMethod.POST)
    public NetVO saveApprovalResult(@RequestParam String idNo, @RequestParam String type, @RequestParam String status) {
        try {
            logger.info("save approval result:" );
            return new SuccFessionVO();
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorFessionVO();
        }
    }
}
