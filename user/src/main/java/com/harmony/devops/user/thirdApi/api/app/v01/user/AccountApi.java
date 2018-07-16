package com.harmony.devops.user.thirdApi.api.app.v01.user;

import com.harmony.devops.user.thirdApi.util.CommonUtil;
import com.zhengtou.cf.common.annotation.validator.TradePwd;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.harmony.devops.user.enums.RedisKeyPrefixEnum;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0.1/consumer/account")
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
    public NetVO setTradePwd(@PathVariable String token, @PathVariable @TradePwd String tradePwd) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getHasTradePwd()) {
            return new SuccFessionVO(RtnResultEnum.E000003);
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
    public NetVO getUpdateTradePwdSmsCode(@PathVariable String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }

        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.TRADE_PWD_SMS_CODE.getPrefix() + userItemVO.getPhone());
        if (regCode != null) {
            return new SuccFessionVO(RtnResultEnum.E010001);
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
    public NetVO updateTradePwd(@PathVariable String token, @PathVariable String smsCode, @PathVariable @TradePwd String newTradePwd) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        String hasCode=(String)myRedisComponent.get(RedisKeyPrefixEnum.TRADE_PWD_SMS_CODE.getPrefix()+userItemVO.getPhone());
        CommonUtil.checkCode(hasCode,smsCode);
        UserEntity userEntity = dao.get(UserEntity.class, userItemVO.getId());
        userEntity.setTradePwd(newTradePwd);
        dao.saveOrModify(userEntity);
        return new SuccFessionVO();
    }
}
