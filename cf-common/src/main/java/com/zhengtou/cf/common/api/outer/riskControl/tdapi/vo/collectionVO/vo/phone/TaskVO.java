package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 任务信息
 * @author 葛文镇
 */
public class TaskVO {
    //渠道类型
    private String channel_type;
    //渠道编码
    private String channel_code;
    //真实姓名
    private String real_name;
    //身份证号码
    private String identity_code;
    //手机号码
    private String user_mobile;
    //创建时间
    private String created_time;
    //用户名
    private String user_name;
    //原始数据
    private TaskDataVO task_data;
    //确实数据信息
    private TaskDataVO lost_data;
    //任务编号
    private String task_id;

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdentity_code() {
        return identity_code;
    }

    public void setIdentity_code(String identity_code) {
        this.identity_code = identity_code;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
