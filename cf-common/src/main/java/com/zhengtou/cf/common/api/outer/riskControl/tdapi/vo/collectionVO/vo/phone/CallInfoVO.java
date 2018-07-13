package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

import java.util.List;

/**
 * 通话详单
 * @author 葛文镇
 */
public class CallInfoVO {
    //总统话时长
    private String total_call_time;
    //总统话次数
    private String total_call_count;
    //通话周期
    private String call_cycle;
    //通话记录
    private List<CallRecordVO> call_record;


    public String getTotal_call_time() {
        return total_call_time;
    }

    public void setTotal_call_time(String total_call_time) {
        this.total_call_time = total_call_time;
    }

    public String getTotal_call_count() {
        return total_call_count;
    }

    public void setTotal_call_count(String total_call_count) {
        this.total_call_count = total_call_count;
    }

    public String getCall_cycle() {
        return call_cycle;
    }

    public void setCall_cycle(String call_cycle) {
        this.call_cycle = call_cycle;
    }

    public List<CallRecordVO> getCall_record() {
        return call_record;
    }

    public void setCall_record(List<CallRecordVO> call_record) {
        this.call_record = call_record;
    }
}
