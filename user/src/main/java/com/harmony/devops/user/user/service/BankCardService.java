package com.harmony.devops.user.user.service;

import com.harmony.devops.user.user.pojo.entity.BankCardEntity;
import com.harmony.devops.user.user.pojo.entity.enums.BindCardStatusEnum;
import com.harmony.devops.user.user.pojo.vo.BindCardTradeVO;
import com.harmony.devops.user.user.pojo.vo.CardVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface BankCardService {
    /**
     * 已绑卡记录
     */
    List<CardVO> getBankCardByUserId(long userId);
    /**
     * 查询默认收款卡
     */
    BankCardEntity queryDefaultBankCardEntityByUserId(long userId,boolean isDefault);
    /**
     * 查询默认还款卡
     */
    BankCardEntity queryRepayDefaultBankCardEntityByUserId(long userId,boolean isRepayDefault);

    /**
     * 绑卡记录查询
     */
    List<BindCardTradeVO> getBindCardTrade(String consumerRealName, String phone, String idnum, String cardNo, String requestno,
                                             BindCardStatusEnum status,Integer page, Integer size);

    Long countBindCardTrade(String consumerRealName, String phone, String idnum, String cardNo, String requestno,
                            BindCardStatusEnum status);
}
