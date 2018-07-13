package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.trade.pojo.entity.enums.TermStatusEnum;
import com.zhengtou.cf.trade.pojo.vo.ReceiptTaskVO;
import com.zhengtou.cf.trade.pojo.vo.ReceiptVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 期供服务
 */
public interface TermService {
    /**
     * 更改期供状态
     */
    void changeTermStatus(long termId, TermStatusEnum termStatusEnum,String paymentTransNo,String errorCode,String errorDesc);

    /**
     * 获取扣款列表
     * 定时器
     */
    List<ReceiptTaskVO> getReceiptAll(Long pmtDueDateStart, Long pmtDueDateEnd, TermStatusEnum termStatusEnum);

    /**
     * 根据termId拿到扣款信息
     */
    ReceiptTaskVO getReceiptByTermId(long termId);

    /**
     * 获取扣款列表
     */
    List<ReceiptVO> getReceiptList(Integer page, Integer size);
    Long countReceipt();

    /**
     * 根据tradeId拿到所有期供信息
     */
    List<ReceiptVO> getReceiptByTradeId(long tradeId,Integer page,Integer size);
    Long countReceiptByTradeId(long tradeId);
}
