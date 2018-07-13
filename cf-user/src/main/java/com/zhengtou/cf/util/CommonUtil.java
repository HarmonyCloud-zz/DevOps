package com.zhengtou.cf.util;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 工具包（签名）
 */
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static String generateSign(String signKey, Object obj) {
        Class<? extends Object> clazz = obj.getClass();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        Collections.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        StringBuffer sb = new StringBuffer();
        for (Field field : fields) {
            try {
                Object value = field.get(obj);
                if (value != null) {
                    sb.append(value.toString());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.info(e.getMessage());
            }
        }
        sb.append(signKey);
        logger.info(sb.toString());
        return DigestUtils.md5Hex(sb.toString());
    }

    public static String generateSign(String signKey, Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });
        StringBuffer sb = new StringBuffer();
        for (String key : keys) {
            Object value = params.get(key);
            if (value != null) {
                sb.append(value);
            }
        }
        sb.append(signKey);
        return DigestUtils.md5Hex(sb.toString());
    }

    /**
     * 检验验证码
     */
    public static void checkCode(String hasCode, String code) throws BaseException {
        if (hasCode == null){
            throw new BaseException(RtnResultEnum.E010002);
        }
        if (!hasCode.equalsIgnoreCase(code)){
            throw new BaseException(RtnResultEnum.E010000);
        }
    }
}