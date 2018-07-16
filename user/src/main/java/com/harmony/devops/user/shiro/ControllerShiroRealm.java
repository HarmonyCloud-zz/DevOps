package com.harmony.devops.user.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class ControllerShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ControllerShiroRealm.class);
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("##################执行Shiro权限认证##################");
        // 获取用户名
        String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
        // 判断用户名是否存在
        if (StringUtils.isEmpty(loginName)) {
            return null;
        }
        // 查询登录用户信息
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("##################执行Shiro登陆认证##################");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名
        String loginName = token.getUsername();
        if (loginName != null && !"".equals(loginName)) {
            // 模拟数据库查询用户信息
        }
        return null;
    }
}
