package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request;

/**
 * 聚信立采集数据权限认证
 * @author 葛文镇
 */
public class AuthCollectionRequest {
    private String token;
    private String account;
    private String password;
    private String captcha;
    private String website;
    private String type;
    private String queryPwd;

    public AuthCollectionRequest(String token, String account, String password, String website) {
        this.token = token;
        this.account = account;
        this.password = password;
        this.website = website;
    }

    public String getQueryPwd() {
        return queryPwd;
    }

    public void setQueryPwd(String queryPwd) {
        this.queryPwd = queryPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
