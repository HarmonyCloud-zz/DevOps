package com.harmony.devops.utils;

import com.harmony.devops.common.utils.DBUtil;
import com.harmony.devops.context.config.BaseConf;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/4
 * @描述
 */
public class UserUtil {
    @Autowired
    static BaseConf baseConf;

    /**
     * 用户默认密码
     */
    public static String genertePassword(){
        return DBUtil.generatePassword(baseConf.password_init,baseConf.password_key);
    }

    /**
     * 密码加密
     */
    public static String genertePassword(String password){
        return DBUtil.generatePassword(password,baseConf.password_key);
    }
}
