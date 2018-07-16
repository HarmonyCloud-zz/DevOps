package com.harmony.devops.user.thirdApi.api.app.v01.trade;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.harmony.devops.user.enums.TdTypeEnum;
import com.harmony.devops.user.enums.ZtProductEnum;
import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.operator.service.OrgService;
import com.harmony.devops.user.risk.service.RiskService;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.harmony.devops.user.user.pojo.entity.person.PersonalEntity;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import com.harmony.devops.user.user.service.ConsumerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单服务
 */
@RestController
@RequestMapping("api/v0.1/trade")
@Api(description = "订单服务")
@Validated
public class TradeOutApi {
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    BaseDao dao;
    @Autowired
    RiskService riskService;
    @Autowired
    OrgService orgService;
    @Autowired
    MyRedisComponent myRedisComponent;

    @RequestMapping(value = "apply/consumer/{token}/{subProductId}/{orgNo}/{loanAmt}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("贷款申请")
    public NetVO apply(@PathVariable String token, @PathVariable long subProductId, @PathVariable String orgNo, @PathVariable String loanAmt,
                       @PathVariable String outTradeFlowNo)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getUserInfoCompleteEnum().equals(UserInfoCompleteEnum.未完善)) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        return new SuccFessionVO();
    }

    @RequestMapping(value = "apply/synOrders/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("订单同步")
    public NetVO synOrders(@PathVariable String orgNo,  @PathVariable String outTradeFlowNo)
            throws BaseException {
        OrganizationEntity organizationEntity = orgService.getOrganizaByOrgNo(orgNo);
        if (organizationEntity == null) {
            return new SuccFessionVO(RtnResultEnum.E080000);
        }
        return new SuccFessionVO();
    }

    @RequestMapping(value = "apply/confirmContract/{token}/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("合同确认")
    public NetVO confirmContract(@PathVariable String token, @PathVariable String outTradeFlowNo)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        return new SuccFessionVO();
    }

    /**
     * 贷款状态查询
     */
    @RequestMapping(value = "queryApplyStatus/{token}/{orgNo}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("贷款状态查询")
    public NetVO queryApplyStatus(@PathVariable String orgNo, @PathVariable String token, @PathVariable String outTradeFlowNo) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = orgService.getOrganizaByOrgNo(orgNo);
        if (organizationEntity == null) {
            return new SuccFessionVO(RtnResultEnum.E040000);
        }
        return new ResponseVO(null);
    }

    /**
     * 合同确认信息查询
     */
    @RequestMapping(value = "queryContract/{token}/{outTradeFlowNo}", method = RequestMethod.POST)
    @ApiOperation("合同简要信息查询")
    public NetVO queryTrade(@PathVariable String token, @PathVariable String outTradeFlowNo) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        return new ResponseVO(null);
    }

    /**
     * 贷款列表查询（用户）
     */
    @RequestMapping(value = "queryTrade/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("贷款列表查询（用户）")
    public NetVO queryTrade(@PathVariable String token, @PathVariable Integer page, @PathVariable Integer size) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        return new SuccFessionVO();
    }
}
