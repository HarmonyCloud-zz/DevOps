package com.harmony.devops.shiro;

import com.harmony.devops.common.enums.RtnStatusEnum;
import com.harmony.devops.common.exception.BaseRunTimeException;
import com.harmony.devops.domain.user.User;
import com.harmony.devops.repository.role.RoleRepository;
import com.harmony.devops.repository.user.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
public class MyShiroRealm extends AuthorizingRealm{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Object o = principals.getPrimaryPrincipal();
        User user=new User();
        BeanUtils.copyProperties(o,user);
        user.getStringRoles().forEach(stringRole->authorizationInfo.addRole(stringRole));
        user.getStringResourceCode().forEach(stringResourceCode->authorizationInfo.addStringPermission(stringResourceCode));
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws BaseRunTimeException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userRepository.getUserByUserName(username);
        if(user==null) {
            throw new BaseRunTimeException(RtnStatusEnum.FAIL,"UnknownUserException", "未知用户异常");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassWord(), //密码
                ByteSource.Util.bytes(user.getUserName()),//salt=username+salt
                getName()  //devops name
        );
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getUserNo());
        return authenticationInfo;
    }

    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     * @param userNo 已经修改了权限的userNo
     */
    public void clearUserAuthByUserId(String userNo){
        if(org.apache.commons.lang.StringUtils.isBlank(userNo)){
            return;
        }
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User.Vo){
                    User.Vo userVo = (User.Vo) obj;
                    System.out.println("user:"+userVo);
                    //比较用户ID，符合即加入集合
                    if(null != userVo && userNo.contains(userVo.getUserNo())){
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }
}
