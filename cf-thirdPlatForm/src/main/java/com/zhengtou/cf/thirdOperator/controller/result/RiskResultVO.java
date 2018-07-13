package com.zhengtou.cf.thirdOperator.controller.result;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class RiskResultVO extends PeakBaseVo {
    public RiskResultVO(String final_decision, String desc) {
        this.final_decision = final_decision;
        this.desc = desc;
    }

    private String final_decision;
    private String desc;

    public String getFinal_decision() {
        return final_decision;
    }

    public void setFinal_decision(String final_decision) {
        this.final_decision = final_decision;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
