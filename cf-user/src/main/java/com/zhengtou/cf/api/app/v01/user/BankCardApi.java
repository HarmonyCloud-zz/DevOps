package com.zhengtou.cf.api.app.v01.user;

import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.BindCardEnum;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v0.1/bankCard")
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
    public NetVO addBankCard(@PathVariable String token, @Validated CardBindingReciveVO bindingVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        ConsumerUserEntity user = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        try {
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
                return new ErrorFessionVO(bindCardEnum.name(), bindCardEnum.getCode());
            }
        } catch (BaseException e) {
            return new SuccFessionVO(e.getErrorCode(), e.getErrorMsg());
        }
    }

    @RequestMapping(value = "authSmsCode/{token}", method = RequestMethod.POST)
    @ApiOperation("银行卡短信验证")
    public NetVO authSmsCode(@PathVariable String token, String requestNo, String cardNo, @RequestParam String code) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        BindCardTradeEntity bindCardTrade = new BindCardTradeEntity();
        if (StringUtils.isBlank(requestNo)) {
            bindCardTrade = dao.get("select b from BindCardTradeEntity b where b.isDeleted=false and b.bankCard.cardNo=?0", new
                    Object[]{cardNo});
            requestNo = bindCardTrade.getRequestno();
        }
        if (StringUtils.isNotBlank(cardNo)) {
            bindCardTrade = dao.get("select b from BindCardTradeEntity b where b.isDeleted=false and b.bankCard.cardNo=?0", new
                    Object[]{cardNo});
        }
        payApi.authCheck(requestNo, code);
        bindCardTrade.setStatus(BindCardStatusEnum.已绑定);
        dao.saveOrModify(bindCardTrade);
        BankCardEntity bankCardEntity = bindCardTrade.getBankCard();
        List<CardVO> carVOS = bankCardService.getBankCardByUserId(userItemVO.getId());
        bankCardEntity.setIsDefault(carVOS == null ? true : false);
        bankCardEntity.setIsRepayDefault(carVOS == null ? true : false);
        bankCardEntity.setStatus(BindCardStatusEnum.已绑定);
        dao.modify(bankCardEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getBankCardList/{token}", method = RequestMethod.POST)
    @ApiOperation("获取银行卡列表")
    public NetVO getBankCardList(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null)
            return new ErrorFessionVO(RtnResultEnum.E000003);
        List<CardVO> cards = bankCardService.getBankCardByUserId(userItemVO.getId());
        return new ListResponseVO(cards);
    }
}
