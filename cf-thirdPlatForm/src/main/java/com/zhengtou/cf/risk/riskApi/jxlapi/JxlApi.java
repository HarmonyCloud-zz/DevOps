package com.zhengtou.cf.risk.riskApi.jxlapi;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.enums.JxlCollectionTypeEnum;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.CollectionProcessVO;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.WebsiteVO;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request.AuthCollectionRequest;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request.BasicInfo;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request.CollectionPhoneJDRequest;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request.CollectionTBTokenRequest;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response.*;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.response.GetHoneyResponse;
import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.response.GetHoneyTokenResponse;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.config.Conf;
import com.zhengtou.cf.util.HttpUtils;
import com.zhengtou.cf.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 聚信立接口实现类
 *
 * @author 葛文镇
 */
@Service
public class JxlApi {
    @Autowired
    Conf conf;
    @Autowired
    MyRedisComponent myRedisComponent;

    /*************************************************蜜蜂********************************************/
    /**
     * 获取access_token
     */
    public String getToken() throws Exception {
        if (myRedisComponent.get(conf.getJxlConf().getToken_key()) == null) {
            StringBuilder tokenUrl = new StringBuilder();
            tokenUrl.append("?client_secret=" + conf.getJxlConf()
                    .getClient_secret()).append("&hours=" + conf.getJxlConf().getToken_hours())
                    .append("&org_name=" + conf.getJxlConf().getOrg_name());
            GetTokenResponse getTokenResponse = JSON.parseObject(HttpUtils.gets(conf.getJxlConf().getToken_url(), tokenUrl.toString()),
                    GetTokenResponse.class);

            //存入redis,24小时有效
            myRedisComponent.setHours(conf.getJxlConf().getToken_key(), getTokenResponse.getAccess_token(), Long.parseLong(conf.getJxlConf()
                    .getToken_hours()));
            return getTokenResponse.getAccess_token();
        } else {
            return myRedisComponent.get(conf.getJxlConf().getToken_key()).toString();
        }
    }

    /**
     * 用户基础数据
     */
    public void getBaseInfo(String name, String idcard, String phone) throws Exception {
        StringBuilder baseInfo = new StringBuilder();
        baseInfo.append("?client_secret=" + conf.getJxlConf()
                .getClient_secret()).append("&access_token=" + getToken())
                .append("&name=" + name).append("&idcard=" + idcard).append("&phone=" + phone);
        System.out.println(HttpUtils.gets(conf.getJxlConf().getBaseInfo_url(), baseInfo.toString()));
    }

    /**
     * 用户运营商数据
     */
    public GetPhoneResponse getPhoneInfo(String name, String idcard, String phone) throws Exception {
        StringBuilder baseInfo = new StringBuilder();
        baseInfo.append("?client_secret=" + conf.getJxlConf()
                .getClient_secret()).append("&access_token=" + getToken())
                .append("&name=" + name).append("&idcard=" + idcard).append("&phone=" + phone);
        return JSON.parseObject(HttpUtils.gets(conf.getJxlConf().getPhoneInfo_url(), baseInfo.toString()), GetPhoneResponse.class);
    }

    /**
     * 获取用户电商数据
     */
    public GetBussinessResponse getBussinesJdInfo(String name, String idcard, String phone) throws Exception {
        StringBuilder baseInfo = new StringBuilder();
        baseInfo.append("?client_secret=" + conf.getJxlConf()
                .getClient_secret()).append("&access_token=" + getToken())
                .append("&name=" + name).append("&idcard=" + idcard).append("&phone=" + phone);
        return JSON.parseObject(HttpUtils.gets(conf.getJxlConf().getBussinesJdInfo_url(), baseInfo.toString()), GetBussinessResponse.class);
    }

    /**
     * 获取用户电商数据
     */
    public void getBussinesTbInfo(String name, String idcard, String phone) throws Exception {
        StringBuilder baseInfo = new StringBuilder();
        baseInfo.append("?client_secret=" + conf.getJxlConf()
                .getClient_secret()).append("&access_token=" + getToken())
                .append("&token=" + getTBToken(name,idcard,phone).getToken());
        System.out.println(HttpUtils.gets(conf.getJxlConf().getBussinessTbInfo_url(), baseInfo.toString()));
    }

    /*******************数据采集*********************/
    /**************运营商**************/
    /**
     * 获取运营商采集token
     */
    public CollectionProcessVO getPhoneToken(String name, String idcard, String phone) {
        if (myRedisComponent.get("jxl_collection_phone" + name + idcard + phone) == null) {
            CollectionPhoneJDRequest collectionPhoneJDRequest = new CollectionPhoneJDRequest();
            collectionPhoneJDRequest.setBasic_info(new BasicInfo(name, idcard, phone));
            CollectionPhoneJDTokenResponse collectionPhoneJDTokenResponse = JSON.parseObject(HttpUtils.post(conf.getJxlConf().getPhone_jd_token_url(),
                    JSON.toJSONString(collectionPhoneJDRequest)),
                    CollectionPhoneJDTokenResponse.class);
            if (!collectionPhoneJDTokenResponse.getSuccess()) {
                return null;
            } else {
                CollectionProcessVO collectionProcessVO = new CollectionProcessVO(name, idcard, phone, collectionPhoneJDTokenResponse.getData()
                        .getToken(), collectionPhoneJDTokenResponse.getData().getDatasource().getCategory_name(), collectionPhoneJDTokenResponse
                        .getData().getDatasource().getWebsite());
                myRedisComponent.setSeconds("jxl_collection_phone" + name + idcard + phone, collectionProcessVO, 1200l);
                return collectionProcessVO;
            }
        } else {
            CollectionProcessVO collectionProcessVO=(CollectionProcessVO) myRedisComponent.get("jxl_collection" + name + idcard + phone);
            return collectionProcessVO;
        }
    }

    /**
     * 鉴权并采集运营商数据
     */
    public AuthCollectionResponse authPhoneCollection(String name, String idcard, String phone,String password, String captcha,String queryPwd) {
        CollectionProcessVO collectionProcessVO=getPhoneToken(name,idcard,phone);
        AuthCollectionRequest authCollectionRequest = new AuthCollectionRequest(collectionProcessVO.getToken(), phone, password, collectionProcessVO.getWebsite());
        if(StringUtils.isNotBlank(queryPwd)){
            authCollectionRequest.setQueryPwd(queryPwd);
            authCollectionRequest.setType(JxlCollectionTypeEnum.提交查询密码.getCode());
        }
        if(StringUtils.isNotBlank(captcha)){
            authCollectionRequest.setCaptcha(captcha);
            authCollectionRequest.setType(JxlCollectionTypeEnum.提交短信验证码.getCode());
        }
        AuthCollectionResponse authCollectionResponse=JSON.parseObject(HttpUtils.post(conf.getJxlConf().getCollectionPhoneJd_url(),
                JSON.toJSONString(authCollectionRequest)),AuthCollectionResponse.class);
        return authCollectionResponse;
    }
    /**************京东**************/
    /**
     * 获取京东采集token
     */
    public CollectionProcessVO getJDToken(String name, String idcard, String phone) {
        if (myRedisComponent.get("jxl_collection_jd" + name + idcard + phone) == null) {
            CollectionPhoneJDRequest collectionPhoneJDRequest = new CollectionPhoneJDRequest();
            collectionPhoneJDRequest.setBasic_info(new BasicInfo(name, idcard, phone));
            collectionPhoneJDRequest.setSkip_mobile(true);
            List<WebsiteVO> websiteVOS=new ArrayList<WebsiteVO>();
            websiteVOS.add(new WebsiteVO());
            collectionPhoneJDRequest.setSelected_website(websiteVOS);
            CollectionPhoneJDTokenResponse collectionPhoneJDTokenResponse = JSON.parseObject(HttpUtils.post(conf.getJxlConf().getPhone_jd_token_url(),
                    JSON.toJSONString(collectionPhoneJDRequest)),
                    CollectionPhoneJDTokenResponse.class);
            if (!collectionPhoneJDTokenResponse.getSuccess()) {
                return null;
            } else {
                CollectionProcessVO collectionProcessVO = new CollectionProcessVO(name, idcard, phone, collectionPhoneJDTokenResponse.getData()
                        .getToken(), collectionPhoneJDTokenResponse.getData().getDatasource().getCategory_name(), collectionPhoneJDTokenResponse
                        .getData().getDatasource().getWebsite());
                myRedisComponent.setSeconds("jxl_collection_jd" + name + idcard + phone, collectionProcessVO, 1200l);
                return collectionProcessVO;
            }
        } else {
            CollectionProcessVO collectionProcessVO=(CollectionProcessVO) myRedisComponent.get("jxl_collection_jd" + name + idcard + phone);
            return collectionProcessVO;
        }
    }

    /**
     * 鉴权并采集JD数据
     */
    public AuthCollectionResponse authJDCollection(String name, String idcard, String phone,String account,String password, String captcha) {
        CollectionProcessVO collectionProcessVO=getJDToken(name,idcard,phone);
        AuthCollectionRequest authCollectionRequest = new AuthCollectionRequest(collectionProcessVO.getToken(), account, password, collectionProcessVO.getWebsite());
        if(StringUtils.isNotBlank(captcha)){
            authCollectionRequest.setCaptcha(captcha);
            authCollectionRequest.setType(JxlCollectionTypeEnum.提交短信验证码.getCode());
        }
        AuthCollectionResponse authCollectionResponse=JSON.parseObject(HttpUtils.post(conf.getJxlConf().getCollectionPhoneJd_url(),
                JSON.toJSONString(authCollectionRequest)),AuthCollectionResponse.class);
        return authCollectionResponse;
    }

    public void getDataSourceList() throws Exception {
        System.out.println(HttpUtils.gets(conf.getJxlConf().getData_source_url(), ""));
    }
    /**************淘宝**************/
    /**
     * 获取淘宝token
     */
    public CollectionProcessVO getTBToken(String name, String idcard, String phone){
        if (myRedisComponent.get("jxl_collection_tb" + name + idcard + phone) == null) {
            CollectionTBTokenRequest collectionTBTokenRequest = new CollectionTBTokenRequest(name, idcard, phone);
            CollectionTBTokenResponse collectionPhoneJDTokenResponse = JSON.parseObject(HttpUtils.post(conf.getJxlConf().getTb_token_url(),
                    JSON.toJSONString(collectionTBTokenRequest)),
                    CollectionTBTokenResponse.class);
            if (!collectionPhoneJDTokenResponse.getSuccess()) {
                return null;
            } else {
                CollectionProcessVO collectionProcessVO = new CollectionProcessVO(name, idcard, phone, collectionPhoneJDTokenResponse.getData()
                        .getToken(), collectionPhoneJDTokenResponse.getData().getDatasource().getCategory_name(), collectionPhoneJDTokenResponse
                        .getData().getDatasource().getWebsite());
                myRedisComponent.setSeconds("jxl_collection_tb" + name + idcard + phone, collectionProcessVO, 1200l);
                return collectionProcessVO;
            }
        }else {
            CollectionProcessVO collectionProcessVO=(CollectionProcessVO) myRedisComponent.get("jxl_collection_tb" + name + idcard + phone);
            return collectionProcessVO;
        }
    }
    /**
     * 鉴权并采集TB数据
     */
    public void authTBCollection(String name, String idcard, String phone,String account,String password) {
        CollectionProcessVO collectionProcessVO=getTBToken(name,idcard,phone);
        StringBuffer postString=new StringBuffer("{\"token\":\"");
        postString.append(collectionProcessVO.getToken()).append("\",");
        postString.append("\"account\":").append("\"").append(account).append("\",");
        postString.append("\"password\":").append(StringUtils.isNotBlank(password)?("\""+password+"\""):("\"\""));
        postString.append("}");
        System.out.println(HttpUtils.post(conf.getJxlConf().getTb_code_url(),
                postString.toString()));
        System.out.println(HttpUtils.post(conf.getJxlConf().getTb_collection_url(),
                "{\"token\":\""+collectionProcessVO.getToken()+"\"}"));
    }

    /**********************************************蜜罐**********************************************/
    /**
     * 获取token
     */
    public String getHoneyToken() throws Exception {
        if (myRedisComponent.get(conf.getJxlConf().getHoneyToken_key()) == null) {
            StringBuilder tokenUrl = new StringBuilder();
            tokenUrl.append("?client_secret=" + conf.getJxlConf()
                    .getClient_secret())
                    .append("&account=" + conf.getJxlConf().getAccount());
            GetHoneyTokenResponse getTokenResponse = JSON.parseObject(HttpUtils.gets(conf.getJxlConf().getHoneyToken_url(), tokenUrl.toString()),
                    GetHoneyTokenResponse.class);

            //存入redis,24小时有效
            myRedisComponent.setHours(conf.getJxlConf().getHoneyToken_key(), getTokenResponse.getData().getAccess_token(), Long.parseLong(conf
                    .getJxlConf().getToken_hours()));
            return getTokenResponse.getData().getAccess_token();
        } else {
            return myRedisComponent.get(conf.getJxlConf().getHoneyToken_key()).toString();
        }
    }

    /**
     * 获取用户基本数据数据
     */
    public GetHoneyResponse getHoneyInfo(String name, String idcard, String phone) throws Exception {
        StringBuilder baseInfo = new StringBuilder();
        baseInfo.append("?client_secret=" + conf.getJxlConf()
                .getClient_secret()).append("&access_token=" + getHoneyToken())
                .append("&name=" + name).append("&id_card=" + idcard).append("&phone=" + phone);
        return JSON.parseObject(HttpUtils.gets(conf.getJxlConf().getHoneyJar_url(), baseInfo.toString()), GetHoneyResponse.class);
    }
}
