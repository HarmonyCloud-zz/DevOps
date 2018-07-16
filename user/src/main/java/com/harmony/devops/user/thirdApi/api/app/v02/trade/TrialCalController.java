package com.harmony.devops.user.thirdApi.api.app.v02.trade;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("trialCal_v0.2")
@RequestMapping("api/v0.2/trialCal")
@Api(description = "试算服务")
@Validated
public class TrialCalController {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;

    @RequestMapping(value = "getSettleCalcuation/{token}/{tradeId}", method = RequestMethod.GET)
    @ApiOperation(value = "获取提前结清试算")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "获取提前结清试算")})
    public NetVO getSettleCalcuation(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value =
            "贷款订单id", name =
            "tradeId") long tradeId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        return null;
    }

    /**
     * 借款试算
     */
    @RequestMapping(value = "loanTrial/{subProductId}/{withDrawAmt}", method = RequestMethod.GET)
    @ApiOperation("借款试算")
    @ApiResponses({@ApiResponse(code = 200, response = String.class, message = "借款试算")})
    public NetVO LoanTrial(@PathVariable @ApiParam(value = "贷款子产品id", name = "subProductId") long subProductId, @PathVariable @ApiParam(value =
            "贷款金额", name = "withDrawAmt") String withDrawAmt) throws BaseException {
        return null;
    }
}
