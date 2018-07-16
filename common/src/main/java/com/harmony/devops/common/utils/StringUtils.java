package com.harmony.devops.common.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Map;

/**
 * 字符串处理类
 * @author 葛文镇
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{

    public static String mapToString(Map<String, String> args) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : args.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 生成流水号
     *
     * @return
     */
    public static String getExtSerialNum() {
        String code = "ZT" + System.currentTimeMillis() + RandomStringUtils.randomNumeric(4);
        return code;
    }

    /**
     * 生成客户编号
     *
     * @return
     */
    public static String getCustomerNum() {
        String code = "CN" + System.currentTimeMillis() + RandomStringUtils.randomNumeric(4);
        return code;
    }
}
