package com.harmony.devops.user.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.harmony.devops.user.thirdApi.handler.UniformExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class PushAPI {
    private static final Logger logger = LoggerFactory.getLogger(UniformExceptionHandler.class);

    //在极光注册上传应用的 appKey 和 masterSecret
    public static final String appKey = "7521a56cbbf8c5306013f003";//必填，例如466f7032ac604e02fb7bda89
    public static final String masterSecret = "69da7ad3609b3b7295a89af3";//必填，每个应用都对应一个masterSecret

    /**
     * 推送所有平台 用于广播消息  用于管理员使用
     */
    public String sendPushAllMessage(String msg) throws BaseException {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        //生成推送的内容，这里我们先测试全部推送
        PushPayload payload = JpushClientZT.buildPushObject_all_all_alert(msg);
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            logger.info("sendPushAllAlert ：Got result - " + result);
            return result.msg_id+"";
        } catch (APIConnectionException e) {
            throw new BaseException(RtnResultEnum.E110000);
        } catch (APIRequestException e) {
            throw new BaseException(e.getErrorCode()+"",e.getErrorMessage());
        }
    }

    /**
     * 根据别名推送所有平台
     * 多目标,最多一次1000条
     */
    public String sendPushAliasAllMessage(String[] alias,String msg) throws BaseException {
        if(alias.length>1000){
            throw new BaseException(RtnResultEnum.E110001);
        }
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        //生成推送的内容，这里我们先测试全部推送
        PushPayload payload = JpushClientZT.buildPushObject_all_alias_alert(alias,msg);
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            logger.info("sendPushAliasAllAlert ：Got result - " + result);
            return result.msg_id+"";
        } catch (APIConnectionException e) {
            throw new BaseException(RtnResultEnum.E110000);
        } catch (APIRequestException e) {
            throw new BaseException(e.getErrorCode()+"",e.getErrorMessage());
        }
    }

    /**
     * 根据别名推送所有平台
     * 多目标
     */
    public String sendPushAliasAllMessage(String alias,String msg) throws BaseException {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
        //生成推送的内容，这里我们先测试全部推送
        PushPayload payload = JpushClientZT.buildPushObject_all_alias_alert(new String[]{alias},msg);
        try {
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            logger.info("sendPushAliasAllAlert ：Got result - " + result);
            return result.msg_id+"";
        } catch (APIConnectionException e) {
            throw new BaseException(RtnResultEnum.E110000);
        } catch (APIRequestException e) {
            throw new BaseException(e.getErrorCode()+"",e.getErrorMessage());
        }
    }

    public static void main(String[] args) throws BaseException {
        PushAPI jPushManage = new PushAPI();
        //所有平台所有人
        System.out.println(jPushManage.sendPushAllMessage("【微利贷】111111111"));
        System.out.println(jPushManage.sendPushAliasAllMessage(new String[]{"ZTU20180410143137406"},"【微利贷】tttttt"));
        System.out.println(jPushManage.sendPushAliasAllMessage("ZTU20180410143137406","【微利贷】tttttt1111111"));
    }
}
