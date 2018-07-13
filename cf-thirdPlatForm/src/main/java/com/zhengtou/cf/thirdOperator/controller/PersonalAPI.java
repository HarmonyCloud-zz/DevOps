package com.zhengtou.cf.thirdOperator.controller;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.AntifraudVO;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.risk.riskApi.tdapi.TdApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdTypeEnum;
import com.zhengtou.cf.risk.service.TdRiskService;
import com.zhengtou.cf.thirdOperator.controller.lvyue.UserReceiveVO;
import com.zhengtou.cf.thirdOperator.controller.result.RiskResultVO;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdCreditScore;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdOrderEntity;
import com.zhengtou.cf.thirdOperator.pojo.entity.ThirdUserEntity;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdOrderStatusEnum;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdTypeEnum;
import com.zhengtou.cf.thirdOperator.service.ThirdService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("thirdPerson")
public class PersonalAPI {
    @Autowired
    BaseDao dao;
    @Autowired
    ThirdService thirdService;
    @Autowired
    TdApi tdApi;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    TdRiskService tdRiskService;

    private static final Log logger = LogFactory.getLog(PersonalAPI.class);

    @RequestMapping(value = "personalInfo", method = RequestMethod.POST)
    @ApiOperation(value = "同步用户基本信息")
    public NetVO savePersonalInfo(@RequestBody String param) {
        try {
            logger.info("save personalInfo result:" + param);
            UserReceiveVO userReceiveVO = JSON.parseObject(param, UserReceiveVO.class);
            ThirdUserEntity thirdUser = dao.get("from ThirdUserEntity t where t.isDeleted=false and t.idNo=?0", new Object[]{userReceiveVO
                    .getUser_id_card()});
            if (thirdUser == null) {
                thirdUser = new ThirdUserEntity();
                thirdUser.setIdNo(userReceiveVO.getUser_id_card());
                thirdUser.setLoginName(userReceiveVO.getUser_phone());
                thirdUser.setPassword(DBUtil.generatePassword());
                thirdUser.setNickName(userReceiveVO.getUser_name());
                thirdUser.setPhone(userReceiveVO.getUser_phone());
                thirdUser.setUserType(ThirdTypeEnum.绿悦);
                thirdUser.setName(userReceiveVO.getUser_name());
                dao.save(thirdUser);

                ThirdCreditScore thirdCreditScore = new ThirdCreditScore();
                thirdCreditScore.setOrderComeForm(userReceiveVO.getOrder_comefrom());
                thirdCreditScore.setThirdUser(thirdUser);
                thirdCreditScore.setScore(userReceiveVO.getCredit_score());
                dao.save(thirdCreditScore);
            } else {
                ThirdCreditScore thirdCreditScore = new ThirdCreditScore();
                thirdCreditScore.setOrderComeForm(userReceiveVO.getOrder_comefrom());
                thirdCreditScore.setThirdUser(thirdUser);
                thirdCreditScore.setScore(userReceiveVO.getCredit_score());
                dao.save(thirdCreditScore);
            }

            ThirdOrderEntity thirdOrder = new ThirdOrderEntity();
            thirdOrder.setIdNo(userReceiveVO.getUser_id_card());
            thirdOrder.setGoodCate(userReceiveVO.getGoods_order_attr());
            thirdOrder.setGoodName(userReceiveVO.getGoods_name());
            thirdOrder.setGoodPrice(MoneyUtil.moneyToLong(userReceiveVO.getGoods_price()));
            thirdOrder.setGoodPurPrice(MoneyUtil.moneyToLong(userReceiveVO.getGoods_pur_price()));
            thirdOrder.setOrderComeForm(userReceiveVO.getOrder_comefrom());
            thirdOrder.setOrderGrossRent(MoneyUtil.moneyToLong(userReceiveVO.getOrder_gross_rent()));
            thirdOrder.setMonthAmt(MoneyUtil.moneyToLong(userReceiveVO.getOrder_monthly_rent()));
            thirdOrder.setOrderNo(DBUtil.getOrderNo());
            thirdOrder.setOrderTerm(userReceiveVO.getOrder_tenancy_term());
            thirdOrder.setThirdCreateTime(TimeUtil.timeToLong(userReceiveVO.getCreate_time()));
            thirdOrder.setThirdOrderId(userReceiveVO.getOrder_id());
            thirdOrder.setThirdOrderNo(userReceiveVO.getOrder_id());
            thirdOrder.setThirdOrderStatus(userReceiveVO.getOrder_status().equals("1") ? ThirdOrderStatusEnum.待审核 : ThirdOrderStatusEnum.待发货);
            dao.save(thirdOrder);
            return new SuccFessionVO();
        } catch (Exception e) {
            return new ErrorFessionVO("false", e.getMessage());
        }
    }

    @RequestMapping(value = "queryPersonalRisk/{thirdOrderId}", method = RequestMethod.POST)
    @ApiOperation(value = "用户风控")
    public NetVO queryPersonalRisk(@PathVariable String thirdOrderId) throws BaseException {
        logger.info("用户风控:" + thirdOrderId);
        ThirdOrderEntity thirdOrderEntity = thirdService.getThirdOrderByThirdOrderId(thirdOrderId);
        if (thirdOrderEntity == null) {
            return new ErrorFessionVO("false", "三方订单不存在");
        }
        ThirdUserEntity thirdUser = dao.get("from ThirdUserEntity t where t.isDeleted=false and t.idNo=?0", new Object[]{thirdOrderEntity
                .getIdNo()});
        if (thirdUser == null) {
            return new ErrorFessionVO("false", "该借款人不存在");
        }
        RiskResultVO riskResultVO = (RiskResultVO) myRedisComponent.get("tdRiskResult_" + thirdUser.getIdNo());
        if (riskResultVO == null) {
            TDApiRequest apiRequest = new TDApiRequest(thirdUser.getIdNo(), thirdUser.getPhone(), thirdUser.getName());
            TDApiResponse tdApiResponse = tdApi.tdApi(TdTypeEnum.web, apiRequest);
            if (tdApiResponse.getSuccess()) {
                tdRiskService.saveTdRisk(thirdUser.getName(), thirdUser.getPhone(), thirdUser.getIdNo(), tdApiResponse);
                AntifraudVO antifraudVO = tdApiResponse.getResult_desc().getANTIFRAUD();
                riskResultVO = new RiskResultVO(antifraudVO.getFinal_decision(), JSON.toJSONString(antifraudVO.getRisk_items()));
                myRedisComponent.setDay("tdRiskResult_" + thirdUser.getIdNo(), riskResultVO, 7l);
                return new ResponseVO(riskResultVO);
            } else {
                return new ErrorFessionVO(tdApiResponse.getReason_code(), tdApiResponse.getReason_desc());
            }
        }
        return new ResponseVO(riskResultVO);
    }

}
