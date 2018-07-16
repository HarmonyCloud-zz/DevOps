package com.harmony.devops.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

public class DBUtil {
    private static final String PASSWORD_KEY = "123456";
    private static final String PASSWORD_INIT = "ztw123";

    /**
     * 生成UUID
     *
     */
    public static String generateID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 对密码进行MD5加密
     */
    public static String generatePassword(String password) {
        return DigestUtils.md5Hex(password + PASSWORD_KEY);
    }

    /**
     * 创建初始密码
     */
    public static String generatePassword() {
        return generatePassword(PASSWORD_INIT);
    }

    public static String formatBoolean(Boolean input) {
        if (input == null) {
            return "未知";
        }
        if (input) {
            return "是";
        } else {
            return "否";
        }
    }

    private static final String DEFAULT_LIKE_FIX = "%";

    public static String generateLikeSql(String input) {
        return generateLikeSql(input, DEFAULT_LIKE_FIX);
    }

    public static String generateLikeSql(String input, String prefix) {
        return generateLikeSql(input, prefix, DEFAULT_LIKE_FIX);
    }

    public static String generateLikeSql(String input, String prefix, String surfix) {
        String trimInput = StringUtils.trimToEmpty(input);
        String dealWithSpace = trimInput.replaceAll("[ ]+", " ");
        String[] searchList = { " ", "_", "[", "]", "%" };
        String[] replacementList = { "%", "\\_", "\\[", "\\]", "\\%" };
        String output = StringUtils.replaceEach(dealWithSpace, searchList, replacementList);
        return prefix + output + surfix;
    }

}
