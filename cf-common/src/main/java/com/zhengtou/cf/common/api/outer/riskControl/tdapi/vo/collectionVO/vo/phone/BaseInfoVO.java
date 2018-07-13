package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 个人信息
 * @author 葛文镇
 */
public class BaseInfoVO {
    //姓名
    private String user_name;
    //性别
    private String user_sex;
    //手机号
    private String user_number;
    //身份证
    private String cert_num;
    //联系地址
    private String cert_addr;
    //联系电话
    private String user_contact_no;
    //邮箱地址
    private String user_email;
    //邮政编码
    private String post_code;

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getCert_num() {
        return cert_num;
    }

    public void setCert_num(String cert_num) {
        this.cert_num = cert_num;
    }

    public String getCert_addr() {
        return cert_addr;
    }

    public void setCert_addr(String cert_addr) {
        this.cert_addr = cert_addr;
    }

    public String getUser_contact_no() {
        return user_contact_no;
    }

    public void setUser_contact_no(String user_contact_no) {
        this.user_contact_no = user_contact_no;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
