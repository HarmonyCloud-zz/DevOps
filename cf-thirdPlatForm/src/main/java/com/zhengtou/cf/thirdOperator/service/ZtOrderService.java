package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;
import com.zhengtou.cf.thirdOperator.pojo.vo.ZtOrderVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface ZtOrderService {
    /**
     * 创建郑投订单
     */
    void createZtOrderByThirdOrder(ThirdOrderEntity thirdOrder, String note);

    /**
     * 更改订单发标状态
     */
    void changeZtOrderStatus(long ztOrderId, SignStatusEnum signStatusEnum);

    /**
     * 有还款
     */
    void changeZtOrderByThirdOrderId(String thirdOrderId, String term) throws BaseException;

    /**
     * 修改备注
     */
    void changeZtOrderNoteById(long ztOrderId, String note);

    /**
     * 列表
     */
    List<ZtOrderVO> fetchZtOrder(Long createTimeStart, Long createTimeEnd, Long signTimeStart, Long signTimeEnd, SignStatusEnum signStatusEnum,
                                 Integer page, Integer size);

    Long countZtOrder(Long createTimeStart, Long createTimeEnd, Long signTimeStart, Long signTimeEnd, SignStatusEnum signStatusEnum);
}
