package com.harmony.devops.common.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MyRedisComponent {
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 秒
     */
    public void setSeconds(String key, Object value, Long alive) {
        redisTemplate.opsForValue().set(key, value, alive, TimeUnit.SECONDS);
    }

    /**
     * 小时
     */
    public void setHours(String key, Object value, Long alive) {
        redisTemplate.opsForValue().set(key, value, alive, TimeUnit.HOURS);
    }

    /**
     * 天
     */
    public void setDay(String key, Object value, Long alive) {
        redisTemplate.opsForValue().set(key, value, alive, TimeUnit.DAYS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        this.redisTemplate.delete(key);
    }

    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
