package com.zhengtou.cf.api.app.v02.trade;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.api.cl.TrialCalApi;
import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.PayMethodEnum;
import com.zhengtou.cf.common.api.outer.cl.apply.request.ApplyRequest;
import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
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
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;
import com.zhengtou.cf.trade.pojo.entity.bill.TermEntity;
import com.zhengtou.cf.trade.pojo.entity.contract.ContractEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.TradeModelEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.EarlySettleTrialVO;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.pojo.vo.ReceiptVO;
import com.zhengtou.cf.trade.pojo.vo.TradeVO;
import com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO;
import com.zhengtou.cf.trade.service.*;
import com.zhengtou.cf.user.pojo.entity.AnnexEntity;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.pojo.vo.person.PersonalVO;
import com.zhengtou.cf.user.service.ConsumerUserService;
import com.zhengtou.cf.util.FileUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单服务
 */
@RestController("trade_v0.2")
@RequestMapping("api/v0.2/trade")
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
    @Autowired
    TermService termService;
    @Autowired
    PayApi payApi;
    @Value("${person.annex.path}")
    private String path;
    @Value("${show.person.annex.path}")
    private String getPath;

    @RequestMapping(value = "apply/consumer/{token}/{subProductId}/{loanAmt}/{cardId}", method = RequestMethod.POST)
    @ApiOperation("贷款申请")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "贷款申请流水号")})
    public NetVO apply(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "子产品id", name =
            "subProductId") long subProductId, @PathVariable @ApiParam(value = "借款金额（元为单位）", name = "loanAmt") String loanAmt, @PathVariable
                       @ApiParam(value = "借款银行卡id", name = "cardId") long cardId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getUserInfoCompleteEnum().equals(UserInfoCompleteEnum.未完善)) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        SubProductEntity subProductEntity = productService.getSubProductEntityById(subProductId);
        if (subProductEntity == null) {
            throw new BaseException(RtnResultEnum.E070005);
        }
        BankCardEntity bankCardEntity = dao.get(BankCardEntity.class, cardId);
        if (bankCardEntity == null) {
            throw new BaseException(RtnResultEnum.E020001);
        }
        String appNo = applyApi.getAppNo(TradeModelEnum.一般进件审批单号);
        String tradeFlowNo = DBUtil.getTradeNo();
        List<TradeVO> tradeVOS = tradeService.findTradeVOByUserIdAndAmtCreateTimeAndNotNullAppNo(userItemVO.getId(), TimeUtil.timeToLong(userItemVO
                .getAmtCreateTime()));
        OrganizationEntity ztorg = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        //首次
        if (tradeVOS == null || tradeVOS.isEmpty()) {
            ApplyRequest applyRequest = tradeService.getAppApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong(loanAmt), null,
                    bankCardEntity);
            applyApi.applyLoan(applyRequest);
            tradeService.saveAppTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, ztorg.getOrgNo(), MoneyUtil.moneyToLong(loanAmt),
                    bankCardEntity);
        } else {
            //一月内贷款
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.正在处理)) {
                throw new BaseException(RtnResultEnum.E070011);
            }
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.拒绝)) {
                throw new BaseException(RtnResultEnum.E070016);
            }
            if (tradeVOS.get(0).getTradeStatus().equals(TradeStatusEnum.放弃)) {
                ApplyRequest applyRequest = tradeService.getAppApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong(loanAmt), null,
                        bankCardEntity);
                applyApi.applyLoan(applyRequest);
                tradeService.saveAppTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, ztorg.getOrgNo(), MoneyUtil.moneyToLong(loanAmt),
                        bankCardEntity);
            }
            if ((System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) / 1000 <= (30 * 24 * 60 * 60l)) {
                tradeService.saveAppTrade(userItemVO, tradeFlowNo, null, subProductEntity, ztorg.getOrgNo(), MoneyUtil.moneyToLong(loanAmt),
                        bankCardEntity);
                if (MoneyUtil.moneyToLong(userItemVO.getCanUseAmt()) < MoneyUtil.moneyToLong(loanAmt)) {
                    tradeService.modifyTradeStatusByTradeFlowNo(tradeFlowNo, TradeStatusEnum.拒绝, null);
                    smsApi.sendSms(userItemVO.getPhone(), "对不起，您申请得贷款已被拒绝");
                    return new SuccFessionVO(RtnResultEnum.E070000);
                } else {
                    tradeService.modifyTradeStatusByTradeFlowNo(tradeFlowNo, TradeStatusEnum.通过, MoneyUtil.moneyToLong(loanAmt));
                    smsApi.sendSms(userItemVO.getPhone(), "恭喜您申请得贷款已审批通过，请您进行合同确认操作");
                }
            } else
                //-月到三月
                if ((30 * 24 * 60 * 60 * 1000l < (System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime()))) && ((System
                        .currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime())) <= 3 * 30 * 24 * 60 * 60 * 1000l)) {
                    ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
                    riskService.saveRuleRecord(consumerUserEntity, TdTypeEnum.web);
                    ApplyRequest applyRequest = tradeService.getAppApplyRequest(userItemVO, appNo, subProductEntity, MoneyUtil.moneyToLong
                            (loanAmt), null, bankCardEntity);
                    applyApi.applyLoan(applyRequest);
                    tradeService.saveAppTrade(userItemVO, tradeFlowNo, appNo, subProductEntity, ztorg.getOrgNo(), MoneyUtil.moneyToLong(loanAmt),
                            bankCardEntity);
                } else if (System.currentTimeMillis() - TimeUtil.timeToLong(userItemVO.getAmtCreateTime()) > (3 * 30 * 24 * 60 * 60 * 1000l)) {
                    throw new BaseException(RtnResultEnum.E120000);
                }
        }
        return new StringResponseVO(tradeFlowNo);
    }

    @RequestMapping(value = "apply/annex/{token}/{tradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("提交贷款附件")
    @ApiResponses({@ApiResponse(code = 200, response = AnnexVO.class, message = "贷款附件返回")})
    public NetVO confirmContract(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款申请流水号",
            name = "tradeFlowNo") String tradeFlowNo, @RequestParam @ApiParam(value = "上传文件集合", name = "files") List<MultipartFile> files,
                                 @RequestParam
                                 @ApiParam(value = "上传附件类型：" +
                                         "   *公安照片(\"gongan\"),\n" +
                                         "    *申请人照片(\"apply_person\"),\n" +
                                         "    *身份证正面(\"card_positive\"),\n" +
                                         "    *身份证反面(\"card_back\"),\n" +
                                         "    *用户图像(\"image\"),\n" +
                                         "    *社保卡(\"social_insurance\"),\n" +
                                         "    *芝麻分(\"ali_fraction\"),\n" +
                                         "    *认证图像(\"certified\"),\n" +
                                         "    *居住证明(\"live\"),\n" +
                                         "    *房产登记证或房产使用权证明(\"estate\"),\n" +
                                         "    *本人照片(\"user\"),\n" +
                                         "    *补充照片(\"supple\"),\n" +
                                         "    *手持身份证半身照(\"hand_card\"),\n" +
                                         "    *FACE第一张(\"FACE1\"),\n" +
                                         "    *FACE第二张(\"FACE2\"),\n" +
                                         "    *FACE第三张(\"FACE3\"),\n" +
                                         "    *FACE第四张(\"FACE4\"),\n" +
                                         "    *与SA的全身合影(\"HY\"),\n" +
                                         "    *代扣银行卡(\"bankcard\"),\n" +
                                         "    *购货小票(\"shop_certified\"),\n" +
                                         "    *授权文件(\"authori\"),\n" +
                                         "    *授权承诺书(\"authori_letter\"),\n" +
                                         "    *借款人告知书(\"borrow_notice\"),\n" +
                                         "    *延期协议(\"extension_agreement\"),\n" +
                                         "    *贷款合同(\"contract\"),\n" +
                                         "    *头像(\"avator\")", name = "annexTypeEnum") AnnexTypeEnum annexTypeEnum) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        TradeEntity tradeEntity = tradeService.getTradeByTradeFlowNo(tradeFlowNo);
        if (tradeEntity == null) {
            throw new BaseException(RtnResultEnum.E070007);
        }
        if (files.isEmpty()) {
            throw new BaseException(RtnResultEnum.U000000);
        }
        String filePath = path + userItemVO.getUserNo() + "/" + tradeFlowNo + "/";
        List<AnnexVO> annexVOS = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = FileUtil.saveFile(file, filePath, annexTypeEnum);
            AnnexEntity annexEntity = new AnnexEntity();
            annexEntity.setTrade(tradeEntity);
            annexEntity.setAnnexTypeEnum(annexTypeEnum);
            annexEntity.setUrl(getPath + userItemVO.getUserNo() + "/" + tradeFlowNo + "/" + fileName);
            annexEntity.setAnnexCode(DBUtil.getAnnexCode(tradeFlowNo + "", annexTypeEnum));
            dao.save(annexEntity);
            annexVOS.add(new AnnexVO(annexTypeEnum, filePath + fileName));
        }
        return new ListResponseVO(annexVOS);
    }

    @RequestMapping(value = "apply/confirmContract/{token}/{tradeId}", method = RequestMethod.POST)
    @ApiOperation("合同确认")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "合同确认返回")})
    public NetVO confirmContract(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款订单id",
            name = "tradeId") long tradeId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        TradeVO tradeVO = tradeService.getTradeVOByTradeId(tradeId);
        if (tradeVO == null) {
            throw new BaseException(RtnResultEnum.E070007);
        }
        if (tradeVO.getFlowStage().equals(TradeStageEnum.审批) && tradeVO.getTradeStatus().equals(TradeStatusEnum.正在处理)) {
            throw new BaseException(RtnResultEnum.E080004);
        }
        if (tradeVO.getFlowStage().equals(TradeStageEnum.审批) && tradeVO.getTradeStatus().equals(TradeStatusEnum.拒绝)) {
            throw new BaseException(RtnResultEnum.E080005);
        }
        if (tradeVO.getFlowStage().equals(TradeStageEnum.审批) && tradeVO.getTradeStatus().equals(TradeStatusEnum.放弃)) {
            throw new BaseException(RtnResultEnum.E080006);
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
    @RequestMapping(value = "queryApplyStatus/{token}/{tradeId}", method = RequestMethod.GET)
    @ApiOperation("贷款状态查询")
    @ApiResponses({@ApiResponse(code = 200, response = TradeVO.class, message = "贷款信息返回")})
    public NetVO queryApplyStatus(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款订单id",
            name = "tradeId") long tradeId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        TradeVO tradeVO = tradeService.getTradeVOByTradeId(tradeId);
        if (tradeVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        return new ResponseVO(tradeVO);
    }

    /**
     * 贷款列表查询-全部
     */
    @RequestMapping(value = "queryAllTrade/{token}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("贷款列表查询-全部")
    @ApiResponses({@ApiResponse(code = 200, response = AppTradeVO.class, message = "贷款列表查询-全部返回")})
    public NetVO queryAllTrade(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "页数", name
            = "page") Integer page, @PathVariable @ApiParam(value = "每页显示数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<AppTradeVO> appTradeVOS = tradeService.queryAPPAllTradeListByConsumerUserId(userItemVO.getId(), page, size);
        Long count = tradeService.countAPPAllTradeListByConsumerUserId(userItemVO.getId());
        return new ListResponseVO(appTradeVOS, count);
    }

    /**
     * 贷款列表查询-申请中
     */
    @RequestMapping(value = "queryApplyTrade/{token}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("贷款列表查询-申请中")
    @ApiResponses({@ApiResponse(code = 200, response = AppTradeVO.class, message = "贷款列表查询-申请中返回")})
    public NetVO queryApplyTrade(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "页数", name
            = "page") Integer page, @PathVariable @ApiParam(value = "每页显示数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<AppTradeVO> appTradeVOS = tradeService.queryAPPApplyTradeListByConsumerUserId(userItemVO.getId(), page, size);
        Long count = tradeService.countAPPApplyTradeListByConsumerUserId(userItemVO.getId());
        return new ListResponseVO(appTradeVOS, count);
    }

    /**
     * 贷款列表查询-还款中
     */
    @RequestMapping(value = "queryRepayTrade/{token}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("贷款列表查询-还款中")
    @ApiResponses({@ApiResponse(code = 200, response = AppTradeVO.class, message = "贷款列表查询-还款中返回")})
    public NetVO queryRepayTrade(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "页数", name
            = "page") Integer page, @PathVariable @ApiParam(value = "每页显示数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<AppTradeVO> appTradeVOS = tradeService.queryAPPRepayTradeListByConsumerUserId(userItemVO.getId(), page, size);
        Long count = tradeService.countAPPRepayTradeListByConsumerUserId(userItemVO.getId());
        return new ListResponseVO(appTradeVOS, count);
    }

    /**
     * 贷款列表查询-已完成
     */
    @RequestMapping(value = "queryCompletedTrade/{token}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("贷款列表查询-已完成")
    @ApiResponses({@ApiResponse(code = 200, response = AppTradeVO.class, message = "贷款列表查询-已完成返回")})
    public NetVO queryCompletedTrade(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "页数", name
            = "page") Integer page, @PathVariable @ApiParam(value = "每页显示数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<AppTradeVO> appTradeVOS = tradeService.queryAPPCompletedTradeListByConsumerUserId(userItemVO.getId(), page, size);
        Long count = tradeService.countAPPCompletedTradeListByConsumerUserId(userItemVO.getId());
        return new ListResponseVO(appTradeVOS, count);
    }

    /**
     * 还款计划查看
     */
    @RequestMapping(value = "queryRepayPlan/{token}/{tradeId}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("还款计划查看")
    @ApiResponses({@ApiResponse(code = 200, response = ReceiptVO.class, message = "还款计划查看返回")})
    public NetVO queryRepayPlan(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款订单id",
            name = "tradeId") long tradeId, @PathVariable @ApiParam(value = "页数", name = "page") Integer page, @PathVariable @ApiParam(value =
            "每页显示数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<ReceiptVO> receiptVOS = termService.getReceiptByTradeId(tradeId, page, size);
        Long count = termService.countReceiptByTradeId(tradeId);
        return new ListResponseVO(receiptVOS, count);
    }

    /**
     * 主动还款
     */
    @RequestMapping(value = "repayLoan/{token}/{termId}/{cardId}", method = RequestMethod.GET)
    @ApiOperation("主动还款")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "主动还款返回")})
    public NetVO repayLoan(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "当前期供id", name =
            "termId") long termId, @PathVariable @ApiParam(value = "还款卡id", name = "cardId") long cardId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        TermEntity term = dao.get(TermEntity.class, termId);
        if (term.getTermStatus() == TermStatusEnum.还款中 || term.getTermStatus() == TermStatusEnum.已还款) {
            throw new BaseException(RtnResultEnum.E070017);
        }
        BillEntity bill = term.getBill();
        if (!bill.getTermNo().equals(term.getTermNo())) {
            throw new BaseException(RtnResultEnum.E070018);
        }
        String transacNo = DBUtil.getTransacNo();
        BankCardEntity bankCardEntity = dao.get(BankCardEntity.class, cardId);
        String cardNo = bankCardEntity.getCardNo();
        ReceiptTaskVO receiptTaskVO = termService.getReceiptByTermId(termId);
        try {
            payApi.noSmsPay(transacNo, receiptTaskVO.getIdCard(), cardNo.substring(0, 6), cardNo.substring(cardNo.length()
                    - 4, cardNo.length()), MoneyUtil.moneyToString(term.getSchdAmt()), receiptTaskVO.getOrgName(), TimeUtil
                    .timeToString(System.currentTimeMillis()), TimeUtil.timeToString(receiptTaskVO.getUserCretime()));
        } catch (BaseException e) {
            termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.扣款失败, transacNo, e.getErrorCode(), e.getErrorMsg());
        }
        termService.changeTermStatus(receiptTaskVO.getTermId(), TermStatusEnum.还款中, transacNo, "", "");
        return new SuccFessionVO();
    }

    /**
     * 提前结清
     */
    @RequestMapping(value = "earlySettle/{token}/{tradeId}", method = RequestMethod.GET)
    @ApiOperation("提前结清")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "提前结清返回")})
    public NetVO earlySettle(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款订单id", name =
            "tradeId") long tradeId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        EarlySettleTrialVO earlySettleTrialVO = tradeService.earlySettleTrial(tradeId);
        String transacNo = DBUtil.getTransacNo();
        TradeEntity tradeEntity = dao.get(TradeEntity.class, tradeId);
        BankCardEntity bankCardEntity = tradeEntity.getBankCard();
        String cardNo = bankCardEntity.getCardNo();
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        PersonalVO personalVO = consumerUserService.getPersonalByUserId(userItemVO.getId());
        try {
            payApi.noSmsPay(transacNo, personalVO.getIdNo(), cardNo.substring(0, 6), cardNo.substring(cardNo.length()
                    - 4, cardNo.length()), earlySettleTrialVO.getTotalCutAmt(), tradeEntity.getOrg().getName(), TimeUtil
                    .timeToString(System.currentTimeMillis()), TimeUtil.timeToString(consumerUserEntity.getCreateTime()));
        } catch (BaseException e) {
        }
        return new SuccFessionVO();
    }

    /**
     * 查询合同附件
     */
    @RequestMapping(value = "queryContractAnnex/{token}/{tradeId}", method = RequestMethod.GET)
    @ApiOperation("查询合同附件")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "合同路径")})
    public NetVO queryContractAnnex(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "贷款订单id", name =
            "tradeId") long tradeId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        ContractEntity contractEntity = dao.get("select c from ContractEntity c where c.isDeleted=false and c.trade.id=?0", new Object[]{tradeId});
        if (contractEntity == null) {
            throw new BaseException(RtnResultEnum.E070021);
        }
        return new StringResponseVO(contractEntity.getContrPath());
    }
}
