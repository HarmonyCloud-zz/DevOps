package com.zhengtou.cf.config.yibao;

/**
 * 同盾接口配置项
 *
 * @author 葛文镇
 */
public class YibaoConf {
    //新投资通QA环境下的测试商户编号
    private static String merchantAccount = "10015799633";
    //商户公钥
    private static String merchantPublicKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIGOjcV4YK5kffyCUwLWHNcw7qU2d19KseBFgLRRvBg/3zfw0Kv6/IpNVxZOhQF01H/Y/DsCFbK2pV2UiPvT2eUlD" +
                    "+Mp2VKV0oWY9/MQggX/CQk3x6Qtrj07e4RytLRviO2LSKPqnPf5RNwI6psIWp7RdCjKAUTt4xwpB+tEMHuwIDAQAB";
    //商户私钥
    private static String merchantPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMgY6NxXhgrmR9/IJTAtYc1zDupTZ3X0qx4EWAtFG8GD/fN/DQq/r8ik1XFk6FAXTUf9j8OwIVsralXZSI+9PZ5SUP4ynZUpXShZj38xCCBf8JCTfHpC2uPTt7hHK0tG+I7YtIo+qc9/lE3AjqmwhantF0KMoBRO3jHCkH60Qwe7AgMBAAECgYEAqaK0h1iMAuGHPWhoXpeaupo4ot9ibtWl3CTCn4tPJJfJy4yCegJX/DoB2cg6Suv2UHneT4AaRzhfRbiXk4RTn0qf5iO0ukUhKZWqUOMee+K1D9sC5eFyYFdytm7sY7T4+ms+rIJ4ZZ9qRFWaMRP7Pez3op8UCwjWLzictTNSSIECQQD+8YQGJ7EvFrtMWYg+Fkhq7NwEfZNq731sew1v2z5w9ucWk9EPU/JtJ7o8359hgN6ccjJBmoP2TN/SZPO/KU0JAkEAyO00V5yRLd1gvtOD9E+7t04BYG1xpGL4EdpLgsb9FwttITlr4ctD/SfjBjcsmQ18EBNRIzfLtOYjF+NhAmjjowJAAc4T8Tc2ue6fRNsda9MckCU9KYr6EDfB25LDdZdHyeT1neNOB53AiaVOXD/5EOeoq9LjKDO/226P5x1v3FqegQJAEPRDCGmDrpURhsieR806j7sbTqGthyt6tzbuTDiyPnGOYdFQkxrtv7zP4URilP+YSlr267vXiooox+k91FA5dQJBAKvIHTRe9v8kDBdPp+PZ/gqPFQ3DgMcHemTd77RAgBSlaQbbczAlNt17qUPRvFtvDUaZoQ4Gd1MRv61MnafwWEM=";
    //易宝公玥
    private static String yeepayPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAHW2D7cMXmTgmpgJ/BviInggaRIDymzDW6+vAs7wVEekCXl" +
            "+bq8hOPKcNa9ryUZmd72XL38rEWyCctobBObLs7LpBhZ3uqSFZjRgihaDEUBcNPClDN/H0PaSTSQ+sdnl3DIfMnXYL+KsNz5ap36kj15I95PvZeLBz70evutfRfwIDAQAB";
    /******以下为QA环境测试接口*****/

    //有短验绑卡请求接口
    private static String bindCardRequestURL = "https://jinrong.yeepay.com/tzt-api/api/bindcard/request";

    //有短验绑卡请求确认接口
    private static String bindCardConfirmURL = "https://jinrong.yeepay.com/tzt-api/api/bindcard/confirm";

    //有短验绑卡请求重发短验接口
    private static String bindCardResendsmsURL = "https://jinrong.yeepay.com/tzt-api/api/bindcard/resendsms";

    //强化鉴权打款请求接口
    private static String remitRequestURL = "https://jinrong.yeepay.com/tzt-api/api/remit/request";

    //强化鉴权打款确认接口
    private static String remitConfirmURL = "https://jinrong.yeepay.com/tzt-api/api/remit/confirm";

    //有短验充值请求接口
    private static String bindPayRequestURL = "https://jinrong.yeepay.com/tzt-api/api/bindpay/request";

    //有短验充值短验确认接口
    private static String bindPayConfirmURL = "https://jinrong.yeepay.com/tzt-api/api/bindpay/confirm";

    //有短验充值请求重发短验接口
    private static String bindPayResendsmsURL = "https://jinrong.yeepay.com/tzt-api/api/bindpay/resendsms";

    //无短验充值请求接口
    private static String unSendBindPayRequestURL = "https://jinrong.yeepay.com/tzt-api/api/bindpay/direct";

    //首次支付请求接口
    private static String firstPayRequestURL = "https://jinrong.yeepay.com/tzt-api/api/firstpay/request";

    //首次支付请求确认接口
    private static String firstPayConfirmURL = "https://jinrong.yeepay.com/tzt-api/api/firstpay/confirm";

    //首次支付重发短验接口
    private static String firstPayResendsmsmURL = "https://jinrong.yeepay.com/tzt-api/api/firstpay/resendsms";

    //换卡请求接口
    private static String changeCardRequestURL = "https://jinrong.yeepay.com/tzt-api/api/changecard/request";

    //换卡请求确认接口
    private static String changeCardConfirmURL = "https://jinrong.yeepay.com/tzt-api/api/changecard/confirm";

    //换卡重发短验接口
    private static String changeCardResendsmsmURL = "https://jinrong.yeepay.com/tzt-api/api/changecard/resendsms";

    //绑卡充值记录查询接口请求地址
    private static String bindPayRecordURL = "https://jinrong.yeepay.com/tzt-api/api/bindpay/record";

    //换卡记录查询接口请求地址
    private static String changeCardURL = "https://jinrong.yeepay.com/tzt-api/api/changecard/record";

    //可提现余额查询接口请求地址
    private static String drawValidAmountURL = "https://jinrong.yeepay.com/tzt-api/api/withdraw/validamount";

    //提现记录查询接口请求地址
    private static String queryDrawRecordURL = "https://jinrong.yeepay.com/tzt-api/api/withdraw/record";

    //绑卡列表查询接口请求地址
    private static String queryBindCardListURL = "https://jinrong.yeepay.com/tzt-api/api/bindcard/list";

    //绑卡记录查询接口请求地址
    private static String queryBindCardRecordURL = "https://jinrong.yeepay.com/tzt-api/api/bindcard/record";

    //银行卡信息查询接口请求地址
    private static String bankCardCheckURL = "https://jinrong.yeepay.com/tzt-api/api/bankcard/check";

    //首次充值记录查询接口请求地址
    private static String firstpayrecordURL = "https://jinrong.yeepay.com/tzt-api/api/firstpay/record";

    //提现接口请求地址
    private static String withdrawURL = "https://jinrong.yeepay.com/tzt-api/api/withdraw/request";

    //鉴权绑卡对账
    private static String accountCheckAuthURL = "https://jinrong.yeepay.com/tzt-api/api/accountcheck/auth";

    //支付对账
    private static String accountCheckPaymentURL = "https://jinrong.yeepay.com/tzt-api/api/accountcheck/auth";

    //提现对账
    private static String accountCheckWithDrawURL = "https://jinrong.yeepay.com/tzt-api/api/accountcheck/withdraw";

    //换卡对账
    private static String accountCheckChangeCardURL = "https://jinrong.yeepay.com/tzt-api/api/accountcheck/changcard";

    //退款对账
    private static String accountCheckRefundURL = "https://jinrong.yeepay.com/tzt-api/api/accountcheck/refund";

    //退款接口
    private static String refundURL = "https://jinrong.yeepay.com/tzt-api/api/refund/request";

    //退款记录查询接口
    private static String refundRecordURL = "https://jinrong.yeepay.com/tzt-api/api/refund/record";

    //解绑卡
    private static String unbindRequestURL = "https://jinrong.yeepay.com/tzt-api/api/unbind/request";

    public static String getMerchantAccount() {
        return merchantAccount;
    }

    public static String getMerchantPublicKey() {
        return merchantPublicKey;
    }

    public static String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public static String getYeepayPublicKey() {
        return yeepayPublicKey;
    }

    public static String getBindCardRequestURL() {
        return bindCardRequestURL;
    }

    public static String getBindCardConfirmURL() {
        return bindCardConfirmURL;
    }

    public static String getBindCardResendsmsURL() {
        return bindCardResendsmsURL;
    }

    public static String getRemitRequestURL() {
        return remitRequestURL;
    }

    public static String getRemitConfirmURL() {
        return remitConfirmURL;
    }

    public static String getBindPayRequestURL() {
        return bindPayRequestURL;
    }

    public static String getBindPayConfirmURL() {
        return bindPayConfirmURL;
    }

    public static String getBindPayResendsmsURL() {
        return bindPayResendsmsURL;
    }

    public static String getUnSendBindPayRequestURL() {
        return unSendBindPayRequestURL;
    }

    public static String getFirstPayRequestURL() {
        return firstPayRequestURL;
    }

    public static String getFirstPayConfirmURL() {
        return firstPayConfirmURL;
    }

    public static String getFirstPayResendsmsmURL() {
        return firstPayResendsmsmURL;
    }

    public static String getChangeCardRequestURL() {
        return changeCardRequestURL;
    }

    public static String getChangeCardConfirmURL() {
        return changeCardConfirmURL;
    }

    public static String getChangeCardResendsmsmURL() {
        return changeCardResendsmsmURL;
    }

    public static String getBindPayRecordURL() {
        return bindPayRecordURL;
    }

    public static String getChangeCardURL() {
        return changeCardURL;
    }

    public static String getDrawValidAmountURL() {
        return drawValidAmountURL;
    }

    public static String getQueryDrawRecordURL() {
        return queryDrawRecordURL;
    }

    public static String getQueryBindCardListURL() {
        return queryBindCardListURL;
    }

    public static String getQueryBindCardRecordURL() {
        return queryBindCardRecordURL;
    }

    public static String getBankCardCheckURL() {
        return bankCardCheckURL;
    }

    public static String getFirstpayrecordURL() {
        return firstpayrecordURL;
    }

    public static String getWithdrawURL() {
        return withdrawURL;
    }

    public static String getAccountCheckAuthURL() {
        return accountCheckAuthURL;
    }

    public static String getAccountCheckPaymentURL() {
        return accountCheckPaymentURL;
    }

    public static String getAccountCheckWithDrawURL() {
        return accountCheckWithDrawURL;
    }

    public static String getAccountCheckChangeCardURL() {
        return accountCheckChangeCardURL;
    }

    public static String getAccountCheckRefundURL() {
        return accountCheckRefundURL;
    }

    public static String getRefundURL() {
        return refundURL;
    }

    public static String getRefundRecordURL() {
        return refundRecordURL;
    }

    public static String getUnbindRequestURL() {
        return unbindRequestURL;
    }
}

