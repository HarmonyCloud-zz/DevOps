package com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.request;

import com.zhengtou.cf.common.api.outer.pay.yibao.vo.merchant.BatchPayItem;

import java.util.List;

/**
 * 批量打款请求
 *
 * @author 葛文镇
 */
public class BatchPayRequest {
    //总公司商户编号
    public String merchantAccount;
    //实际发生付款交易商户号（可以为子公司）
    public String merchantAccountChild;
    //打款批次号
    public String batchNo;
    public String totalNum;
    public String totalAmt;
    public List<BatchPayItem> items;
    public String hmac;
}
