package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.trade.controller.receiveVO.OrderReciveVO;
import com.zhengtou.cf.trade.pojo.entity.order.OrderEntity;
import com.zhengtou.cf.trade.pojo.vo.OrderVO;

/**
 * 订单业务service
 * @author 葛文镇
 */
public interface OrderService {
    /**
     * 保存订单信息
     */
    OrderEntity saveOrder(OrderReciveVO orderReciveVO, OrganizationEntity organizationEntity, String outTradeFlowNo);

    /**
     * 长亮订单
     */
    OrderVO getOrderVOByOutTradeFlowNo(String outTradeFlowNo);
}
