package com.harmony.devops.thirdplatform.config.td;

/**
 * 同盾接口配置项
 * @author 葛文镇
 */
public class TdConf {
    private String partner_code="zhengtou";
    private String partner_key="c98d837cd9004ebc95c47d6132338c20";
    private String partner_app="zhengtou_web";
    private String td_api_url="https://apitest.tongdun.cn/bodyguard/apply/v4";

    public TdConf(String partnerApp){
        this.partner_app=partnerApp;
    }

    public String getUrl(){
        return new StringBuilder().append(td_api_url).append("?partner_code=").append(partner_code).append("&partner_key=").append(partner_key).append("&app_name=").append(partner_app).toString();
    }

    public String getCrawUrl(){
        return new StringBuilder().append(td_api_url).append("?partner_code=").append(partner_code).append("&partner_key=").append(partner_key).toString();
    }
}

