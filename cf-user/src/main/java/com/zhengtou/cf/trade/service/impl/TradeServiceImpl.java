package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.api.cl.ApplyApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.*;
import com.zhengtou.cf.common.api.outer.cl.apply.infoVO.*;
import com.zhengtou.cf.common.api.outer.cl.apply.request.ApplyRequest;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.LoanStatusEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.StoreEntity;
import com.zhengtou.cf.operator.service.OrgService;
import com.zhengtou.cf.product.pojo.entity.FeeEntity;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.product.pojo.entity.enums.FeeTypeEnum;
import com.zhengtou.cf.product.service.ProductService;
import com.zhengtou.cf.trade.controller.receiveVO.QueryTradeReciveVO;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.*;
import com.zhengtou.cf.trade.pojo.vo.app.AppBillVO;
import com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO;
import com.zhengtou.cf.trade.pojo.vo.app.enums.AppTradetatusEnum;
import com.zhengtou.cf.trade.service.OrderService;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.person.PersonalEntity;
import com.zhengtou.cf.user.pojo.vo.CardVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.pojo.vo.person.ContactVO;
import com.zhengtou.cf.user.pojo.vo.person.IncomeVO;
import com.zhengtou.cf.user.service.BankCardService;
import com.zhengtou.cf.user.service.ConsumerUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    BaseDao dao;
    @Autowired
    BankCardService bankCardService;
    @Autowired
    ApplyApi applyApi;
    @Autowired
    OrderService orderService;
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    OrgService orgService;
    @Autowired
    ProductService productService;

    @Override
    public ApplyRequest getApplyRequest(ConsumerUserVO userItemVO, String appNo, SubProductEntity subProductEntity, Long applyAmt, OrderVO orderVO)
            throws BaseException {
        List<CardVO> cardVOS = bankCardService.getBankCardByUserId(userItemVO.getId());
        if (cardVOS.isEmpty()) {
            throw new BaseException(RtnResultEnum.E000007);
        }
        ApplyRequest applyRequest = new ApplyRequest();
        applyRequest.setAppNo(appNo);
        //个人基础信息
        PersonalInfo personalInfo = new PersonalInfo();
        PersonalEntity personalInfoEntity = dao.get("select p from PersonalEntity p,ConsumerUserEntity c where p.isDeleted=false and c.id=?0 and c" +
                ".personal=p ", new Object[]{userItemVO.getId()});
        if (personalInfoEntity == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        BeanUtils.copyProperties(personalInfoEntity, personalInfo);
        personalInfo.setGender(personalInfoEntity.getGender() == null ? null : GenderEnum.valueOf(personalInfoEntity.getGender().getCode()));
        personalInfo.setIdType(personalInfoEntity.getIdType() == null ? null : CertificateTypeEnum.valueOf(personalInfoEntity.getIdType().getCode()));
        applyRequest.setPersonalInfo(personalInfo);
        //卡信息
        List<CardInfo> cardInfos = new ArrayList<>();
        CardVO cardVO = cardVOS.get(0);
        CardInfo cardInfo = new CardInfo();
        cardInfo.setBankCardNo(cardVO.getBankCardNo());
        cardInfo.setBankCode(BankCodeEnum.valueOf(cardVO.getBankNameSub()).getCode());
        cardInfo.setBankReservePhoneNumber(cardVO.getBankReservePhoneNumber());
        cardInfo.setBankNameSub(cardVO.getBankNameSub());
        cardInfos.add(cardInfo);
        applyRequest.setCardInfo(cardInfos);
        //联系人信息
        List<ContactInfo> contactInfos = new ArrayList<>();
        List<ContactVO> contactVOS = consumerUserService.getContactByPersonalId(userItemVO.getPersonId());
        if (!contactVOS.isEmpty()) {
            for (ContactVO contactVO : contactVOS) {
                ContactInfo contactInfo = new ContactInfo();
                BeanUtils.copyProperties(contactVO, contactInfo);
                contactInfo.setContactRelation(PersonRelationEnum.valueOf(contactVO.getContactRelation().getCode()));
                contactInfos.add(contactInfo);
            }
            applyRequest.setContactInfo(contactInfos);
        }
        //收入信息
        IncomeInfo incomeInfo = new IncomeInfo();
        IncomeVO incomeVO = consumerUserService.getIncomeByPersonalId(userItemVO.getPersonId());
        if (incomeVO != null) {
            BeanUtils.copyProperties(incomeVO, incomeInfo);
            applyRequest.setIncomeInfo(incomeInfo);
        }
        //借款信息
        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setProductCd(subProductEntity.getProductCd());
        loanInfo.setAppLmt(Double.parseDouble(MoneyUtil.moneyToString(applyAmt)));
        loanInfo.setLoanTerm(subProductEntity.getCycleCnt());
        applyRequest.setLoanInfo(loanInfo);
        //申请单基础信息
        String tradeFlowNo = DBUtil.getTradeNo();
        applyRequest.setAppSerialNo(tradeFlowNo);
        applyRequest.setUniqueId(userItemVO.getUserNo());
        applyRequest.setCooperatorId("1");
        applyRequest.setSocialIdentity(SocialIdentityEnum.getEnum(userItemVO.getSocialTypeEnum().name()));
        String contractNo = DBUtil.getContractNo();
        applyRequest.setContractNo(contractNo);
        //商户订单信息
        if (orderVO != null) {
            OrderInfo orderInfo = new OrderInfo();
            BeanUtils.copyProperties(orderVO, orderInfo);
            applyRequest.setOrderInfo(orderInfo);
        }
        return applyRequest;
    }

    @Override
    public void saveTrade(ConsumerUserVO userItemVO, String tradeFlowNo, String appNo, SubProductEntity subProductEntity, String orgNo, String
            storeNo, Long applyAmt,
                          String outTradeFlowNo) {
        List<CardVO> cardVOS = bankCardService.getBankCardByUserId(userItemVO.getId());
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setTradeFlowNo(tradeFlowNo);
        OrganizationEntity organizationEntity = dao.get("from OrganizationEntity o where o.orgNo=?0", new Object[]{orgNo});
        tradeEntity.setOrg(organizationEntity);
        if (StringUtils.isNotBlank(storeNo)) {
            StoreEntity storeEntity = dao.get("from StoreEntity s where s.storeNo=?0", new Object[]{storeNo});
            tradeEntity.setStore(storeEntity);
        }
        tradeEntity.setOutTradeFlowNo(outTradeFlowNo);
        tradeEntity.setAppNO(StringUtils.isNotBlank(appNo) ? appNo : "");
        tradeEntity.setContractNo(DBUtil.getContractNo());
        tradeEntity.setConsumerUser(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        tradeEntity.setFlowStage(TradeStageEnum.审批);
        tradeEntity.setTradeStatus(TradeStatusEnum.正在处理);
        tradeEntity.setRequestAmount(applyAmt);
        tradeEntity.setBankCard(dao.get(BankCardEntity.class, cardVOS.get(0).getCardId()));
        tradeEntity.setSubProduct(subProductEntity);
        dao.save(tradeEntity);
    }

    @Override
    public ApplyRequest getAppApplyRequest(ConsumerUserVO userItemVO, String appNo, SubProductEntity subProductEntity, Long applyAmt, OrderVO
            orderVO, BankCardEntity bankCardEntity) throws BaseException {
        List<CardVO> cardVOS = bankCardService.getBankCardByUserId(userItemVO.getId());
        if (cardVOS.isEmpty()) {
            throw new BaseException(RtnResultEnum.E000007);
        }
        ApplyRequest applyRequest = new ApplyRequest();
        applyRequest.setAppNo(appNo);
        //个人基础信息
        PersonalInfo personalInfo = new PersonalInfo();
        PersonalEntity personalInfoEntity = dao.get("select p from PersonalEntity p,ConsumerUserEntity c where p.isDeleted=false and c.id=?0 and c" +
                ".personal=p", new Object[]{userItemVO.getId()});
        if (personalInfoEntity == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        BeanUtils.copyProperties(personalInfoEntity, personalInfo);
        personalInfo.setGender(personalInfoEntity.getGender() == null ? null : GenderEnum.valueOf(personalInfoEntity.getGender().getCode()));
        personalInfo.setIdType(personalInfoEntity.getIdType() == null ? null : CertificateTypeEnum.valueOf(personalInfoEntity.getIdType().getCode()));
        applyRequest.setPersonalInfo(personalInfo);
        //卡信息
        List<CardInfo> cardInfos = new ArrayList<>();
        CardInfo cardInfo = new CardInfo();
        cardInfo.setBankCardNo(bankCardEntity.getCardNo());
        cardInfo.setBankCode(bankCardEntity.getBankName().getCode());
        cardInfo.setBankReservePhoneNumber(personalInfoEntity.getCellphone());
        cardInfo.setBankNameSub(bankCardEntity.getBankName().name());
        cardInfos.add(cardInfo);
        applyRequest.setCardInfo(cardInfos);
        //联系人信息
        List<ContactInfo> contactInfos = new ArrayList<>();
        List<ContactVO> contactVOS = consumerUserService.getContactByPersonalId(userItemVO.getPersonId());
        if (!contactVOS.isEmpty()) {
            for (ContactVO contactVO : contactVOS) {
                ContactInfo contactInfo = new ContactInfo();
                BeanUtils.copyProperties(contactVO, contactInfo);
                contactInfo.setContactRelation(PersonRelationEnum.valueOf(contactVO.getContactRelation().getCode()));
                contactInfos.add(contactInfo);
            }
            applyRequest.setContactInfo(contactInfos);
        }
        //收入信息
        IncomeInfo incomeInfo = new IncomeInfo();
        IncomeVO incomeVO = consumerUserService.getIncomeByPersonalId(userItemVO.getPersonId());
        if (incomeVO != null) {
            BeanUtils.copyProperties(incomeVO, incomeInfo);
            applyRequest.setIncomeInfo(incomeInfo);
        }
        //借款信息
        LoanInfo loanInfo = new LoanInfo();
        loanInfo.setProductCd(subProductEntity.getProductCd());
        loanInfo.setAppLmt(Double.parseDouble(MoneyUtil.moneyToString(applyAmt)));
        loanInfo.setLoanTerm(subProductEntity.getCycleCnt());
        applyRequest.setLoanInfo(loanInfo);
        //申请单基础信息
        String tradeFlowNo = DBUtil.getTradeNo();
        applyRequest.setAppSerialNo(tradeFlowNo);
        applyRequest.setUniqueId(userItemVO.getUserNo());
        applyRequest.setCooperatorId("1");
        applyRequest.setSocialIdentity(SocialIdentityEnum.getEnum(userItemVO.getSocialTypeEnum().name()));
        String contractNo = DBUtil.getContractNo();
        applyRequest.setContractNo(contractNo);
        //商户订单信息
        if (orderVO != null) {
            OrderInfo orderInfo = new OrderInfo();
            BeanUtils.copyProperties(orderVO, orderInfo);
            applyRequest.setOrderInfo(orderInfo);
        }
        return applyRequest;
    }

    @Override
    public void saveAppTrade(ConsumerUserVO userItemVO, String tradeFlowNo, String appNo, SubProductEntity subProductEntity, String orgNo, Long
            applyAmt, BankCardEntity
                                     bankCardEntity) {
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setTradeFlowNo(tradeFlowNo);
        OrganizationEntity organizationEntity = dao.get("from OrganizationEntity o where o.orgNo=?0", new Object[]{orgNo});
        tradeEntity.setOrg(organizationEntity);
        tradeEntity.setAppNO(StringUtils.isNotBlank(appNo) ? appNo : "");
        tradeEntity.setContractNo(DBUtil.getContractNo());
        tradeEntity.setConsumerUser(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        tradeEntity.setFlowStage(TradeStageEnum.审批);
        tradeEntity.setTradeStatus(TradeStatusEnum.正在处理);
        tradeEntity.setRequestAmount(applyAmt);
        tradeEntity.setBankCard(bankCardEntity);
        tradeEntity.setSubProduct(subProductEntity);
        dao.save(tradeEntity);
    }

    @Override
    public TradeVO getTradeByOutTradeFlowNo(String outTradeFlowNo) {
        return dao.get("select new com.zhengtou.cf.trade.pojo.vo.TradeVO(t.id,t.consumerUser.personal.name,t.consumerUser.personal.idNo,t" +
                ".subProduct.cycleCnt,t.createTime,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.repayMethod,t" +
                ".tradeFlowNo,t.outTradeFlowNo,t.appNO,t.contractNo,t.flowStage,t.tradeStatus,t.requestAmount,t.approvalAmount,t.org.id,t.org.name)" +
                " from TradeEntity t where t.isDeleted=false and t.outTradeFlowNo=?0", new Object[]{outTradeFlowNo});
    }

    @Override
    public TradeEntity getTradeByTradeFlowNo(String tradeFlowNo) {
        return dao.get("select t from TradeEntity t where t.isDeleted=false and t.tradeFlowNo=?0 ", new Object[]{tradeFlowNo});
    }

    @Override
    public List<TradeVO> queryTradeList(QueryTradeReciveVO queryTradeReciveVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.trade.pojo.vo.TradeVO(t.id,p.name,p.idNo,t" +
                ".subProduct.cycleCnt,t.createTime,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.repayMethod,t" +
                ".tradeFlowNo,t.outTradeFlowNo,t.appNO,t.contractNo,t.flowStage,t.tradeStatus,t.requestAmount,t.approvalAmount,t.org.id,t.org.name)" +
                " from TradeEntity t,PersonalEntity p  where t.isDeleted=false and t.consumerUser.personal=p ");
        HashMap<String, Object> where = new HashMap<>();
        if (queryTradeReciveVO.getFlowStage() != null) {
            hql.append(" and t.flowStage=:flowStage");
            where.put("flowStage", queryTradeReciveVO.getFlowStage());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getIdnum())) {
            hql.append(" and p.idNo=:idnum");
            where.put("idnum", queryTradeReciveVO.getIdnum());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getRealname())) {
            hql.append(" and p.name like :realname");
            where.put("realname", DBUtil.generateLikeSql(queryTradeReciveVO.getRealname()));
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getOutTradeFlowNo())) {
            hql.append(" and t.outTradeFlowNo=:outTradeFlowNo");
            where.put("outTradeFlowNo", queryTradeReciveVO.getOutTradeFlowNo());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getTradeFlowNo())) {
            hql.append(" and t.tradeFlowNo=:tradeFlowNo");
            where.put("tradeFlowNo", queryTradeReciveVO.getTradeFlowNo());
        }
        if (queryTradeReciveVO.getTradeStatus() != null) {
            hql.append(" and t.tradeStatus=:tradeStatus");
            where.put("tradeStatus", queryTradeReciveVO.getTradeStatus());
        }
        if (queryTradeReciveVO.getZtProductEnum() != null) {
            hql.append(" and t.subProduct.ztProductEnum=:ztProductEnum");
            where.put("ztProductEnum", queryTradeReciveVO.getZtProductEnum());
        }
        hql.append(" order by t.createTime desc");
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public Long countTradeList(QueryTradeReciveVO queryTradeReciveVO) {
        StringBuffer hql = new StringBuffer("select count(*) from TradeEntity t,PersonalEntity p  where t.isDeleted=false and t.consumerUser" +
                ".personal=p ");
        HashMap<String, Object> where = new HashMap<>();
        if (queryTradeReciveVO.getFlowStage() != null) {
            hql.append(" and t.flowStage=:flowStage");
            where.put("flowStage", queryTradeReciveVO.getFlowStage());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getIdnum())) {
            hql.append(" and p.idNo=:idnum");
            where.put("idnum", queryTradeReciveVO.getIdnum());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getRealname())) {
            hql.append(" and p.name like :realname");
            where.put("realname", DBUtil.generateLikeSql(queryTradeReciveVO.getRealname()));
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getOutTradeFlowNo())) {
            hql.append(" and t.outTradeFlowNo=:outTradeFlowNo");
            where.put("outTradeFlowNo", queryTradeReciveVO.getOutTradeFlowNo());
        }
        if (StringUtils.isNotBlank(queryTradeReciveVO.getTradeFlowNo())) {
            hql.append(" and t.tradeFlowNo=:tradeFlowNo");
            where.put("tradeFlowNo", queryTradeReciveVO.getTradeFlowNo());
        }
        if (queryTradeReciveVO.getTradeStatus() != null) {
            hql.append(" and t.tradeStatus=:tradeStatus");
            where.put("tradeStatus", queryTradeReciveVO.getTradeStatus());
        }
        if (queryTradeReciveVO.getZtProductEnum() != null) {
            hql.append(" and t.subProduct.ztProductEnum=:ztProductEnum");
            where.put("ztProductEnum", queryTradeReciveVO.getZtProductEnum());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public List<AppTradeVO> queryAPPAllTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size) {
        List<AppTradeVO> appTradeVOS = dao.find("select new com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO(t.id,t.createTime,t" +
                        ".subProduct.cycleCnt,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.productSubCd,t.subProduct.repayMethod," +
                        "t" +
                        ".tradeFlowNo,t.contractNo,t.requestAmount,t.approvalAmount,t.flowStage,t.tradeStatus,t.bankCard.bankName,t.bankCard" +
                        ".cardNo) from TradeEntity t where t.isDeleted=false and t.consumerUser.id=?0 order by t.createTime desc", new
                        Object[]{consumerUserId},
                page, size);
        for (AppTradeVO appTradeVO : appTradeVOS) {
            if (appTradeVO.getAppTradetatusEnum() == null) {
                AppBillVO appBillVO = getBillVOByTradeId(appTradeVO.getTradeId());
                if (appBillVO != null) {
                    if (appBillVO.getBillStatusEnum().equals(BillStatusEnum.正常)) {
                        appTradeVO.setAppTradetatusEnum(AppTradetatusEnum.还款中);
                        appTradeVO.setSchdAmt(appBillVO.getSchdAmt());
                        appTradeVO.setPmtDueDate(appBillVO.getPmtDueDate());
                    }
                    if (appBillVO.getBillStatusEnum().equals(BillStatusEnum.已逾期)) {
                        appTradeVO.setAppTradetatusEnum(AppTradetatusEnum.已逾期);
                        appTradeVO.setOverDue(true);
                        appTradeVO.setSchdAmt(appBillVO.getSchdAmt());
                        appTradeVO.setPmtDueDate(appBillVO.getPmtDueDate());
                    }
                    if (appBillVO.getBillStatusEnum().equals(BillStatusEnum.已结清)) {
                        appTradeVO.setAppTradetatusEnum(AppTradetatusEnum.已结清);
                    }
                }
            }
        }
        return appTradeVOS;
    }

    @Override
    public Long countAPPAllTradeListByConsumerUserId(long consumerUserId) {
        return dao.count("select count(*)" +
                " from TradeEntity t where t.isDeleted=false and t.consumerUser.id=?0 ", new Object[]{consumerUserId});
    }

    @Override
    public List<AppTradeVO> queryAPPApplyTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size) {
        List<AppTradeVO> appTradeVOS = dao.find("select new com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO(t.id,t.createTime,t" +
                ".subProduct.cycleCnt,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.productSubCd,t.subProduct.repayMethod,t" +
                ".tradeFlowNo,t.contractNo,t.requestAmount,t.approvalAmount,t.flowStage,t.tradeStatus,t.bankCard.bankName,t.bankCard.cardNo)" +
                " from TradeEntity t where t.isDeleted=false and t.consumerUser.id=?0 and t.flowStage=?1 and (t.tradeStatus=?2 or t.tradeStatus=?3)" +
                " order by t.createTime desc", new Object[]{consumerUserId, TradeStageEnum.审批, TradeStatusEnum.通过, TradeStatusEnum.正在处理}, page, size);
        return appTradeVOS;
    }

    @Override
    public Long countAPPApplyTradeListByConsumerUserId(long consumerUserId) {
        return dao.count("select count(*) from TradeEntity t where t.isDeleted=false and t.consumerUser.id=?0 and t.flowStage=?1 and (t" +
                ".tradeStatus=?2 or t.tradeStatus=?3)", new Object[]{consumerUserId, TradeStageEnum.审批, TradeStatusEnum
                .通过, TradeStatusEnum.正在处理});
    }

    @Override
    public List<AppTradeVO> queryAPPRepayTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size) {
        List<AppTradeVO> appTradeVOS = dao.find("select new com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO(t.id,t.createTime,t" +
                        ".subProduct.cycleCnt,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.productSubCd,t.subProduct.repayMethod," +
                        "t" +
                        ".tradeFlowNo,t.contractNo,t.requestAmount,t.approvalAmount,tm.schdAmt,tm.pmtDueDate,b.billStatus,b.billNo,b.xfrOutPrin,b" +
                        ".unpaidAmt," +
                        "b.termNo) from TradeEntity t,ContractEntity c,BillEntity b,TermEntity tm where t.isDeleted=false and t.consumerUser.id=?0 " +
                        "and c" +
                        ".trade=t and c.bill=b and (b.billStatus=?1 or b.billStatus=?2) and tm.bill=b and b.termNo=tm.termNo order by t.createTime " +
                        "desc",
                new Object[]{consumerUserId, BillStatusEnum.正常, BillStatusEnum.已逾期}, page, size);
        return appTradeVOS;
    }

    @Override
    public Long countAPPRepayTradeListByConsumerUserId(long consumerUserId) {
        return dao.count("select count(*) from TradeEntity t,ContractEntity c,BillEntity b,TermEntity tm where t.isDeleted=false and t.consumerUser" +
                        ".id=?0 and c.trade=t and c.bill=b and (b.billStatus=?1 or b.billStatus=?2) and tm.bill=b and b.termNo=tm.termNo",
                new Object[]{consumerUserId, BillStatusEnum.正常, BillStatusEnum.已逾期});
    }

    @Override
    public List<AppTradeVO> queryAPPCompletedTradeListByConsumerUserId(long consumerUserId, Integer page, Integer size) {
        List<AppTradeVO> appTradeVOS = dao.find("select new com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO(t.id,t.createTime,t" +
                        ".subProduct.cycleCnt,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.productSubCd,t.subProduct.repayMethod," +
                        "t" +
                        ".tradeFlowNo,t.contractNo,t.requestAmount,t.approvalAmount,tm.schdAmt,tm.pmtDueDate,b.billStatus,b.billNo,b.xfrOutPrin,b" +
                        ".unpaidAmt," +
                        "b.termNo)" +
                        " from TradeEntity t,ContractEntity c,BillEntity b,TermEntity tm where t.isDeleted=false and t.consumerUser.id=?0 and c" +
                        ".trade=t and c.bill=b and (b.billStatus=?1 or b.billStatus=?2) and tm.bill=b and b.termNo=tm.termNo order by t.createTime " +
                        "desc",
                new Object[]{consumerUserId, BillStatusEnum.已结清, BillStatusEnum.已终止}, page, size);
        return appTradeVOS;
    }

    @Override
    public Long countAPPCompletedTradeListByConsumerUserId(long consumerUserId) {
        return dao.count("select count(*) from TradeEntity t,ContractEntity c,BillEntity b,TermEntity tm where t.isDeleted=false and t.consumerUser" +
                        ".id=?0 and c.trade=t and c.bill=b and (b.billStatus=?1 or b.billStatus=?2) and tm.bill=b and b.termNo=tm.termNo",
                new Object[]{consumerUserId, BillStatusEnum.已结清, BillStatusEnum.已终止});
    }

    @Override
    public AppBillVO getBillVOByTradeId(long tradeId) {
        return dao.get("select new com.zhengtou.cf.trade.pojo.vo.app.AppBillVO(b.id,t.termNo,t.pmtDueDate,t.schdAmt,t.paidOutDate,b.billStatus)" +
                " from ContractEntity c, BillEntity b ,TermEntity t where c.trade.id=?0 and c.bill=b and t.bill=b and b.loanTerm=t.termNo ", new
                Object[]{tradeId});
    }

    @Override
    public TradeVO getTradeVOByPhoneAndOutTradeFlowNo(String phone, String outTradeFlowNo) {
        return dao.get("select new com.zhengtou.cf.trade.pojo.vo.TradeVO(t.id,p.name,p.idNo,t.subProduct.cycleCnt,t" +
                ".createTime,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.repayMethod,t.tradeFlowNo,t.outTradeFlowNo,t.appNO,t" +
                ".contractNo,t.flowStage,t.tradeStatus,t.requestAmount,t.approvalAmount,t.org.id,t.org.name) from TradeEntity t,PersonalEntity p " +
                "where t.isDeleted=false and t.consumerUser.phone=?0 and t.outTradeFlowNo=?2 and t.consumerUser.personal=p", new
                Object[]{phone, outTradeFlowNo});
    }

    @Override
    public TradeVO getTradeVOByTradeId(long tradeId) {
        return dao.get("select new com.zhengtou.cf.trade.pojo.vo.TradeVO(t.id,t.consumerUser.personal.name,t.consumerUser.personal.idNo,t" +
                ".subProduct.cycleCnt,t" +
                ".createTime,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.repayMethod,t.tradeFlowNo,t.outTradeFlowNo,t.appNO,t" +
                ".contractNo,t.flowStage,t.tradeStatus,t.requestAmount,t.approvalAmount,t.org.id,t.org.name) from TradeEntity t where t" +
                ".isDeleted=false and t.id=?0", new Object[]{tradeId});
    }

    @Override
    public List<TradeVO> findTradeVOByUserIdAndAmtCreateTimeAndNotNullAppNo(long userId, long amtCreateTime) {
        return dao.find("select new com.zhengtou.cf.trade.pojo.vo.TradeVO(t.id,p.name,p.idNo,t.subProduct.cycleCnt,t" +
                ".createTime,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.repayMethod,t.tradeFlowNo,t.outTradeFlowNo,t.appNO,t" +
                ".contractNo,t.flowStage,t.tradeStatus,t.requestAmount,t.approvalAmount,t.org.id,t.org.name) from TradeEntity t,PersonalEntity p " +
                "where t.isDeleted=false and t.consumerUser.id=?0 and t.consumerUser.personal=p and t.createTime>=?1 and t.appNO is not null order " +
                "by t.createTime desc", new Object[]{userId, amtCreateTime});
    }

    @Override
    public List<AccountReceiptSummaryVO> queryAccountReceiptSummary(long dateStart, long dateEnd, String orgNo, String orgName) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.trade.pojo.vo.AccountReceiptSummaryVO(count(t.id),sum(t.schdAmt),c.trade" +
                ".org.id,c.trade.org.name,c.trade.org.orgNo) from TermEntity t,BillEntity b,ContractEntity c where c.bill=b and t.bill=b ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(orgNo)) {
            hql.append(" and c.trade.org.orgNo=:orgNo");
            where.put("orgNo", orgNo);
        }
        if (StringUtils.isNotBlank(orgName)) {
            hql.append(" and c.trade.org.name like :name");
            where.put("name", DBUtil.generateLikeSql(orgName));
        }
        hql.append(" and t.createTime>=:dateStart");
        where.put("dateStart", dateStart);
        hql.append(" and t.createTime<:dateEnd");
        where.put("dateEnd", dateEnd);
        hql.append(" and t.termStatus=:termStatus");
        where.put("termStatus", TermStatusEnum.已还款);
        hql.append(" group by c.trade.org.id,c.trade.org.name,c.trade.org.orgNo");
        return dao.find(hql.toString(), where);
    }

    @Override
    public List<AccountPaySummaryVO> queryAccountPaySummary(long dateStart, long dateEnd, String orgNo, String orgName) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.trade.pojo.vo.AccountPaySummaryVO(count(c.id),sum(c.contrPrin),c.trade.org" +
                ".id,c.trade.org.name,c.trade.org.orgNo) from ContractEntity c where c.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(orgNo)) {
            hql.append(" and c.trade.org.orgNo=:orgNo");
            where.put("orgNo", orgNo);
        }
        if (StringUtils.isNotBlank(orgName)) {
            hql.append(" and c.trade.org.name like :name");
            where.put("name", DBUtil.generateLikeSql(orgName));
        }
        hql.append(" and c.createTime>=:dateStart");
        where.put("dateStart", dateStart);
        hql.append(" and c.createTime<:dateEnd");
        where.put("dateEnd", dateEnd);
        hql.append(" and c.loanStatus=:loanStatus");
        where.put("loanStatus", LoanStatusEnum.已完成);
        hql.append(" group by c.trade.org.id,c.trade.org.name,c.trade.org.orgNo");
        return dao.find(hql.toString(), where);
    }

    @Override
    public ContractVO previewContractByOutTradeFlowNo(long userId, String outTradeFlowNo) {
        return dao.get("select new com.zhengtou.cf.trade.pojo.vo.ContractVO(t.tradeFlowNo,t.outTradeFlowNo,t.contractNo,t.approvalAmount,t" +
                ".createTime,current_date(),t.purpose,t.org.orgNo) from TradeEntity t where t.isDeleted=false and t.flowStage=?0 and t" +
                ".tradeStatus=?1" +
                " and t.outTradeFlowNo=?2 and t.consumerUser.id=?3", new Object[]{TradeStageEnum.审批, TradeStatusEnum.通过, outTradeFlowNo, userId});
    }

    @Override
    public void modifyTradeStatusByOutTradeFlowNo(String outTradeFlowNo, TradeStatusEnum tradeStatus, Long approvalAmount) throws BaseException {
        TradeEntity tradeEntity = dao.get("from TradeEntity t where t.isDeleted=false and t.outTradeFlowNo=?0", new Object[]{outTradeFlowNo});
        if (tradeEntity == null) {
            throw new BaseException(RtnResultEnum.E070007);
        }
        tradeEntity.setTradeStatus(tradeStatus);
        if (tradeStatus.equals(TradeStatusEnum.通过)) {
            tradeEntity.setApprovalAmount(approvalAmount);
        }
        dao.modify(tradeEntity);
    }

    @Override
    public void modifyTradeStatusByTradeFlowNo(String tradeFlowNo, TradeStatusEnum tradeStatus, Long approvalAmount) throws BaseException {
        TradeEntity tradeEntity = dao.get("from TradeEntity t where t.isDeleted=false and t.tradeFlowNo=?0", new Object[]{tradeFlowNo});
        if (tradeEntity == null) {
            throw new BaseException(RtnResultEnum.E070007);
        }
        tradeEntity.setTradeStatus(tradeStatus);
        if (tradeStatus.equals(TradeStatusEnum.通过)) {
            tradeEntity.setApprovalAmount(approvalAmount);
        }
        dao.modify(tradeEntity);
    }

    @Override
    public void modifyTradeFlowStageAndStatusByTradeId(long tradeId, TradeStageEnum tradeStageEnum, TradeStatusEnum tradeStatusEnum) throws
            BaseException {
        TradeEntity tradeEntity = dao.get(TradeEntity.class, tradeId);
        if (tradeEntity == null) {
            throw new BaseException(RtnResultEnum.E070007);
        }
        tradeEntity.setFlowStage(tradeStageEnum);
        tradeEntity.setTradeStatus(tradeStatusEnum);
        dao.modify(tradeEntity);
    }

    @Override
    public AppTradeVO getAppTradeVOByTradeId(long tradeId) {
        AppTradeVO appTradeVO = dao.get("select new com.zhengtou.cf.trade.pojo.vo.app.AppTradeVO(t.id,t.createTime,t" +
                ".subProduct.cycleCnt,t.subProduct.ztProductEnum,t.subProduct.productCd,t.subProduct.productSubCd,t.subProduct.repayMethod," +
                "t.tradeFlowNo,t.contractNo,t.requestAmount,t.approvalAmount,tm.schdAmt,tm.pmtDueDate,b.billStatus,b.billNo,b.xfrOutPrin,b" +
                ".unpaidAmt," +
                "b.termNo) from TradeEntity t,ContractEntity c,BillEntity b,TermEntity tm where t.isDeleted=false and t.id=?0 and c.trade=t" +
                " and c.bill=b and tm.bill=b and b.termNo=tm.termNo", new Object[]{tradeId});
        return appTradeVO;
    }

    @Override
    public EarlySettleTrialVO earlySettleTrial(long tradeId) throws BaseException {
        AppTradeVO appTradeVO = getAppTradeVOByTradeId(tradeId);
        if (appTradeVO.getAppTradetatusEnum() != AppTradetatusEnum.还款中) {
            throw new BaseException(RtnResultEnum.E070019);
        }
        EarlySettleTrialVO earlySettleTrialVO = new EarlySettleTrialVO();
        earlySettleTrialVO.setBillNo(appTradeVO.getBillNo());
        earlySettleTrialVO.setContrNo(appTradeVO.getContractNo());
        earlySettleTrialVO.setTradeFlowNo(appTradeVO.getTradeFlowNo());
        earlySettleTrialVO.setAllInterest(appTradeVO.getAllInterest());
        earlySettleTrialVO.setPrincipal(appTradeVO.getXfrOutPrin());
        FeeEntity feeEntity = productService.getFeeByProduceCdAndProductSubCdAndFeeType(appTradeVO.getProductCd(), appTradeVO
                .getProductSubCd(), FeeTypeEnum.提前结清手续费);
        if (feeEntity == null) {
            throw new BaseException(RtnResultEnum.E070020);
        }
        long chargeFeeVal=0l;
        if(feeEntity.getChargeFeeVal()!=null){
            earlySettleTrialVO.setTxnFeeAmt(MoneyUtil.moneyToString(feeEntity.getChargeFeeVal()));
            chargeFeeVal=feeEntity.getChargeFeeVal();
        }
        //本金
        long xfrOutPrin = MoneyUtil.moneyToLong(appTradeVO.getXfrOutPrin());
        //当前据当期还款日时间
        long nextDay = (TimeUtil.timeToLong(appTradeVO.getPmtDueDate()) - TimeUtil.timeToLong(TimeUtil.timeToStringYMD(System.currentTimeMillis()))
        ) / 1000 / 60 / 60 / 24;
        long day=TimeUtil.getRepaymentDay(appTradeVO.getCycleCnt()-Integer.parseInt(appTradeVO.getTermNo()))+nextDay;
        long settleInterest=MoneyUtil.moneyToLong(xfrOutPrin*feeEntity.getChargeFeeRate()*day);
        earlySettleTrialVO.setSettleInterest(MoneyUtil.moneyToString(settleInterest));
        earlySettleTrialVO.setTotalCutAmt(MoneyUtil.moneyToString(xfrOutPrin+settleInterest+chargeFeeVal));
        return earlySettleTrialVO;
    }
}
