package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

import java.util.List;

/**
 * 短信箱单
 *
 * @author 葛文镇
 */
public class SmsInfoVO {
    //费用合计
    private String total_msg_cost;
    //总短信次数
    private String total_msg_count;
    //短信周期
    private String msg_cycle;
    //短信记录
    private List<SmsRecordVO> sms_record;


    public String getTotal_msg_cost() {
        return total_msg_cost;
    }

    public void setTotal_msg_cost(String total_msg_cost) {
        this.total_msg_cost = total_msg_cost;
    }

    public String getTotal_msg_count() {
        return total_msg_count;
    }

    public void setTotal_msg_count(String total_msg_count) {
        this.total_msg_count = total_msg_count;
    }

    public String getMsg_cycle() {
        return msg_cycle;
    }

    public void setMsg_cycle(String msg_cycle) {
        this.msg_cycle = msg_cycle;
    }

    public List<SmsRecordVO> getSms_record() {
        return sms_record;
    }

    public void setSms_record(List<SmsRecordVO> sms_record) {
        this.sms_record = sms_record;
    }
}
