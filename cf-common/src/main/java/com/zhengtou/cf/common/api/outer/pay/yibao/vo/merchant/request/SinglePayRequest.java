package com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request;

/**
 * 单笔代付代收
 *
 * @author 葛文镇
 */
public class SinglePayRequest {
    //总公司商户编号
    public String merchantAccount;
    //实际发生付款交易商户号（可以为子公司）
    public String merchantAccountChild;
    //打款批次号
    public String batchNo;
    //收款银行编号（易宝附录）
    public String bankCode;
    //订单号
    public String orderId;
    //联行号
    public String cnaps;
    //收款银行全称
    public String bankName;
    //收款银行支行名称
    public String branchBankName;
    //打款金额
    public double amount;
    //账户名称
    public String accountName;
    //账户号
    public String accountNumber;
    //账户类型（pu:对公；pr:对私）
    public String accountType;
    //收款行省份编码
    public String province;
    //收款行城市编码
    public String city;
    //手续费收取方式
    public String feeType;
    //签名字段
    public String hmac;

    public SinglePayRequest(String merchantAccount, String merchantAccountChild, String batchNo, String bankCode, String orderId, String cnaps,
                            String bankName, String branchBankName, double amount, String accountName, String accountNumber, String feeType) {
        this.merchantAccount = merchantAccount;
        this.merchantAccountChild = merchantAccountChild;
        this.batchNo = batchNo;
        this.bankCode = bankCode;
        this.orderId = orderId;
        this.cnaps = cnaps;
        this.bankName = bankName;
        this.branchBankName = branchBankName;
        this.amount = amount;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.feeType = feeType;
    }
}
