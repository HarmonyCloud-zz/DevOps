package com.zhengtou.cf.thirdOperator.controller;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.thirdOperator.controller.lvyue.RepayItemReceiveVO;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdRepaymentEntity;
import com.zhengtou.cf.thirdOperator.service.ZtOrderService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("thirdTrade")
public class TradeAPI {

    private static Log log = LogFactory.getLog(TradeAPI.class);

    @Autowired
    BaseDao dao;
    @Autowired
    ZtOrderService ztOrderService;

    @RequestMapping(value = "tradeInfo", method = RequestMethod.POST)
    @ApiOperation(value = "订单信息同步")
    public NetVO saveTradeInfo(@RequestBody String tradeInfo) {
        try {
            log.info("save tradeInfo result:" + tradeInfo);
            return new SuccFessionVO();
        } catch (Exception e) {
            return new ErrorFessionVO("false", e.getMessage());
        }
    }

    @RequestMapping(value = "payItem", method = RequestMethod.POST)
    @ApiOperation(value = "还款记录同步")
    public NetVO savePayItem(@RequestBody String param) {
        log.info("save pay result:" + param);
        RepayItemReceiveVO repayItemReceiveVO = JSON.parseObject(param, RepayItemReceiveVO.class);

        ThirdOrderEntity thirdOrderEntity = dao.get("from ThirdOrderEntity t where t.isDeleted=false and t.thirdOrderId=?0", new
                Object[]{repayItemReceiveVO.getOrder_id()});
        if (thirdOrderEntity == null) {
            return new SuccFessionVO("false", "订单不存在");
        }
        ThirdRepaymentEntity thirdRepaymentEntity = dao.get("from ThirdRepaymentEntity t where t.isDeleted=false and t.thirdRepaymentId=?0", new
                Object[]{repayItemReceiveVO.getStages_id()});
        if(thirdRepaymentEntity!=null){
            return new SuccFessionVO("false", "还款记录id重复");
        }
        ThirdRepaymentEntity thirdRepayment = new ThirdRepaymentEntity();
        thirdRepayment.setAmountAlready(MoneyUtil.moneyToLong(repayItemReceiveVO.getPay_time()));
        thirdRepayment.setAmountNeed(MoneyUtil.moneyToLong(repayItemReceiveVO.getPayable()));
        thirdRepayment.setNumber(repayItemReceiveVO.getNow_term());
        thirdRepayment.setPayTime(TimeUtil.timeToLong(repayItemReceiveVO.getPay_time()));
        thirdRepayment.setThirdCreateTime(TimeUtil.timeToLong(repayItemReceiveVO.getPay_time()));
        thirdRepayment.setThirdLastPaytime(TimeUtil.timeToLong(repayItemReceiveVO.getRepayment_time()));
        thirdRepayment.setThirdOrder(thirdOrderEntity);
        thirdRepayment.setThirdRepaymentId(repayItemReceiveVO.getStages_id());
        dao.save(thirdRepayment);
        try {
            ztOrderService.changeZtOrderByThirdOrderId(repayItemReceiveVO.getOrder_id(), repayItemReceiveVO.getNow_term());
        } catch (BaseException e) {
            log.info("订单号：" + repayItemReceiveVO.getOrder_id() + ",未进行发标操作，还款期数为：" + repayItemReceiveVO.getNow_term());
        }
        return new SuccFessionVO();
    }
}
