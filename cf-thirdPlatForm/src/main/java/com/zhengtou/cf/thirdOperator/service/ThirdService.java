package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.inner.third.reciveVO.QueryThirdUserVO;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdOrderVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRepaymentVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdRiskVO;
import com.zhengtou.cf.thirdOperator.pojo.vo.ThirdUserVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface ThirdService {
    /**
     * 三方用户检索
     */
    List<ThirdUserVO> queryThirdUserList(QueryThirdUserVO queryThirdUserVO, Integer page, Integer size);
    Long countThirdUserList(QueryThirdUserVO queryThirdUserVO);
    /**
     * 更具用户id拿到所有风控记录
     */
    List<ThirdRiskVO> queryThirdRiskByUserId(long thirdUserId, Integer page, Integer size);
    Long countThirdRiskByUserId(long thirdUserId);
    /**
     * 查询三方订单记录
     */
    List<ThirdOrderVO> queryThirdOrderByUserId(Long thirdUserId,String thirOrderId,Integer page,Integer size);
    Long countThirdOrderByUserId(Long thirdUserId,String thirOrderId);


    /**
     * 查询三方还款记录
     */
    List<ThirdRepaymentVO> queryThirdRepayByOrderId(long thirdOrderId, Integer page, Integer size);
    Long countThirdRepayByOrderId(long thirdOrderId);

    ThirdOrderEntity getThirdOrderByThirdOrderId(String thirdOrderId);
}
