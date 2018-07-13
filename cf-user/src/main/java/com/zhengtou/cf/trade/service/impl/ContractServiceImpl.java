package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.api.third.SignatureApi;
import com.zhengtou.cf.api.third.vo.SignInfo;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.LoanStatusEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.product.service.ProductService;
import com.zhengtou.cf.trade.pojo.entity.contract.ContractEntity;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.vo.PaymentVO;
import com.zhengtou.cf.trade.pojo.vo.TradeVO;
import com.zhengtou.cf.trade.service.ContractService;
import com.zhengtou.cf.user.pojo.entity.AnnexEntity;
import com.zhengtou.cf.user.pojo.entity.enums.AnnexStatusEnum;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.pojo.vo.person.PersonalVO;
import com.zhengtou.cf.user.service.ConsumerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    BaseDao dao;
    @Autowired
    SignatureApi signatureApi;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Value("${person.contractFile.path}")
    private String path;
    @Value("${personShow.contractFile.path}")
    private String showPath;
    @Value("${upload.contractFile.path}")
    private String tempPath;
    @Value("${show.contractFile.path}")
    private String showTempPath;
    @Autowired
    ProductService productService;
    @Autowired
    ConsumerUserService consumerUserService;

    @Override
    public void saveContract(TradeVO tradeVO, String contractPdfPath) {
        TradeEntity tradeEntity = dao.get(TradeEntity.class, tradeVO.getTradeId());
        //保存合同
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setApplyDate(TimeUtil.timeToLong(tradeVO.getCreateTime()));
        contractEntity.setApplyNo(StringUtils.isNotBlank(tradeVO.getAppNO()) ? tradeVO.getAppNO() : "");
        contractEntity.setContractStatus(ContractEntity.EnumContractStatus.有效);
        contractEntity.setContrLimit(MoneyUtil.moneyToLong(tradeVO.getApprovalAmount()));
        contractEntity.setContrNo(tradeVO.getContractNo());
        contractEntity.setContrPath(contractPdfPath);
        contractEntity.setContrPrin(MoneyUtil.moneyToLong(tradeVO.getApprovalAmount()));
        contractEntity.setLoanStatus(LoanStatusEnum.未审核);
        contractEntity.setSignDate(System.currentTimeMillis());
        contractEntity.setCustSource(tradeEntity.getOrg().getOrgNo());
        contractEntity.setTrade(tradeEntity);
        dao.save(contractEntity);
    }

    @Override
    public String saveContractPdf(ConsumerUserVO consumerUserVO, TradeEntity tradeEntity) throws BaseException {
        SubProductEntity subProductEntity = tradeEntity.getSubProduct();
        AnnexEntity annexEntity = dao.get("from AnnexEntity a where a.annexStatus=?0 and a.ztProductEnum=?1 ", new Object[]{AnnexStatusEnum.启用,
                subProductEntity.getZtProductEnum()});
        if(annexEntity==null){
            throw new BaseException(RtnResultEnum.E070015);
        }
        PersonalVO personalVO = consumerUserService.getPersonalByUserId(consumerUserVO.getId());
        String macPath = annexEntity.getUrl().replace(showTempPath, tempPath);
        String pdfPath = path + consumerUserVO.getUserNo() + "/" + tradeEntity.getContractNo() + ".pdf";
        String pdfPath_temp = path + consumerUserVO.getUserNo() + "/" + tradeEntity.getContractNo() + "temp.pdf";
        String pdfPath_temp2 = path + consumerUserVO.getUserNo() + "/" + tradeEntity.getContractNo() + "temp2.pdf";
        String pdfShowPath = showPath + consumerUserVO.getUserNo() + "/" + tradeEntity.getContractNo() + ".pdf";
        HashMap<String, String> map = new HashMap<>();
        OrganizationEntity organizationEntity = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        map.put("DOCSEQNO", tradeEntity.getContractNo());
        map.put("PAYNAME", organizationEntity.getName());
        map.put("PAYIDCARD", organizationEntity.getSocialCreditCode());
        map.put("BORROWNAME", personalVO.getName());
        map.put("BORROWIDCARD", personalVO.getIdNo());
        map.put("AMOUNTUPPER", MoneyUtil.arabNumToChineseRMB(MoneyUtil.moneyToString(tradeEntity.getApprovalAmount())));
        map.put("AMOUNTLOWER ", MoneyUtil.moneyToString(tradeEntity.getApprovalAmount()));
        map.put("DAYS", TimeUtil.getRepaymentDayNum(subProductEntity.getCycleCnt())+"");
        map.put("PROSDATE", TimeUtil.dateToString(System.currentTimeMillis()));
        map.put("PROEDATE", TimeUtil.dateToString(TimeUtil.getRepaymentDay(subProductEntity.getCycleCnt())));
        map.put("PURPOSE", "消费贷款");
        map.put("PAYMENT", subProductEntity.getRepayMethod().name());
        map.put("ESTABLISH", TimeUtil.timeToString(tradeEntity.getCreateTime()));
        //合同文件存储
        signatureApi.macdownDocToPdf(macPath, pdfPath_temp, map);
        List<SignInfo> signInfos = new ArrayList<>();
        SignInfo signInfo = new SignInfo(270, 730, 1);
        SignInfo signInfo1 = new SignInfo(250, 100, 2);
        signInfos.add(signInfo);
        signInfos.add(signInfo1);
        signatureApi.ztSign(pdfPath_temp, pdfPath_temp2, signInfos);
        List<SignInfo> personSignInfos = new ArrayList<>();
        SignInfo signInfo2 = new SignInfo(130, 710, 1);
        SignInfo signInfo3 = new SignInfo(120, 780, 3);
        personSignInfos.add(signInfo2);
        personSignInfos.add(signInfo3);
        signatureApi.personSign(personalVO.getName(), personalVO.getIdNo(), pdfPath_temp2, pdfPath, personSignInfos);
        return pdfShowPath;
    }

    @Override
    public List<PaymentVO> getPaymentList(Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.trade.pojo.vo.PaymentVO(c.id,c.createTime,c.trade.org.id,c.trade.org.name,c.contrPrin,c" +
                ".purpose,c.loanStatus) from ContractEntity c where c.isDeleted=false and c.contractStatus=?0", new Object[]{ContractEntity
                .EnumContractStatus.有效}, page, size);
    }

    @Override
    public Long countPayment() {
        return dao.count("select count(*) " +
                "from ContractEntity c where c.isDeleted=false and c.contractStatus=?0", new Object[]{ContractEntity.EnumContractStatus.有效});
    }
}
