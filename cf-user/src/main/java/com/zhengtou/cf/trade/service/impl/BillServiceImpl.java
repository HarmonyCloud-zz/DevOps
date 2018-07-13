package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Bnp;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.Term;
import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;
import com.zhengtou.cf.trade.pojo.entity.bill.BnpEntity;
import com.zhengtou.cf.trade.pojo.entity.bill.TermEntity;
import com.zhengtou.cf.trade.pojo.entity.contract.ContractEntity;
import com.zhengtou.cf.trade.pojo.entity.enums.BillStatusEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.BnpTypeEnum;
import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.service.BillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BaseDao dao;

    @Override
    public List<BillEntity> getAllNormalBill() {
        return dao.find("select b from BillEntity b where b.isDeleted=false and b.billStatus=?0 ", new Object[]{BillStatusEnum.正常});
    }

    @Override
    public void saveBill(String contactNo, LoanItem loanItem) {
        //保存借据信息
        BillEntity billEntity = new BillEntity();
        BeanUtils.copyProperties(loanItem, billEntity);
        billEntity.setBillNo(DBUtil.getBillNo());
        billEntity.setBillStatus(BillStatusEnum.正常);
        billEntity.setCurrBal(MoneyUtil.moneyToLong(loanItem.getUnpaidAmt()));
        billEntity.setLoanPrin(MoneyUtil.moneyToLong(loanItem.getLendingAmt()));
        billEntity.setLoanTerm(loanItem.getLoanTerm());
        billEntity.setTermNo("1");
        billEntity.setUnpaidAmt(MoneyUtil.moneyToLong(loanItem.getUnpaidAmt()));
        billEntity.setWithdrawAmt(0l);
        billEntity.setXfrOutPrin(MoneyUtil.moneyToLong(loanItem.getLendingAmt()));
        billEntity.setRepayMethod(RepayMethodEnum.getEnum(loanItem.getRepayMethod().getCode()));
        long totalIntersetAmt=0l;
        long serviceFee=0l;
        dao.save(billEntity);
        ContractEntity contractEntity= dao.get("from ContractEntity c where c.isDeleted=false and c.contrNo=?0", new Object[]{contactNo});
        contractEntity.setBill(billEntity);
        //保存期供信息（扣款）
        for(Term term:loanItem.getTermList()){
            TermEntity termEntity=new TermEntity();
            if(term.getTermNo().equals("总计")){
                for(Bnp bnp:term.getBnpList()){
                    if(bnp.getBnpType().equals("INTEREST")){
                        totalIntersetAmt+=MoneyUtil.moneyToLong(bnp.getSchdAmt());
                    }
                    //平台服务费
                    if(bnp.getBnpType().equals("DSPT")){
                        serviceFee+=MoneyUtil.moneyToLong(bnp.getSchdAmt());
                    }
                    //分期服务费
                    if(bnp.getBnpType().equals("TERM_SERV_FEE")){
                        serviceFee+=MoneyUtil.moneyToLong(bnp.getSchdAmt());
                    }
                    //贷款服务费
                    if(bnp.getBnpType().equals("LOAN_SERV_FEE")){
                        serviceFee+=MoneyUtil.moneyToLong(bnp.getSchdAmt());
                    }
                }
            }else{
                termEntity.setAgeCd(term.getAgeCd());
                termEntity.setLoanRemainPrin(MoneyUtil.moneyToLong(term.getLoanRemainPrin()));
                termEntity.setOverDueAmt(MoneyUtil.moneyToLong(term.getOverDueAmt()));
                termEntity.setPaidAmt(MoneyUtil.moneyToLong(term.getPaidAmt()));
                termEntity.setSchdAmt(MoneyUtil.moneyToLong(term.getSchdAmt()));
                termEntity.setTermNo(term.getTermNo());
                termEntity.setWavAmt(MoneyUtil.moneyToLong(term.getWavAmt()));
                termEntity.setPmtDueDate(TimeUtil.getRepaymentDay(Integer.parseInt(term.getTermNo())));
                termEntity.setBill(billEntity);
                termEntity.setTermStatus(TermStatusEnum.待还款);
                dao.save(termEntity);
                for(Bnp bnp:term.getBnpList()){
                    BnpEntity bnpEntity=new BnpEntity();
                    if(bnp.getSchdAmt()!=0){
                        bnpEntity.setBnpType(BnpTypeEnum.getEnum(bnp.getBnpType()));
                        bnpEntity.setOverDueAmt(MoneyUtil.moneyToLong(bnp.getOverDueAmt()));
                        bnpEntity.setPaidAmt(MoneyUtil.moneyToLong(bnp.getPaidAmt()));
                        bnpEntity.setSchdAmt(MoneyUtil.moneyToLong(bnp.getSchdAmt()));
                        bnpEntity.setWavAmt(MoneyUtil.moneyToLong(bnp.getWavAmt()));
                        bnpEntity.setTerm(termEntity);
                        dao.save(bnpEntity);
                    }
                }
            }
        }
        billEntity.setServiceFee(serviceFee);
        billEntity.setTotalIntersetAmt(totalIntersetAmt);
        dao.modify(billEntity);
    }
}
