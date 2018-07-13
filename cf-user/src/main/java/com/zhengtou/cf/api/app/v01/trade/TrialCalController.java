package com.zhengtou.cf.api.app.v01.trade;

import com.zhengtou.cf.api.cl.TrialCalApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.PayMethodEnum;
import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;
import com.zhengtou.cf.product.service.ProductService;
import com.zhengtou.cf.trade.pojo.entity.trade.TradeEntity;
import com.zhengtou.cf.trade.service.TradeService;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0.1/trialCal")
@Api(description = "试算服务")
@Validated
public class TrialCalController {
    @Autowired
    TradeService tradeService;
    @Autowired
    TrialCalApi trialCalApi;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    ProductService subProductService;

    @RequestMapping(value = "getSettleCalcuation/{token}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation(value = "获取提前结清试算")
    public NetVO getSettleCalcuation(@PathVariable String token, @PathVariable String outTradeFlowNo) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        TradeEntity tradeEntity = dao.get("from TradeEntity t where t.isDeleted=false and t.outTradeFlowNo=?0", new Object[]{outTradeFlowNo});
        if (tradeEntity == null) {
            return new ErrorFessionVO(RtnResultEnum.E030001);
        }
        return new ResponseVO(null);
    }

    /**
     * 借款试算
     */
    @RequestMapping(value = "loanTrial/{subProductId}/{withDrawAmt}", method = RequestMethod.POST)
    @ApiOperation("借款试算")
    public NetVO LoanTrial(@PathVariable long subProductId, @PathVariable String withDrawAmt) throws BaseException {
        SubProductVO subProductVO = subProductService.getSubProductById(subProductId);
        if (subProductVO == null) {
            return new SuccFessionVO(RtnResultEnum.E070005);
        }
        LoanItem loanItem = trialCalApi.getLoanCalcuation(subProductVO.getProductCd(), PayMethodEnum.getEnum(subProductVO.getRepayMethod().name()),
                subProductVO.getCycleCnt(), withDrawAmt + "");
        return new ResponseVO(loanItem);
    }
}
