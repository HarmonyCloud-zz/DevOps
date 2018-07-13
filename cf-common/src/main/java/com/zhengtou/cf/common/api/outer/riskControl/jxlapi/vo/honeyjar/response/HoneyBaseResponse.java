package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.response;

/**
 * 聚信立蜜罐返回基类
 * @author 葛文镇
 */
public class HoneyBaseResponse {
    /**
     * 授权token，为调用获取数据的凭证
     */
    private String message;
    /**
     * 说明信息
     */
    private String code;
    /**
     * 接口调用状态,true为调用成功
     */
    private String spend_time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpend_time() {
        return spend_time;
    }

    public void setSpend_time(String spend_time) {
        this.spend_time = spend_time;
    }
}
