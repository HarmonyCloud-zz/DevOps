package com.zhengtou.cf.risk.controller;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.enums.TdTypeEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.RiskRecordEntity;
import com.zhengtou.cf.risk.pojo.entity.rule.RuleEntity;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskItemVO;
import com.zhengtou.cf.risk.pojo.vo.risk.RiskVO;
import com.zhengtou.cf.risk.pojo.vo.rule.RuleVO;
import com.zhengtou.cf.risk.service.RiskService;
import com.zhengtou.cf.risk.service.RuleService;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.service.ConsumerUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rule")
@Api(description = "风控规则服务")
public class RuleController {
    @Autowired
    BaseDao dao;
    @Autowired
    RuleService ruleService;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    RiskService riskService;

    @RequestMapping(value = "getRuleList/{token}", method = RequestMethod.POST)
    @ApiOperation("获取规则列表")
    public NetVO getRuleList(RuleVO ruleVO, Integer page, Integer rows) {
        return new ListResponseVO(ruleService.getRuleList(ruleVO, page, rows), ruleService.countRuleList(ruleVO));
    }

    @RequestMapping(value = "getRuleRecords/{token}/{userId}", method = RequestMethod.POST)
    @ApiOperation("获取最新命中记录")
    public NetVO getRuleRecords(@PathVariable long userId) throws BaseException {
        ConsumerUserEntity consumerUserEntity=dao.get(ConsumerUserEntity.class,userId);
        RiskRecordEntity riskRecordEntity=riskService.getNewRiskRecordEntityByConsumerUser(consumerUserEntity);
        List<RiskItemVO> ruleRecordVOs=riskService.getRuleRecords(riskRecordEntity);
        return new ListResponseVO(ruleRecordVOs,ruleRecordVOs.size());
    }

    @RequestMapping(value = "getRiskResult/{token}", method = RequestMethod.POST)
    @ApiOperation("风控")
    public NetVO getRuleList(@PathVariable String token,TdTypeEnum tdTypeEnum) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        ConsumerUserEntity consumerUserEntity=dao.get(ConsumerUserEntity.class,userItemVO.getId());
        RiskVO riskVO=riskService.getRiskResultByConsumerUser(consumerUserEntity);
        return new ResponseVO(riskVO);
    }

    @RequestMapping(value = "addRule/{token}", method = RequestMethod.POST)
    @ApiOperation("添加风控规则")
    public NetVO addRule(@Validated RuleVO ruleVO) {
        RuleEntity ruleEntity=new RuleEntity();
        BeanUtils.copyProperties(ruleVO,ruleEntity);
        dao.save(ruleEntity);
        return new SuccFessionVO();
    }
}
