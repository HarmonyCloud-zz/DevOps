package com.harmony.devops.user.thirdApi.api.app.v02.user;

import com.harmony.devops.user.thirdApi.util.CommonUtil;
import com.zhengtou.cf.common.annotation.validator.MsgCode;
import com.zhengtou.cf.common.annotation.validator.TradePwd;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.harmony.devops.user.enums.RedisKeyPrefixEnum;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import io.swagger.annotations.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("account_v0.2")
@RequestMapping("api/v0.2/consumer/account")
@Api(description = "账户管理模块")
@Validated
public class AccountApi {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;

    /**
     * 设置用户交易密码
     */
    @RequestMapping(value = "setTradePwd/{token}/{tradePwd}", method = RequestMethod.PUT)
    @ApiOperation("设置交易密码")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "设置交易密码返回")})
    public NetVO setTradePwd(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @TradePwd @ApiParam(value =
            "交易密码", name = "tradePwd") String tradePwd) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getHasTradePwd()) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        UserEntity userEntity = dao.get(UserEntity.class, userItemVO.getId());
        userEntity.setTradePwd(tradePwd);
        dao.saveOrModify(userEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取更新交易密码验证码
     */
    @RequestMapping(value = "getUpdateTradePwdSmsCode/{token}", method = RequestMethod.GET)
    @ApiOperation("获取更新交易密码短信")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "设置交易密码返回")})
    public NetVO getUpdateTradePwdSmsCode(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }

        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.TRADE_PWD_SMS_CODE.getPrefix() + userItemVO.getPhone());
        if (regCode != null) {
            throw new BaseException(RtnResultEnum.E010001);
        }
        String smsCode = RandomStringUtils.randomNumeric(6);
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.TRADE_PWD_SMS_CODE.getPrefix() + userItemVO.getPhone(), smsCode, 60L);
        return new SuccFessionVO();
    }

    /**
     * 更新用户交易密码
     */
    @RequestMapping(value = "updateTradePwd/{token}/{smsCode}/{newTradePwd}", method = RequestMethod.PUT)
    @ApiOperation("更新交易密码")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "设置交易密码返回")})
    public NetVO updateTradePwd(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @MsgCode @ApiParam(value =
            "短信验证码", name = "smsCode") String smsCode, @PathVariable @TradePwd @ApiParam(value = "交易密码", name = "newTradePwd") String newTradePwd) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.TRADE_PWD_SMS_CODE.getPrefix() + userItemVO.getPhone());
        CommonUtil.checkCode(hasCode, smsCode);
        UserEntity userEntity = dao.get(UserEntity.class, userItemVO.getId());
        userEntity.setTradePwd(newTradePwd);
        dao.saveOrModify(userEntity);
        return new SuccFessionVO();
    }
}
