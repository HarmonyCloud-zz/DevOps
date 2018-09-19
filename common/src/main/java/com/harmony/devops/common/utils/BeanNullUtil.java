package com.harmony.devops.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/21
 * @描述
 */
public class BeanNullUtil {
    public static String[] getNullPropertyNames (Object o) {
        final BeanWrapper src = new BeanWrapperImpl(o);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
