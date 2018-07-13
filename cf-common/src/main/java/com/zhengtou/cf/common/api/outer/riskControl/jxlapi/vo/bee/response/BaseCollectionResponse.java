package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

/**
 * 数据采集响应基类
 * @author 葛文镇
 */
public class BaseCollectionResponse {
    private Boolean success;
    private String message;
    private String code;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

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
}
