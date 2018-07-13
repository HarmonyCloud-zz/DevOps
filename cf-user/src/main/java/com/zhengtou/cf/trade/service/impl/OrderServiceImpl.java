package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.trade.controller.receiveVO.OrderProductReciveVO;
import com.zhengtou.cf.trade.controller.receiveVO.OrderReciveVO;
import com.zhengtou.cf.trade.pojo.entity.order.OrderEntity;
import com.zhengtou.cf.trade.pojo.entity.order.OrderProductEntity;
import com.zhengtou.cf.trade.pojo.vo.OrderProductVO;
import com.zhengtou.cf.trade.pojo.vo.OrderVO;
import com.zhengtou.cf.trade.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BaseDao dao;

    @Override
    public OrderEntity saveOrder(OrderReciveVO orderReciveVO, OrganizationEntity organizationEntity, String outTradeFlowNo) {
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderReciveVO, orderEntity);
        orderEntity.setReferenceId(outTradeFlowNo);
        orderEntity.setOrg(organizationEntity);
        dao.save(orderEntity);
        if (orderReciveVO.getOrderProductInfo() != null && !orderReciveVO.getOrderProductInfo().isEmpty()) {
            for (OrderProductReciveVO orderProductVO : orderReciveVO.getOrderProductInfo()) {
                OrderProductEntity orderProductEntity = new OrderProductEntity();
                BeanUtils.copyProperties(orderProductVO, orderProductEntity);
                orderProductEntity.setOrderEntity(orderEntity);
                dao.save(orderProductEntity);
            }
        }
        return orderEntity;
    }

    @Override
    public OrderVO getOrderVOByOutTradeFlowNo(String outTradeFlowNo) {
        OrderEntity orderEntity = dao.get(" from OrderEntity o where o.referenceId=?0", new Object[]{outTradeFlowNo});
        if (orderEntity == null) {
            return null;
        }
        List<OrderProductVO> orderProductVOS = dao.find("select new com.zhengtou.cf.trade.pojo.vo.OrderProductVO(o.commodityId,o.brand,o" +
                ".commodityName,o.price,o.quantity) from OrderProductEntity o where o.orderEntity=?0", new Object[]{orderEntity});
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderEntity, orderVO);
        orderVO.setCompanyId(orderEntity.getOrg().getId() + "");
        if (orderProductVOS != null && !orderProductVOS.isEmpty()) {
            orderVO.setOrderProductInfo(orderProductVOS);
        }
        return orderVO;
    }
}
