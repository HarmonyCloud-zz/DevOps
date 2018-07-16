package com.harmony.devops.user.user.service;

import com.harmony.devops.user.user.pojo.entity.UserEntity;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 根据手机号获取用户
     */
    UserEntity getUserByPhone(String phone);

}
