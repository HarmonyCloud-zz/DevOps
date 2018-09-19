package com.harmony.devops.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.UUID;

public class DBUtil {

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
    public static String generatePassword(String password,String passwordKey) {
        return DigestUtils.md5Hex(password + passwordKey);
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

    /**
     * map转String
     */
    public static String mapToString(Map<String, Object> args) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue())
                    .append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
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

    /*****************************************业务类*******************************************/
    /**
     * 用户编号
     */
    public static String getUserNo(){
        return "U"+TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMdd")+ RandomStringUtils.randomNumeric(6);
    }

    /**
     * 机构编号
     */
    public static String getOrgNo(){
        return "O"+TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMdd")+ RandomStringUtils.randomNumeric(6);
    }

    /**
     * 订单编号
     */
    public static String getOrderNo(String type){
        return "O"+TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMddHHmmss")+ RandomStringUtils.randomNumeric(6);
    }

    /**
     * 附件编号
     */
    public static String getAnnexNo(String annexType){
        return "A"+annexType+TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMdd")+RandomStringUtils.randomNumeric(4);
    }

    /**
     * 商品编号
     */
    public static String getGoodNo(String categoryCode){
        return "G"+categoryCode+TimeUtil.timeToString(System.currentTimeMillis(),"yyyyMMdd")+RandomStringUtils.randomNumeric(4);
    }

}
