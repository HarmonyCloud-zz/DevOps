package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class YiBaoBaseResponse extends PeakBaseVo {

    private static final Logger logger = LoggerFactory.getLogger(YiBaoBaseResponse.class);

    public void parseMap(Map<String, String> map) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    if (map.containsKey(property.getName())) {
                        setter.invoke(this, map.get(property.getName()));
                    }
                }
            }
        } catch (IntrospectionException e) {
            logger.info("IntrospectionException:", e.getMessage());
        } catch (IllegalAccessException e) {
            logger.info("IllegalAccessException:", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.info("IllegalArgumentException:",
                    e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            logger.info("InvocationTargetException:",
                    e.getMessage());
            e.printStackTrace();
        }
    }

}
