package com.zhengtou.cf.api.app.v02.user;

import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.BindCardEnum;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.DateUtils;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.user.controller.reciveVO.CardBindingReciveVO;
import com.zhengtou.cf.user.pojo.entity.BankCardEntity;
import com.zhengtou.cf.user.pojo.entity.BindCardTradeEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.BindCardStatusEnum;
import com.zhengtou.cf.user.pojo.entity.enums.ChannelTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.PersonalEntity;
import com.zhengtou.cf.user.pojo.vo.CardVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.service.BankCardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("bankCard_v0.2")
@RequestMapping("api/v0.2/bankCard")
@Api(description = "银行卡服务")
@Validated
public class BankCardApi {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    PayApi payApi;
    @Autowired
    BaseDao dao;
    @Autowired
    BankCardService bankCardService;

    /**
     * 添加银行卡信息(绑卡)
     */
    @RequestMapping(value = "authBankCard/{token}", method = RequestMethod.POST)
    @ApiOperation("银行卡四要素认证")
    public NetVO addBankCard(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated CardBindingReciveVO bindingVO)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        ConsumerUserEntity user = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        String requestno = DBUtil.getBindCardNo();
        String requesttime = DateUtils.getSysDateTimeString();
        BindCardEnum bindCardEnum = payApi.authBankCard(requestno, bindingVO.getCardNo(), personalEntity.getIdNo(), personalEntity.getName(),
                userItemVO.getPhone(), requesttime);

        if (bindCardEnum.equals(BindCardEnum.TO_VALIDATE)) {
            BankCardEntity bankCardEntity = dao.get("from BankCardEntity b where b.isDeleted=false and b.cardNo=?0", new Object[]{bindingVO
                    .getCardNo()});
            if (bankCardEntity == null) {
                bankCardEntity = new BankCardEntity();
                bankCardEntity.setBankName(BankCodeEnum.valueOf(bindingVO.getBankName()));
                bankCardEntity.setUser(user);
                bankCardEntity.setCardNo(bindingVO.getCardNo());
                bankCardEntity.setChannelType(ChannelTypeEnum.易宝);
                bankCardEntity.setStatus(BindCardStatusEnum.待短验);
                dao.save(bankCardEntity);
            }
            BindCardTradeEntity bindCardTrade = new BindCardTradeEntity();
            bindCardTrade.setRequestno(requestno);
            bindCardTrade.setBankCard(bankCardEntity);
            bindCardTrade.setStatus(BindCardStatusEnum.待短验);
            bindCardTrade.setConsumerUser(user);
            bindCardTrade.setRequesttime(DateUtils.getSysDateTimeString());
            dao.save(bindCardTrade);
            return new ResponseVO(dao.get("select b from BindCardTradeEntity b where b.isDeleted=false and b.requestno=?0", new
                    Object[]{requestno}));
        } else {
            throw new BaseException(bindCardEnum.name(), bindCardEnum.getCode());
        }
    }

    @RequestMapping(value = "authSmsCode/{token}", method = RequestMethod.POST)
    @ApiOperation("银行卡短信验证")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "提前结清返回")})
    public NetVO authSmsCode(@PathVariable @ApiParam(value = "token", name = "token") String token, @ApiParam(value = "绑卡请求编号，绑卡验证返回", name =
            "requestNo") String requestNo, @ApiParam(value = "银行卡号", name = "cardNo") String cardNo, @RequestParam @ApiParam(value = "绑卡验证码", name =
            "code") String code) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        BindCardTradeEntity bindCardTrade = new BindCardTradeEntity();
        if (StringUtils.isBlank(requestNo) && StringUtils.isBlank(cardNo)) {
            throw new BaseException(RtnResultEnum.E030000);
        }
        if (StringUtils.isBlank(requestNo)) {
            bindCardTrade = dao.get("select b from BindCardTradeEntity b where b.isDeleted=false and b.bankCard.cardNo=?0", new
                    Object[]{cardNo});
            requestNo = bindCardTrade.getRequestno();
        }
        payApi.authCheck(requestNo, code);
        bindCardTrade.setStatus(BindCardStatusEnum.已绑定);
        dao.saveOrModify(bindCardTrade);
        BankCardEntity bankCardEntity = bindCardTrade.getBankCard();
        List<CardVO> carVOS = bankCardService.getBankCardByUserId(userItemVO.getId());
        bankCardEntity.setIsDefault((carVOS == null || carVOS.isEmpty()) ? true : false);
        bankCardEntity.setIsRepayDefault((carVOS == null || carVOS.isEmpty()) ? true : false);
        bankCardEntity.setStatus(BindCardStatusEnum.已绑定);
        dao.modify(bankCardEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getBankCardList/{token}", method = RequestMethod.GET)
    @ApiOperation("获取银行卡列表")
    @ApiResponses({@ApiResponse(code = 200, response = CardVO.class, message = "获取银行卡列表")})
    public NetVO getBankCardList(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<CardVO> cards = bankCardService.getBankCardByUserId(userItemVO.getId());
        return new ListResponseVO(cards);
    }

    @RequestMapping(value = "deletedBankCard/{token}/{cardId}", method = RequestMethod.DELETE)
    @ApiOperation("银行卡解绑-模拟解绑")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "银行卡解绑-模拟解绑")})
    public NetVO deletedBankCard(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "银行卡id",
            name = "cardId") long cardId) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<CardVO> cards = bankCardService.getBankCardByUserId(userItemVO.getId());
        if (cards.size() == 1) {
            throw new BaseException(RtnResultEnum.E010000);
        }
        BankCardEntity bankCardEntity = dao.get(BankCardEntity.class, cardId);
        if (bankCardEntity.getIsDefault()) {
            throw new BaseException(RtnResultEnum.E020002);
        }
        if (bankCardEntity.getIsRepayDefault()) {
            throw new BaseException(RtnResultEnum.E020002);
        }
        bankCardEntity.setIsDeleted(true);
        dao.modify(bankCardEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "setDefaultBankCard/{token}/{cardId}", method = RequestMethod.PUT)
    @ApiOperation("设置默认收款银行卡")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "设置默认收款银行卡")})
    public NetVO getBankCardList(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "银行卡id",
            name = "cardId") long cardId) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        BankCardEntity bankCardEntity = bankCardService.queryDefaultBankCardEntityByUserId(userItemVO.getId(), true);
        if (bankCardEntity != null) {
            if (bankCardEntity.getId() == cardId) {
                return new SuccFessionVO(RtnResultEnum.E080007);
            }
            bankCardEntity.setIsDefault(false);
            dao.modify(bankCardEntity);
        }
        BankCardEntity bankCardEntity1 = dao.get(BankCardEntity.class, cardId);
        bankCardEntity1.setIsDefault(true);
        dao.modify(bankCardEntity1);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "setRepayDefaultBankCard/{token}/{cardId}", method = RequestMethod.PUT)
    @ApiOperation("设置默认还款银行卡")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "设置默认还款银行卡")})
    public NetVO setRepayDefaultBankCard(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "银行卡id",
            name = "cardId") long cardId) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        BankCardEntity bankCardEntity = bankCardService.queryRepayDefaultBankCardEntityByUserId(userItemVO.getId(), true);
        if (bankCardEntity != null) {
            if (bankCardEntity.getId() == cardId) {
                return new SuccFessionVO(RtnResultEnum.E080008);
            }
            bankCardEntity.setIsRepayDefault(false);
            dao.modify(bankCardEntity);
        }
        BankCardEntity bankCardEntity1 = dao.get(BankCardEntity.class, cardId);
        bankCardEntity1.setIsRepayDefault(true);
        dao.modify(bankCardEntity1);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "queryRepayDefaultBankCard/{token}", method = RequestMethod.GET)
    @ApiOperation("查询默认还款银行卡")
    @ApiResponses({@ApiResponse(code = 200, response = CardVO.class, message = "查询默认还款银行卡")})
    public NetVO queryRepayDefaultBankCard(@PathVariable @ApiParam(value = "token", name = "token") String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        BankCardEntity bankCardEntity = bankCardService.queryRepayDefaultBankCardEntityByUserId(userItemVO.getId(), true);
        CardVO cardVO = new CardVO(bankCardEntity.getId(), bankCardEntity.getCardNo(), bankCardEntity.getUser().getPhone(), bankCardEntity
                .getBankName(), bankCardEntity.getIsDefault(), bankCardEntity.getIsRepayDefault());
        return new ResponseVO(cardVO);
    }

    @RequestMapping(value = "queryDefaultBankCard/{token}", method = RequestMethod.GET)
    @ApiOperation("查询默认收款银行卡")
    @ApiResponses({@ApiResponse(code = 200, response = CardVO.class, message = "查询默认收款银行卡")})
    public NetVO queryDefaultBankCard(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        BankCardEntity bankCardEntity = bankCardService.queryDefaultBankCardEntityByUserId(userItemVO.getId(), true);
        if (bankCardEntity == null) {
            throw new BaseException(RtnResultEnum.E020001);
        }
        CardVO cardVO = new CardVO(bankCardEntity.getId(), bankCardEntity.getCardNo(), bankCardEntity.getUser().getPhone(), bankCardEntity
                .getBankName(), bankCardEntity.getIsDefault(), bankCardEntity.getIsRepayDefault());
        return new ResponseVO(cardVO);
    }
}
