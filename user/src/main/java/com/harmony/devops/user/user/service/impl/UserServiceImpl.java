package com.harmony.devops.user.user.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BaseDao dao;

    @Override
    public UserEntity getUserByPhone(String phone) {
        return dao.get("select u from UserEntity u where u.isDeleted=false and u.phone=?0", new Object[]{phone});
    }

}
