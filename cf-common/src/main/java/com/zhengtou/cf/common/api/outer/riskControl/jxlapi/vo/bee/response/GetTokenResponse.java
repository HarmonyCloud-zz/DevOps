package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

/**
 * 拿到token返回参数封装
 * @author 葛文镇
 */
public class GetTokenResponse {
    /**
     * 授权token，为调用获取数据的凭证
     */
    private String access_token;
    /**
     * 说明信息
     */
    private String note;
    /**
     * 接口调用状态,true为调用成功
     */
    private String success;
    /**
     * token类型,过期时间
     */
    private String expires_in;
    /**
     * 更新时间
     */
    private String update_time;
    /**
     * 创建时间
     */
    private String create_time;
    /**
     * 过期时间
     */
    private String expire_time;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }
}
