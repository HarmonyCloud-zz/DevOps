package com.harmony.devops.utils;

import com.harmony.devops.common.utils.TimeUtil;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/31
 * @描述
 */
public class ResourcesUtil {
    /**
     * 角色编码
     */
    public static String getRoleCode(){
        return "RC"+ TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMddHHmmss")+ RandomStringUtils.randomNumeric(3);
    }


    /**
     * 角色编码
     */
    public static String getResourcesCode(){
        return "RC"+ TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMddHHmmss")+ RandomStringUtils.randomNumeric(3);
    }
}
