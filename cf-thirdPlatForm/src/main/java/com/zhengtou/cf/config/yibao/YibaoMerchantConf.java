package com.zhengtou.cf.config.yibao;

/**
 * 同盾接口配置项
 *
 * @author 葛文镇
 */
public class YibaoMerchantConf {
    //新投资通QA环境下的测试商户编号
    private static String merchantAccount = "10015799633";
    //商户私钥
    private static String merchantPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMgY6NxXhgrmR9/IJTAtYc1zDupTZ3X0qx4EWAtFG8GD/fN/DQq/r8ik1XFk6FAXTUf9j8OwIVsralXZSI+9PZ5SUP4ynZUpXShZj38xCCBf8JCTfHpC2uPTt7hHK0tG+I7YtIo+qc9/lE3AjqmwhantF0KMoBRO3jHCkH60Qwe7AgMBAAECgYEAqaK0h1iMAuGHPWhoXpeaupo4ot9ibtWl3CTCn4tPJJfJy4yCegJX/DoB2cg6Suv2UHneT4AaRzhfRbiXk4RTn0qf5iO0ukUhKZWqUOMee+K1D9sC5eFyYFdytm7sY7T4+ms+rIJ4ZZ9qRFWaMRP7Pez3op8UCwjWLzictTNSSIECQQD+8YQGJ7EvFrtMWYg+Fkhq7NwEfZNq731sew1v2z5w9ucWk9EPU/JtJ7o8359hgN6ccjJBmoP2TN/SZPO/KU0JAkEAyO00V5yRLd1gvtOD9E+7t04BYG1xpGL4EdpLgsb9FwttITlr4ctD/SfjBjcsmQ18EBNRIzfLtOYjF+NhAmjjowJAAc4T8Tc2ue6fRNsda9MckCU9KYr6EDfB25LDdZdHyeT1neNOB53AiaVOXD/5EOeoq9LjKDO/226P5x1v3FqegQJAEPRDCGmDrpURhsieR806j7sbTqGthyt6tzbuTDiyPnGOYdFQkxrtv7zP4URilP+YSlr267vXiooox+k91FA5dQJBAKvIHTRe9v8kDBdPp+PZ/gqPFQ3DgMcHemTd77RAgBSlaQbbczAlNt17qUPRvFtvDUaZoQ4Gd1MRv61MnafwWEM=";

    //交易请求地址
    private static String yeepayCommonReqURL = "https://cha.yeepay.com/app-merchant-proxy/transferController.action";
    private static String onlinePaymentReqURL = "https://cha.yeepay.com/app-merchant-proxy/groupTransferController.action";

    //查询和退款地址
    private static String queryRefundReqURL = "https://cha.yeepay.com/app-merchant-proxy/transferController.action";

    public static String getMerchantAccount() {
        return merchantAccount;
    }

    public static void setMerchantAccount(String merchantAccount) {
        YibaoMerchantConf.merchantAccount = merchantAccount;
    }

    public static String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public static void setMerchantPrivateKey(String merchantPrivateKey) {
        YibaoMerchantConf.merchantPrivateKey = merchantPrivateKey;
    }

    public static String getYeepayCommonReqURL() {
        return yeepayCommonReqURL;
    }

    public static void setYeepayCommonReqURL(String yeepayCommonReqURL) {
        YibaoMerchantConf.yeepayCommonReqURL = yeepayCommonReqURL;
    }

    public static String getOnlinePaymentReqURL() {
        return onlinePaymentReqURL;
    }

    public static void setOnlinePaymentReqURL(String onlinePaymentReqURL) {
        YibaoMerchantConf.onlinePaymentReqURL = onlinePaymentReqURL;
    }

    public static String getQueryRefundReqURL() {
        return queryRefundReqURL;
    }

    public static void setQueryRefundReqURL(String queryRefundReqURL) {
        YibaoMerchantConf.queryRefundReqURL = queryRefundReqURL;
    }
}

