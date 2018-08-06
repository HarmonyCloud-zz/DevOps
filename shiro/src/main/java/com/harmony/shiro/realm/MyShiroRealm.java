package com.harmony.shiro.realm;

import com.harmony.shiro.pojo.vo.RoleInfoVO;
import com.harmony.shiro.pojo.vo.UserInfoVO;
import com.harmony.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfoVO userInfoVO = (UserInfoVO) principals.getPrimaryPrincipal();
        for (RoleInfoVO role : userInfoVO.roleInfoVOList) {
            authorizationInfo.addRole(role.roleCode);
            for (String p : role.permissionList) {
                authorizationInfo.addStringPermission(p);
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfoVO userInfo = userService.findUserByLoginNameAndPassWord(username,"111");
//        System.out.println("----->>userInfo="+userInfo);
        if (userInfo == null) {
            return null;
        }
        if (userInfo==null) { //账户冻结
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.realName, //用户名
                "222", //密码
                ByteSource.Util.bytes("222333"),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
