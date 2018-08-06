package com.harmony.shiro.service;

import com.harmony.shiro.pojo.vo.UserInfoVO;
import org.springframework.stereotype.Service;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述 用户服务
 */
public interface UserService {
    /**
     * 根据用户名和密码查找用户
     */
    UserInfoVO findUserByLoginNameAndPassWord(String loginName,String passWord);
}
