package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

/**
 * 聚信立蜜蜂基础数据
 * @author 葛文镇
 */
public class BaseResponse {
    private String status;
    private String update_time;
    private String error_code;
    private String error_msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

}
