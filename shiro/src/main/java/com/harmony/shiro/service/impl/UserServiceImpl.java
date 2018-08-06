package com.harmony.shiro.service.impl;

import com.harmony.shiro.pojo.vo.RoleInfoVO;
import com.harmony.shiro.pojo.vo.UserInfoVO;
import com.harmony.shiro.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public UserInfoVO findUserByLoginNameAndPassWord(String loginName, String passWord) {
        List<RoleInfoVO> roleInfoVOList=new ArrayList<>();
        List<String> promissionList=new ArrayList<>();
        promissionList.add("p111");
        roleInfoVOList.add(new RoleInfoVO("111",promissionList));
        return new UserInfoVO("ceshi",roleInfoVOList);
    }
}
