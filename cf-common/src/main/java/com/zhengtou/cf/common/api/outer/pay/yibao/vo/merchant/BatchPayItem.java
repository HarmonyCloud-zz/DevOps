package com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant;

/**
 * 批量打款数据项
 * @author 葛文镇
 */
public class BatchPayItem {
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
    //备注
    public String remarksInfo;
    public BatchPayItem( String bankCode, String orderId, String cnaps,
                            String bankName, String branchBankName, double amount, String accountName, String accountNumber, String feeType) {
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
