package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.bussiness;

/**
 * 账户信息
 * @author 葛文镇
 */
public class AccountInfoVO {
    //账户余额
    private String account_balance;
    //金融账户余额
    private String financial_account_balance;
    //信用分数
    private String credit_point;
    //信用额度
    private String credit_quota;
    //消费额度
    private String consume_quota;

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getFinancial_account_balance() {
        return financial_account_balance;
    }

    public void setFinancial_account_balance(String financial_account_balance) {
        this.financial_account_balance = financial_account_balance;
    }

    public String getCredit_point() {
        return credit_point;
    }

    public void setCredit_point(String credit_point) {
        this.credit_point = credit_point;
    }

    public String getCredit_quota() {
        return credit_quota;
    }

    public void setCredit_quota(String credit_quota) {
        this.credit_quota = credit_quota;
    }

    public String getConsume_quota() {
        return consume_quota;
    }

    public void setConsume_quota(String consume_quota) {
        this.consume_quota = consume_quota;
    }
}
