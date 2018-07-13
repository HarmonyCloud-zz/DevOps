package com.zhengtou.cf.user.service;

import com.zhengtou.cf.user.pojo.entity.UserEntity;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 根据手机号获取用户
     */
    UserEntity getUserByPhone(String phone);

}
