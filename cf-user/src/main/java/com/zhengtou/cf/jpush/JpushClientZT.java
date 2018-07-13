package com.zhengtou.cf.jpush;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class JpushClientZT {
    /**
     * 构建推送对象：对所有平台，所有设备，内容为 alert的通知
     * @param alter
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(String alter) {
        return PushPayload.alertAll(alter);
    }
    /**
     * 所有平台，推送目标是别名为 "alias"，通知内容为 alert
     */
    public static PushPayload buildPushObject_all_alias_alert(String[] alias,Object alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }
}
