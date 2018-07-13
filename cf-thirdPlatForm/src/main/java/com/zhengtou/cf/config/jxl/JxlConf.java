package com.zhengtou.cf.config.jxl;
/**
 * 聚信立接口配置项
 * @author 葛文镇
 */
public class JxlConf {
    private String org_name="zhengtou";
    private String client_secret="e47c3b61025c4219a733b4447090779b";
    private String token_hours="24";
    private String account="zhengtou";

    /********************************************************蜜蜂***************************************/
    //access_token,redis主键
    private String token_key="jxl";
    //获取access_token地址
    private String token_url="https://www.juxinli.com/api/v2/access_report_token";
    //获取基础信息地址
    private String baseInfo_url="https://www.juxinli.com/api/access_report_data";
    //获取运营商数据地址
    private String phoneInfo_url="https://www.juxinli.com/api/access_raw_data";
    //获取电商数据地址
    private String bussinesJdInfo_url="https://www.juxinli.com/api/access_e_business_raw_data";
    //获取淘宝数据地址
    private String bussinessTbInfo_url="https://www.juxinli.com/api/access_e_business_raw_data_by_token";
    /*******************数据采集*********************/
    /**************运营商、京东**************/
    //数据源采集请求 先运营商，再京东
    private String phone_jd_token_url="https://www.juxinli.com/orgApi/rest/v3/applications/zhengtou";
    //发送数据源采集请求
    private String collectionPhoneJd_url="https://www.juxinli.com/orgApi/rest/v2/messages/collect/req";
    //跳过数据源接口
    private String skipPhone_url="https://www.juxinli.com/orgApi/rest/v2/messages/collect/skip";
    //重置phone密码接口
    private String resetPhone_url="https://www.juxinli.com/orgApi/rest/v2/messages/reset/req";
    //获取数据源列表 京东
    private String data_source_url="https://www.juxinli.com/orgApi/rest/v2/orgs/zhengtou/datasources";
    /*****************淘宝*****************/
    //获取淘宝采集token
    private String tb_token_url="https://www.juxinli.com/orgApi/rest/v3/taobao/applications/zhengtou";
    //获取用户登陆二维码
    private String tb_code_url="https://www.juxinli.com/orgApi/rest/v3/taobao/message/collect/req/zhengtou";
    //采集结果响应地址
    private String tb_collection_url="https://www.juxinli.com/orgApi/rest/v3/taobao/messages/qrcodeCollect/resp/";
    /******************************************************蜜罐**************************************/
    private String honeyToken_key="jxlHoney";
    private String honeyToken_url="https://mi.juxinli.com/api/access_token";
    private String honeyJar_url="https://mi.juxinli.com/api/search";

    public JxlConf(){
    }

    public String getBussinesJdInfo_url() {
        return bussinesJdInfo_url;
    }

    public void setBussinesJdInfo_url(String bussinesJdInfo_url) {
        this.bussinesJdInfo_url = bussinesJdInfo_url;
    }

    public String getBussinessTbInfo_url() {
        return bussinessTbInfo_url;
    }

    public void setBussinessTbInfo_url(String bussinessTbInfo_url) {
        this.bussinessTbInfo_url = bussinessTbInfo_url;
    }

    public String getTb_token_url() {
        return tb_token_url;
    }

    public void setTb_token_url(String tb_token_url) {
        this.tb_token_url = tb_token_url;
    }

    public String getTb_code_url() {
        return tb_code_url;
    }

    public void setTb_code_url(String tb_code_url) {
        this.tb_code_url = tb_code_url;
    }

    public String getTb_collection_url() {
        return tb_collection_url;
    }

    public void setTb_collection_url(String tb_collection_url) {
        this.tb_collection_url = tb_collection_url;
    }

    public String getPhone_jd_token_url() {
        return phone_jd_token_url;
    }

    public void setPhone_jd_token_url(String phone_jd_token_url) {
        this.phone_jd_token_url = phone_jd_token_url;
    }

    public String getCollectionPhoneJd_url() {
        return collectionPhoneJd_url;
    }

    public void setCollectionPhoneJd_url(String collectionPhoneJd_url) {
        this.collectionPhoneJd_url = collectionPhoneJd_url;
    }

    public String getSkipPhone_url() {
        return skipPhone_url;
    }

    public void setSkipPhone_url(String skipPhone_url) {
        this.skipPhone_url = skipPhone_url;
    }

    public String getResetPhone_url() {
        return resetPhone_url;
    }

    public void setResetPhone_url(String resetPhone_url) {
        this.resetPhone_url = resetPhone_url;
    }

    public String getAccount() {
        return account;
    }

    public String getData_source_url() {
        return data_source_url;
    }

    public void setData_source_url(String data_source_url) {
        this.data_source_url = data_source_url;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHoneyToken_key() {
        return honeyToken_key;
    }

    public void setHoneyToken_key(String honeyToken_key) {
        this.honeyToken_key = honeyToken_key;
    }

    public String getHoneyToken_url() {
        return honeyToken_url;
    }

    public void setHoneyToken_url(String honeyToken_url) {
        this.honeyToken_url = honeyToken_url;
    }

    public String getBaseInfo_url() {
        return baseInfo_url;
    }

    public String getHoneyJar_url() {
        return honeyJar_url;
    }

    public void setHoneyJar_url(String honeyJar_url) {
        this.honeyJar_url = honeyJar_url;
    }

    public void setBaseInfo_url(String baseInfo_url) {
        this.baseInfo_url = baseInfo_url;
    }

    public String getPhoneInfo_url() {
        return phoneInfo_url;
    }

    public void setPhoneInfo_url(String phoneInfo_url) {
        this.phoneInfo_url = phoneInfo_url;
    }

    public String getToken_key() {
        return token_key;
    }

    public void setToken_key(String token_key) {
        this.token_key = token_key;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getToken_url() {
        return token_url;
    }

    public void setToken_url(String token_url) {
        this.token_url = token_url;
    }

    public String getToken_hours() {
        return token_hours;
    }

    public void setToken_hours(String token_hours) {
        this.token_hours = token_hours;
    }
}
