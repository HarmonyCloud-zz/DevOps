package com.zhengtou.cf.trade.pojo.entity.trade.enums;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 订单状态
 */
public enum TradeStatusEnum {
    //审批
    正在处理,通过,拒绝,放弃,
    //合同
    打款中,打款成功,打款失败,商户结算,
    //TODO
    驳回待补件;
}
