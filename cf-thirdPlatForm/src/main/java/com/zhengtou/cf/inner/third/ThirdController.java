package com.zhengtou.cf.inner.third;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.inner.third.reciveVO.QueryThirdUserVO;
import com.zhengtou.cf.thirdOperator.service.ThirdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inner/third/lv")
@Api(description = "绿悦服务")
public class ThirdController {
    @Autowired
    ThirdService thirdService;

    @RequestMapping(value = "queryThirdUser/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("三方用户查询服务")
    public NetVO queryThirdUser(@Validated QueryThirdUserVO queryThirdUserVO, @PathVariable Integer page, @PathVariable Integer size) throws
            BaseException {
        return new ListResponseVO(thirdService.queryThirdUserList(queryThirdUserVO, page, size), thirdService.countThirdUserList(queryThirdUserVO));
    }

    @RequestMapping(value = "queryThirdUserRisk/{thirdUserId}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("三方用户风控数据查询服务")
    public NetVO queryThirdUserRisk(@PathVariable long thirdUserId, @PathVariable Integer page, @PathVariable Integer size) throws BaseException {
        return new ListResponseVO(thirdService.queryThirdRiskByUserId(thirdUserId, page, size), thirdService.countThirdRiskByUserId(thirdUserId));
    }

    @RequestMapping(value = "queryThirdOrder/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("三方用户订单查询服务")
    public NetVO queryThirdOrder(Long thirdUserId, @PathVariable Integer page, @PathVariable Integer size) throws BaseException {
        return new ListResponseVO(thirdService.queryThirdOrderByUserId(thirdUserId, "", page, size), thirdService.countThirdOrderByUserId
                (thirdUserId,""));
    }

    @RequestMapping(value = "queryThirdRepayment/{thirdOrderId}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("三方用户支付记录查询服务")
    public NetVO queryThirdRepayment(@PathVariable long thirdOrderId, @PathVariable Integer page, @PathVariable Integer size) throws BaseException {
        return new ListResponseVO(thirdService.queryThirdRepayByOrderId(thirdOrderId, page, size), thirdService.countThirdRepayByOrderId(thirdOrderId));
    }
}
