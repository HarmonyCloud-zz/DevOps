package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.common.api.outer.cl.apply.request.ApplyRequest;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.trade.controller.receiveVO.QueryTradeReciveVO;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.*;
import com.zhengtou.cf.trade.pojo.vo.app.AppBillVO;
import com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;

import java.util.List;

/**
 * 贷款业务service
 *
 * @author 葛文镇
 */
public interface TradeService {
    /**
     * 审批
     */
    ApplyRequest getApplyRequest(ConsumerUserVO userItemVO, String appNo, SubProductEntity subProductEntity, Long applyAmt, OrderVO orderVO) throws
            BaseException;

    /**
     * 保存订单信息
     */
    void saveTrade(ConsumerUserVO userItemVO,String tradeFlowNo, String appNo, SubProductEntity subProductEntity, String orgNo, String storeNo, Long applyAmt, String
            outTradeFlowNo);

    /**
     * app审批
     */
    ApplyRequest getAppApplyRequest(ConsumerUserVO userItemVO, String appNo, SubProductEntity subProductEntity, Long applyAmt, OrderVO orderVO,
                                    BankCardEntity bankCardEntity) throws BaseException;

    /**
     * 保存app订单信息
     */
    void saveAppTrade(ConsumerUserVO userItemVO,String tradeFlowNo, String appNo, SubProductEntity subProductEntity, String orgNo, Long applyAmt, BankCardEntity
            bankCardEntity);


    /**
     * 根据外部订单号查trade
     */
    TradeVO getTradeByOutTradeFlowNo(String outTradeFlowNo);

    /**
     * 根据订单号查tradeEntity
     */
    TradeEntity getTradeByTradeFlowNo(String tradeFlowNo);

    /**
     * 查询订单列表
     */
    List<TradeVO> queryTradeList(QueryTradeReciveVO queryTradeReciveVO, Integer page, Integer size);

    Long countTradeList(QueryTradeReciveVO queryTradeReciveVO);

    /**
     * 查看用户全部订单列表
     */
    List<AppTradeVO> queryAPPAllTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size);

    Long countAPPAllTradeListByConsumerUserId(long consumerUserId);

    /**
     * 查看用户申请中订单列表
     */
    List<AppTradeVO> queryAPPApplyTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size);

    Long countAPPApplyTradeListByConsumerUserId(long consumerUserId);

    /**
     * 查看用户还款中订单列表
     */
    List<AppTradeVO> queryAPPRepayTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size);

    Long countAPPRepayTradeListByConsumerUserId(long consumerUserId);

    /**
     * 查看用户已完成订单列表
     */
    List<AppTradeVO> queryAPPCompletedTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size);

    Long countAPPCompletedTradeListByConsumerUserId(long consumerUserId);

    AppBillVO getBillVOByTradeId(long tradeId);

    /**
     * 根据手机号、商户编号、外部订单号定位借款订单
     */
    TradeVO getTradeVOByPhoneAndOutTradeFlowNo(String phone, String outTradeFlowNo);

    /**
     * 根据手机号、商户编号、外部订单号定位借款订单
     */
    TradeVO getTradeVOByTradeId(long tradeId);

    /**
     * 根据userId拿所有贷款订单
     */
    List<TradeVO> findTradeVOByUserIdAndAmtCreateTimeAndNotNullAppNo(long userId,long amtCreateTime);

    /**
     * 收款汇总查询
     */
    List<AccountReceiptSummaryVO> queryAccountReceiptSummary(long dateStart, long dateEnd, String orgNo, String orgName);

    /**
     * 打款汇总查询
     */
    List<AccountPaySummaryVO> queryAccountPaySummary(long dateStart, long dateEnd, String orgNo, String orgName);

    /**
     * 需确认合同信息
     */
    ContractVO previewContractByOutTradeFlowNo(long userId, String outTradeFlowNo);

    /**
     * 根据外部订单号，更改订单状态信息
     * 状态|审批金额
     */
    void modifyTradeStatusByOutTradeFlowNo(String outTradeFlowNo, TradeStatusEnum tradeStatus,Long approvalAmount) throws BaseException;

    /**
     * 根据内部订单号，更改订单状态信息
     * 状态|审批金额
     */
    void modifyTradeStatusByTradeFlowNo(String tradeFlowNo, TradeStatusEnum tradeStatus,Long approvalAmount) throws BaseException;
    /**
     * 根据订单id更改订单阶段
     */
    void modifyTradeFlowStageAndStatusByTradeId(long tradeId, TradeStageEnum tradeStageEnum,TradeStatusEnum tradeStatusEnum) throws BaseException;

    /**
     * 根据tradeid拿到apptradevo
     */
    AppTradeVO getAppTradeVOByTradeId(long tradeId);
    /**
     * 提前结清试算
     */
    EarlySettleTrialVO earlySettleTrial(long tradeId) throws BaseException;
}
