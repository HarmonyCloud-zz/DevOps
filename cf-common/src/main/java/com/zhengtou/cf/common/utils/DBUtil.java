package com.zhengtou.cf.common.utils;

import com.zhengtou.cf.common.enums.AnnexTypeEnum;
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

    /****************************************************业务方法******************************************************/
    /**
     * 生成订单编号
     */
    public static String getTradeNo(){
        return "ZT"+ TimeUtil.timeToStringMS()+RandomStringUtils.randomNumeric(4);
    }
    /**
     * 生成客户编号
     */
    public static String getUserNo(){
        return "ZTU"+TimeUtil.timeToStringMS()+RandomStringUtils.randomNumeric(3);
    }
    /**
     * 生成合同编号
     */
    public static String getContractNo(){
        return "CN"+ TimeUtil.timeToStringMS()+RandomStringUtils.randomNumeric(4);
    }
    /**
     * 生成借据编号
     */
    public static String getBillNo(){
        return "BIll"+TimeUtil.timeToStringMS()+ RandomStringUtils.randomNumeric(4);
    }
    /**
     * 生成商品订单编号
     */
    public static String getOrderNo(){
        return "ZO"+ TimeUtil.timeToStringMS()+RandomStringUtils.randomNumeric(4);
    }
    /**
     * 生成交易流水编号
     */
    public static String getTransacNo(){
        return "ZTT"+ TimeUtil.timeToStringMS()+RandomStringUtils.randomNumeric(4);
    }
    /**
     * 生成绑卡订单编号
     */
    public static String getBindCardNo(){
        return "ZTB"+TimeUtil.timeToStringMS()+ RandomStringUtils.randomNumeric(4);
    }
    /**
     * 图片唯一标识码
     */
    public static String getAnnexCode(String userId, AnnexTypeEnum annexTypeEnum){
        return userId+"_"+annexTypeEnum.getCode()+"_"+RandomStringUtils.randomNumeric(6);
    }
    /**
     * 商户编号
     */
    public static String getOrgNo(){
        return "ZTO" + TimeUtil.timeToStringDD()+RandomStringUtils.randomNumeric(4);
    }
    /**
     * 门店编号
     */
    public static String getStoreNo(){
        return "ZTS" + TimeUtil.timeToStringDD()+RandomStringUtils.randomNumeric(4);
    }
}
