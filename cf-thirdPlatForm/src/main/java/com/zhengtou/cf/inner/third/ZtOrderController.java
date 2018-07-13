package com.zhengtou.cf.inner.third;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.inner.third.reciveVO.QueryZtOrderVO;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;
import com.zhengtou.cf.thirdOperator.service.ThirdService;
import com.zhengtou.cf.thirdOperator.service.ZtOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@RestController
@RequestMapping("inner/zt")
@Api(description = "郑投服务")
public class ZtOrderController {
    @Autowired
    ThirdService thirdService;
    @Autowired
    ZtOrderService ztOrderService;
    @Autowired
    BaseDao dao;

    @RequestMapping(value = "queryZtOrder/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("郑投发标订单查询")
    public NetVO queryZtOrder(@Validated QueryZtOrderVO queryZtOrderVO, @PathVariable Integer page, @PathVariable Integer size) throws
            BaseException {
        return new ListResponseVO(ztOrderService.fetchZtOrder(queryZtOrderVO.getCreateTimeStart(), queryZtOrderVO.getCreateTimeEnd(),
                queryZtOrderVO.getSignTimeStart(), queryZtOrderVO.getSignTimeEnd(), queryZtOrderVO.getSignStatusEnum(), page, size), ztOrderService
                .countZtOrder(queryZtOrderVO.getCreateTimeStart(), queryZtOrderVO.getCreateTimeEnd(),
                        queryZtOrderVO.getSignTimeStart(), queryZtOrderVO.getSignTimeEnd(), queryZtOrderVO.getSignStatusEnum()));
    }

    @RequestMapping(value = "createZtOrder/{thirdOrderId}", method = RequestMethod.POST)
    @ApiOperation("创建郑投发标订单")
    public NetVO createZtOrder(@PathVariable long thirdOrderId, String note) throws
            BaseException {
        ThirdOrderEntity thirdOrderEntity=dao.get(ThirdOrderEntity.class,thirdOrderId);
        ztOrderService.createZtOrderByThirdOrder(thirdOrderEntity,note);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "changeZtOrderStatus/{id}", method = RequestMethod.PUT)
    @ApiOperation("更改郑投发标订单状态为已发标")
    public NetVO changeZtOrderStatus(@PathVariable long id) throws
            BaseException {
        ztOrderService.changeZtOrderStatus(id, SignStatusEnum.已发标);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "changeZtOrderNoteById/{id}", method = RequestMethod.PUT)
    @ApiOperation("更改郑投发标订单状态为已发标")
    public NetVO changeZtOrderNoteById(@PathVariable long id,String note) throws
            BaseException {
        ztOrderService.changeZtOrderNoteById(id,note);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "test/{thirdOrderId}", method = RequestMethod.PUT)
    @ApiOperation("测试有还款")
    public NetVO test(@PathVariable String thirdOrderId,String term) throws
            BaseException {
        ztOrderService.changeZtOrderByThirdOrderId(thirdOrderId,term);
        return new SuccFessionVO();
    }
}
