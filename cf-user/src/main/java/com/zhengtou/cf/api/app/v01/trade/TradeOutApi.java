package com.zhengtou.cf.api.app.v01.trade;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.api.cl.TrialCalApi;
import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.PayMethodEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.request.ApplyRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.TdTypeEnum;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.service.OrgService;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.product.service.ProductService;
import com.zhengtou.cf.risk.service.RiskService;
import com.zhengtou.cf.trade.controller.receiveVO.OrderReciveVO;
import com.zhengtou.cf.trade.controller.receiveVO.QueryTradeReciveVO;
import com.zhengtou.cf.trade.pojo.entity.enums.TradeModelEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.OrderVO;
import com.zhengtou.cf.trade.pojo.vo.TradeVO;
import com.zhengtou.cf.trade.service.BillService;
import com.zhengtou.cf.trade.service.ContractService;
import com.zhengtou.cf.trade.service.OrderService;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.zhengtou.cf.user.pojo.entity.person.PersonalEntity;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.service.ConsumerUserService;
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
@RequestMapping("api/v0.1/trade")
@Api(description = "订单服务")
@Validated
public class TradeOutApi {
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    OrderService orderService;
    @Autowired
    ApplyApi applyApi;
    @Autowired
    TradeService tradeService;
    @Autowired
    BaseDao dao;
    @Autowired
    RiskService riskService;
    @Autowired
    OrgService orgService;
    @Autowired
    TrialCalApi trialCalApi;
    @Autowired
    ContractService contractService;
    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    SmsApi smsApi;

    @RequestMapping(value = "apply/consumer/{token}/{subProductId}/{orgNo}/{loanAmt}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("贷款申请")
    public NetVO apply(@PathVariable String token, @PathVariable long subProductId, @PathVariable String orgNo, @PathVariable String loanAmt,
                       @PathVariable String outTradeFlowNo)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getUserInfoCompleteEnum().equals(UserInfoCompleteEnum.未完善)) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        OrderVO orderVO = orderService.getOrderVOByOutTradeFlowNo(outTradeFlowNo);
        SubProductEntity subProductEntity = productService.getSubProductEntityById(subProductId);
        if (subProductEntity == null) {
            throw new BaseException(RtnResultEnum.E070005);
        }
        String appNo = applyApi.getAppNo(TradeModelEnum.一般进件审批单号);
        String tradeFlowNo = DBUtil.getTradeNo();
        List<TradeVO> tradeVOS = tradeService.findTradeVOByUserIdAndAmtCreateTimeAndNotNullAppNo(userItemVO.getId(), TimeUtil.timeToLong(userItemVO
                .getAmtCreateTime()));
        //首次
        if (tradeVOS == null || tradeVOS.isEmpty()) {
            ApplyRequest applyRequest = tradeService.getApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong(loanAmt), orderVO);
            applyApi.applyLoan(applyRequest);
            tradeService.saveTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, orgNo, null, MoneyUtil.moneyToLong(loanAmt), outTradeFlowNo);
        } else {
            //一月内贷款
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.正在处理)) {
                throw new BaseException(RtnResultEnum.E070011);
            }
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.拒绝)) {
                throw new BaseException(RtnResultEnum.E070016);
            }
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.放弃)) {
                ApplyRequest applyRequest = tradeService.getApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong(loanAmt), orderVO);
                applyApi.applyLoan(applyRequest);
                tradeService.saveTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, orgNo, null, MoneyUtil.moneyToLong(loanAmt), outTradeFlowNo);
            }
            if ((System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) / 1000 <= (30 * 24 * 60 * 60)) {
                tradeService.saveTrade(userItemVO, tradeFlowNo, null, subProductEntity, orgNo, null, MoneyUtil.moneyToLong(loanAmt), outTradeFlowNo);
                if (MoneyUtil.moneyToLong(userItemVO.getCanUseAmt()) < MoneyUtil.moneyToLong(loanAmt)) {
                    tradeService.modifyTradeStatusByOutTradeFlowNo(outTradeFlowNo, TradeStatusEnum.拒绝, null);
                    smsApi.sendSms(userItemVO.getPhone(), "对不起，您申请得贷款已被拒绝");
                    return new SuccFessionVO(RtnResultEnum.E070000);
                } else {
                    tradeService.modifyTradeStatusByOutTradeFlowNo(outTradeFlowNo, TradeStatusEnum.通过, MoneyUtil.moneyToLong(loanAmt));
                    smsApi.sendSms(userItemVO.getPhone(), "恭喜您申请得贷款已审批通过，请您进行合同确认操作");
                }
            } else
            //-月到三月
            if ((30 * 24 * 60 * 60 * 1000) < (System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) && (System
                    .currentTimeMillis() -TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) <= (3 * 30 * 24 * 60 * 60 * 1000)) {
                ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
                riskService.saveRuleRecord(consumerUserEntity, TdTypeEnum.web);
                ApplyRequest applyRequest = tradeService.getApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong(loanAmt), null);
                applyApi.applyLoan(applyRequest);
                tradeService.saveTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, orgNo, null, MoneyUtil.moneyToLong(loanAmt), outTradeFlowNo);
            } else
            if ((System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) > (3 * 30 * 24 * 60 * 60 * 1000)) {
                throw new BaseException(RtnResultEnum.E120000);
            }
        }
        return new SuccFessionVO();
    }

    @RequestMapping(value = "apply/synOrders/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("订单同步")
    public NetVO synOrders(@PathVariable String orgNo, @RequestBody OrderReciveVO orderReciveVO, @PathVariable String outTradeFlowNo)
            throws BaseException {
        OrganizationEntity organizationEntity = orgService.getOrganizaByOrgNo(orgNo);
        if (organizationEntity == null) {
            return new SuccFessionVO(RtnResultEnum.E080000);
        }
        orderService.saveOrder(orderReciveVO, organizationEntity, outTradeFlowNo);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "apply/confirmContract/{token}/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("合同确认")
    public NetVO confirmContract(@PathVariable String token, @PathVariable String outTradeFlowNo)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        TradeVO tradeVO = tradeService.getTradeVOByPhoneAndOutTradeFlowNo(userItemVO.getPhone(), outTradeFlowNo);
        if(tradeVO==null){
            throw new BaseException(RtnResultEnum.E070007);
        }
        if (tradeVO.getFlowStage().equals(TradeStageEnum.审批) && tradeVO.getTradeStatus().equals(TradeStatusEnum.正在处理)) {
            throw new BaseException(RtnResultEnum.E080004);
        }
        if (MoneyUtil.moneyToLong(userItemVO.getCanUseAmt()) < MoneyUtil.moneyToLong(tradeVO.getApprovalAmount())) {
            tradeService.modifyTradeStatusByOutTradeFlowNo(outTradeFlowNo, TradeStatusEnum.拒绝, null);
            throw new BaseException(RtnResultEnum.E070000);
        }
        //保存期供
        LoanItem loanItem = trialCalApi.getLoanCalcuation(tradeVO.getProductCd(), PayMethodEnum.getEnum(tradeVO.getRepayMethod().name()),
                tradeVO.getCycleCnt(), tradeVO.getApprovalAmount());
        //保存合同信息
        contractService.saveContract(tradeVO, contractService.saveContractPdf(userItemVO, dao.get(TradeEntity.class, tradeVO.getTradeId())));
        //保存借据
        billService.saveBill(tradeVO.getContractNo(), loanItem);
        //更改用户可用额度
        consumerUserService.changeConsumerCanUseAmt(userItemVO.getId(), MoneyUtil.moneyToLong(tradeVO.getApprovalAmount()));
        //更改订单信息
        tradeService.modifyTradeFlowStageAndStatusByTradeId(tradeVO.getTradeId(), TradeStageEnum.合同, tradeVO.getZtProductEnum().equals
                (ZtProductEnum.现金贷) ? TradeStatusEnum.打款中 : TradeStatusEnum.商户结算);
        return new SuccFessionVO();
    }

    /**
     * 贷款状态查询
     */
    @RequestMapping(value = "queryApplyStatus/{token}/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("贷款状态查询")
    public NetVO queryApplyStatus(@PathVariable String orgNo, @PathVariable String token, @PathVariable String outTradeFlowNo) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = orgService.getOrganizaByOrgNo(orgNo);
        if (organizationEntity == null) {
            return new SuccFessionVO(RtnResultEnum.E040000);
        }
        TradeVO tradeVO = tradeService.getTradeByOutTradeFlowNo(outTradeFlowNo);
        if (tradeVO == null) {
            return new SuccFessionVO(RtnResultEnum.E070007);
        }
        if (tradeVO.getOrgId() != organizationEntity.getId()) {
            return new SuccFessionVO(RtnResultEnum.E070006);
        }
        return new ResponseVO(tradeVO);
    }

    /**
     * 合同确认信息查询
     */
    @RequestMapping(value = "queryContract/{token}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("合同简要信息查询")
    public NetVO queryTrade(@PathVariable String token, @PathVariable String outTradeFlowNo) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        return new ResponseVO(tradeService.previewContractByOutTradeFlowNo(userItemVO.getId(), outTradeFlowNo));
    }

    /**
     * 贷款列表查询（用户）
     */
    @RequestMapping(value = "queryTrade/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("贷款列表查询（用户）")
    public NetVO queryTrade(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer size) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        QueryTradeReciveVO queryTradeReciveVO = new QueryTradeReciveVO();
        queryTradeReciveVO.setIdnum(personalEntity.getIdNo());
        List<TradeVO> tradeVOList = tradeService.queryTradeList(queryTradeReciveVO, page, size);
        Long count = tradeService.countTradeList(queryTradeReciveVO);
        return new ListResponseVO(tradeVOList, count);
    }
}
