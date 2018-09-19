package com.harmony.devops.context;

import com.harmony.devops.common.component.redis.MyRedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目初始化数据准备
 * @author 葛文镇
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    MyRedisComponent myRedisComponent;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            /**
             * 初始化超级管理员
             */
//            User userEntity=dao.get("select u from User u where u.isDeleted=false ",new Object[]{});
//            if(userEntity==null){
//                userEntity=new UserEntity();
//                userEntity.userName="admin";
//                userEntity.passWord= UserUtil.genertePassword();
//                dao.save(userEntity);
//            }
            //初始化超级管理员权限

        }
    }
}
