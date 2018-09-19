package com.harmony.devops.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/31
 * @描述
 */
public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 发送post请求
     */
    public static String post(String url, Map<String, Object> args) {
        log.info("Url: " + url + ", Request: " + DBUtil.mapToString(args));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(10000);
        client.getHttpConnectionManager().getParams().setSoTimeout(30000);
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Content-type",
                "application/x-www-form-urlencoded");
        post.addRequestHeader("Connection", "Close");// 是否保持连接
        post.addRequestHeader("Accept-Encoding", "identity");
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            post.addParameter(entry.getKey(), entry.getValue()+"");
        }
        try {
            client.executeMethod(post);
            String responseBody = post.getResponseBodyAsString();
            log.info("Response: " + responseBody);
            return responseBody;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            post.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return null;
    }

    /**
     * 以固定格式发送
     */
    public static String post(String url, String data, String charset) {
        log.info("Request: " + data);
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams()
                .setConnectionTimeout(10000);
        client.getHttpConnectionManager().getParams().setSoTimeout(30000);
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Content-type",
                "application/x-www-form-urlencoded");
        post.addRequestHeader("Connection", "Close");// 是否保持连接
        post.addRequestHeader("Accept-Encoding", "identity");
        try {
            post.setRequestEntity(new StringRequestEntity(data,
                    "application/x-www-form-urlencoded", charset));
            client.executeMethod(post);
            String response = new String(
                    post.getResponseBodyAsString().getBytes(charset),
                    "utf-8");
            log.info("Response: " + response);
            return response;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return null;
    }
}
