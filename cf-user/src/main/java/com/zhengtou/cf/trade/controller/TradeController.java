package com.zhengtou.cf.trade.controller;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.product.service.ProductService;
import com.zhengtou.cf.risk.service.RiskService;
import com.zhengtou.cf.trade.controller.receiveVO.QueryTradeReciveVO;
import com.zhengtou.cf.trade.pojo.vo.*;
import com.zhengtou.cf.trade.service.ContractService;
import com.zhengtou.cf.trade.service.TermService;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.user.pojo.vo.BackUserVO;
import com.zhengtou.cf.common.utils.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单服务
 */
@RestController
@RequestMapping("trade")
@Api(description = "订单服务")
@Validated
public class TradeController {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    ApplyApi applyApi;
    @Autowired
    TradeService tradeService;
    @Autowired
    RiskService riskService;
    @Autowired
    ContractService contractService;
    @Autowired
    TermService termService;
    @Autowired
    ProductService productService;
    @Autowired
    PayApi payApi;

    /**
     * 贷款列表查询
     */
    @RequestMapping(value = "queryTradeBack/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("贷款列表查询（后台）")
    public NetVO queryTradeBack(@PathVariable String token, QueryTradeReciveVO queryTradeReciveVO, @PathVariable Integer page,
                                @PathVariable Integer size) {
        BackUserVO userItemVO = (BackUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<TradeVO> tradeVOList = tradeService.queryTradeList(queryTradeReciveVO, page, size);
        Long count = tradeService.countTradeList(queryTradeReciveVO);
        return new ListResponseVO(tradeVOList, count);
    }

    /**
     * 打款记录
     */
    @RequestMapping(value = "queryPayment/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("打款记录查询")
    public NetVO queryPayment(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer size) {
        BackUserVO userItemVO = (BackUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<PaymentVO> paymentVOS = contractService.getPaymentList(page, size);
        Long count = contractService.countPayment();
        return new ListResponseVO(paymentVOS, count);
    }

    /**
     * 收款记录
     */
    @RequestMapping(value = "queryReceipt/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("收款记录查询")
    public NetVO queryReceipt(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer size) {
        BackUserVO userItemVO = (BackUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<ReceiptVO> receiptVOS = termService.getReceiptList(page, size);
        Long count = termService.countReceipt();
        return new ListResponseVO(receiptVOS, count);
    }

    /**
     * 还款记录查询 by 贷款订单
     */
    @RequestMapping(value = "queryReceiptByTrade/{tradeId}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("还款记录查询 by 贷款订单")
    public NetVO queryReceiptByTrade(@PathVariable long tradeId, @PathVariable Integer page, @PathVariable Integer size) {
        List<ReceiptVO> receiptVOS = termService.getReceiptByTradeId(tradeId,page,size);
        Long count=termService.countReceiptByTradeId(tradeId);
        return new ListResponseVO(receiptVOS,count);
    }

    /**
     * 账户余额查询
     */
    @RequestMapping(value = "queryAccountBalance", method = RequestMethod.GET)
    @ApiOperation("账户余额查询")
    public NetVO queryAccountBalance() throws BaseException {
        return new StringResponseVO(payApi.drawValidAmount());
    }

    /**
     * 账户出帐汇总
     */
    @RequestMapping(value = "accountPaySummary", method = RequestMethod.GET)
    @ApiOperation("账户出帐汇总")
    public NetVO accountPaySummary(@RequestParam String dateStart, @RequestParam String dateEnd, String orgNo,String orgName) throws BaseException {
        List<AccountPaySummaryVO> accountPaySummaryVOs = tradeService.queryAccountPaySummary(TimeUtil.dateToLong(dateStart), TimeUtil.dateToLong(dateEnd),
                orgNo,orgName);
        return new ListResponseVO(accountPaySummaryVOs);
    }

    /**
     * 账户收款汇总
     */
    @RequestMapping(value = "accountReceiptSummary", method = RequestMethod.GET)
    @ApiOperation("账户收款汇总")
    public NetVO accountReceiptSummary(@RequestParam String dateStart, @RequestParam String dateEnd, String orgNo,String orgName) throws BaseException {
        List<AccountReceiptSummaryVO> accountReceiptSummaryVOs = tradeService.queryAccountReceiptSummary(TimeUtil.dateToLong(dateStart), TimeUtil
                .dateToLong(dateEnd), orgNo,orgName);
        return new ListResponseVO(accountReceiptSummaryVOs);
    }
}
