package com.harmony.devops.controller.login;

import com.harmony.devops.common.component.redis.MyRedisComponent;
import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import com.harmony.devops.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@Api(description = "登陆服务")
public class LoginController {
    @Autowired
    MyRedisComponent myRedisComponent;

    @ApiOperation("未登陆")
    @RequestMapping(value = "error/nologin",method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO noLogin() throws BaseException {
        throw new BaseException(RtnResultEnum.E010001);
    }

    @ApiOperation("无权限")
    @RequestMapping(value = "error/unauthorized",method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO unauthorized() throws BaseException {
        throw new BaseException(RtnResultEnum.E010002);
    }

    @ApiOperation("登陆")
    @RequestMapping(value = "login", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO login(@RequestParam @ApiParam(value = "用户名", name = "userName") String userName,
                       @RequestParam @ApiParam(value = "密码",name = "passWord") String passWord,
                       @RequestParam(defaultValue = "false") @ApiParam(value = "记住",name = "rememberMe") boolean rememberMe)
            throws BaseException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, UserUtil.genertePassword(passWord));
        token.setRememberMe(rememberMe);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            throw new BaseException(RtnResultEnum.E010000);
        }
        return new SuccFessionVO();
    }
}
