package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.pojo.vo.PaymentVO;
import com.zhengtou.cf.trade.pojo.vo.TradeVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;

import java.util.List;

public interface ContractService {
    /**
     * 保存合同信息
     */
    void saveContract(TradeVO tradeVO,String contractPdfPath);

    String saveContractPdf(ConsumerUserVO consumerUserVO, TradeEntity tradeEntity) throws BaseException;
    /**
     * 获取所有打款列表
     */
    List<PaymentVO> getPaymentList(Integer page, Integer size);
    Long countPayment();

}
