package com.harmony.devops.user.user.controller;

import com.zhengtou.cf.common.annotation.validator.PassWord;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.harmony.devops.user.enums.RedisKeyPrefixEnum;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.service.UserService;
import com.harmony.devops.user.thirdApi.util.CommonUtil;
import com.harmony.devops.user.thirdApi.util.VerifyCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("user")
@Api(description = "基础用户服务")
@Validated
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;

    @RequestMapping(value = "updatePwdSms/{phone}/{code}/{password}", method = RequestMethod.PUT)
    @ApiOperation("忘记密码使用验证码修改密码")
    public NetVO updatePwdSms(@PathVariable @Phone String phone, @PathVariable @PassWord String password, @PathVariable String code) throws
            BaseException {
        //验证验证码
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone);
        CommonUtil.checkCode(hasCode, code);
        UserEntity user = userService.getUserByPhone(phone);
        if (user == null) {
            throw  new BaseException(RtnResultEnum.E000000);
        }
        user.setPassword(password);
        dao.saveOrModify(user);
        return new SuccFessionVO();
    }

    /**
     * 获取注册短信
     */
    @RequestMapping(value = "getRegSmsCode/{phone}/{pngToken}/{pngCode}", method = RequestMethod.GET)
    @ApiOperation("获取注册短信")
    public NetVO getRegSmsCode(@PathVariable @Phone String phone, @PathVariable String pngCode, @PathVariable String pngToken) throws BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken);
        CommonUtil.checkCode(hasCode, pngCode);
        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone);
        if (regCode != null) {
            throw new BaseException(RtnResultEnum.E010001);
        }
        String smsCode = RandomStringUtils.randomNumeric(6);
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone, smsCode, 60L);
        return new SuccFessionVO();
    }

    /**
     * 获取改密短信
     */
    @RequestMapping(value = "getPwdSmsCode/{phone}/{pngToken}/{pngCode}", method = RequestMethod.GET)
    @ApiOperation("获取改密短信")
    public NetVO getPwdSmsCode(@PathVariable @Phone String phone, @PathVariable String pngCode, @PathVariable String pngToken) throws BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken);
        CommonUtil.checkCode(hasCode, pngCode);
        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone);
        if (regCode != null) {
            throw new BaseException(RtnResultEnum.E010001);
        }
        String smsCode = RandomStringUtils.randomNumeric(6);
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone, smsCode, 60L);
        return new SuccFessionVO();
    }

    /**
     * 获取图片验证码
     */
    @RequestMapping(value = "getPngCode/{pngToken}", method = RequestMethod.GET)
    @ApiOperation("获取图片验证码")
    public void getPngCode(@PathVariable String pngToken, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken, verifyCode, 60L);
        int w = 200, h = 80;
        try {
            VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
