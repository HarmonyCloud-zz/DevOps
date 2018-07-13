package com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request;

/**
 * 批次明细查询
 * @author 葛文镇
 */
public class QueryPayRequest {
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
    public String pageNo;
    //签名字段
    public String hmac;
}
