package com.harmony.devops.common.api;

import com.alibaba.fastjson.JSON;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.utils.ParseUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/28
 * @描述 与外部接口交互response封装
 */
public abstract class BaseResponse {
    public Map<String, Object> parseMap() throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Field param : this.getClass().getFields()) {
            String paramName = param.getName();
            try {
                map.put(paramName, param.get(this));
            } catch (IllegalArgumentException e) {
                throw new BaseException("","");
            } catch (IllegalAccessException e) {
                throw new BaseException("","");
            }
        }
        return map;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public void parseXML(String bestResponse) throws BaseException {
        Map<String, Object> map = ParseUtil.getMapFromXML(bestResponse);
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    if (map.containsKey(property.getName())) {
                        if (map.containsKey(property.getName())) {
                            setter.invoke(this, map.get(property.getName()));
                        }
                    }
                }
            }
        } catch (IntrospectionException e) {
            throw new BaseException("IntrospectionException", e.getMessage());
        } catch (IllegalAccessException e) {
            throw new BaseException("IllegalAccessException", e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BaseException("IllegalArgumentException",
                    e.getMessage());
        } catch (InvocationTargetException e) {
            throw new BaseException("InvocationTargetException",
                    e.getMessage());
        }
    }

    public void parseXML(Map<String, String[]> map) throws BaseException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    if (map.containsKey(property.getName())) {
                        if (map.containsKey(property.getName())) {
                            setter.invoke(this, map.get(property.getName())[0]);
                        }
                    }
                }
            }
        } catch (IntrospectionException e) {
            throw new BaseException("IntrospectionException", e.getMessage());
        } catch (IllegalAccessException e) {
            throw new BaseException("IllegalAccessException", e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BaseException("IllegalArgumentException",
                    e.getMessage());
        } catch (InvocationTargetException e) {
            throw new BaseException("InvocationTargetException",
                    e.getMessage());
        }
    }

    public void parseJSON(String bestResponse) throws BaseException {
        Map<String, Object> map;
        try {
            map = JSON.parseObject(bestResponse, this.getClass()).parseMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(this, map.get(property.getName()));
                }
            }
        } catch (IntrospectionException e) {
            throw new BaseException("IntrospectionException", e.getMessage());
        } catch (InvocationTargetException e) {
            throw new BaseException("InvocationTargetException",
                    e.getMessage());
        } catch (IllegalAccessException e) {
            throw new BaseException("IllegalAccessException", e.getMessage());
        }
    }
}
