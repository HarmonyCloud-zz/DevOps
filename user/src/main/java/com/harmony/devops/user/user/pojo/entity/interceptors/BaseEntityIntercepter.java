package com.harmony.devops.user.user.pojo.entity.interceptors;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * 数据库拦截器(hibernate)
 * @author 葛文镇
 */
@Component("baseEntityIntercepter")
public class BaseEntityIntercepter extends EmptyInterceptor{
    @Override
    public void preFlush(Iterator entities) {
        while (entities.hasNext()) {
            Object entity = entities.next();
            if (entity instanceof BaseEntity) {
                ((BaseEntity) entity).setLastModifyTime(System.currentTimeMillis());
            }
        }
        super.preFlush(entities);
    }

    @Override
    public void postFlush(Iterator entities) {
        while (entities.hasNext()) {
            Object entity = entities.next();
        }
        super.postFlush(entities);
    }

}
