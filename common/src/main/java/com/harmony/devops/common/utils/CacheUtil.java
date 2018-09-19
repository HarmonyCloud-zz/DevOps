package com.harmony.devops.common.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/21
 * @描述
 */
public class CacheUtil {
    private static final int KEY_LENGTH = 8;
    private static Map<String, Cache> cacheMap = new HashMap<String, Cache>();

    public static String set(String key,Object value, long expireTime) {
        synchronized (cacheMap) {
            cacheMap.put(key, new Cache(value, expireTime));
            return key;
        }
    }

    public static String set(String key,Object value, int expire, TimeUnit unit) {
        return set(key,value, unit.toMillis(expire));
    }

    public static String set(String key,Object value) {
        return set(key,value, TimeUnit.HOURS.toMillis(2));
    }

    public static Object get(String key) {
        synchronized (cacheMap) {
            return containsKey(key) ? cacheMap.get(key).content : null;
        }
    }

    private static boolean containsKey(String key) {
        Cache cache = cacheMap.get(key);
        if (cache != null && !cache.isOutOfDate()) {
            return true;
        } else {
            cacheMap.remove(key);
            return false;
        }
    }

    private static String generateKey() {
        String key = RandomStringUtils.randomNumeric(KEY_LENGTH);
        while (containsKey(key)) {
            key = RandomStringUtils.randomNumeric(KEY_LENGTH);
        }
        return key;
    }

    private static class Cache {
        private Object content;
        private long expireTime;

        public Cache(Object content, long expireTime) {
            this.content = content;
            this.expireTime = System.currentTimeMillis() + expireTime;
        }

        public boolean isOutOfDate() {
            return System.currentTimeMillis() > expireTime;
        }
    }
}
