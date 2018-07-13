package com.zhengtou.cf.config.sms;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class YmConf {
    private  String sms_url="http://bjmtn.b2m.cn/simpleinter/sendSMS";
    private String appid="3SDK-EMY-0130-KDTLN";
    private String secretKey="5E25B346B08B0016";

    public String getSms_url() {
        return sms_url;
    }

    public void setSms_url(String sms_url) {
        this.sms_url = sms_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
