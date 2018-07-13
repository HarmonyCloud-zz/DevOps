package com.zhengtou.cf.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求方法封装
 * @author 葛文镇
 */

public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 发送post请求
     * params
     */
    public static String post(String url, Map<String, String> args) {
        logger.info("Url: " + url + ", Request: " + StringUtils.mapToString(args));
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        client.getHttpConnectionManager().getParams().setSoTimeout(30000);
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Content-type", "application/x-www-form-urlencoded");
        post.addRequestHeader("Connection", "Close");// 是否保持连接
        post.addRequestHeader("Accept-Encoding", "identity");
        for (Map.Entry<String, String> entry : args.entrySet()) {
            post.addParameter(entry.getKey(), entry.getValue());
        }
        try {
            client.executeMethod(post);
            String responseBody = post.getResponseBodyAsString();
            logger.info("Response: " + responseBody);
            return responseBody;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            post.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return null;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * postBody
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String post(String url, String param) {
        logger.info("Url: " + url + ", Request: " + param);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("content-type",
                    "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("发送 POST 请求出现异常！"+e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                logger.info("请求关闭异常"+ex);
            }
        }
        logger.info("Response: " + result.toString());
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * postBody
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String tdPost(String url, String param) {
        logger.info("Url: " + url + ", Request: " + param);
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.info("发送 POST 请求出现异常！"+e);
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                logger.info("请求关闭异常"+ex);
            }
        }
        logger.info("Response: " + result.toString());
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * postBody
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String ybPost(String url, String param) {
        logger.info("易宝Url: " + url + ", Request: " + param);
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
            post.setRequestEntity(new StringRequestEntity(param,
                    "application/x-www-form-urlencoded", "utf8"));
            client.executeMethod(post);
            String response = post.getResponseBodyAsString();
            logger.info("易宝Response: " + response);
            return response;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            post.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return null;
    }

    /**
     * 以固定格式发送<br>
     * 带证书
     */
    public static String posts(String url, String data, String path,
                               String certPwd)
            throws KeyStoreException, IOException, KeyManagementException,
            UnrecoverableKeyException, NoSuchAlgorithmException {
        logger.info("Request: " + data);
        CloseableHttpResponse response = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(path));// 加载本地的证书进行https加密传输
        try {
            keyStore.load(instream, certPwd.toCharArray());// 设置证书密码
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, certPwd.toCharArray()).build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[]{"TLSv1"}, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        CloseableHttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(data, Consts.UTF_8));
        httpPost.setConfig(RequestConfig.custom().setConnectTimeout(30000)
                .setSocketTimeout(30000).build());
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            logger.info(new String(
                    EntityUtils.toString(entity).getBytes("iso-8859-1"),
                    "utf-8"));
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info(String.valueOf(statusCode));
            if (HttpStatus.SC_OK == statusCode) {
                // 如果响应码是200
                String response1 = new String(
                        EntityUtils.toString(entity).getBytes("iso-8859-1"),
                        "utf-8");
                logger.info("Response: " + response1);
                return response1;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            client.close();
        }
        return null;
    }

    /**
     * 发送https，get请求
     *
     * @throws Exception
     */
    public String gets(String url, Object baseRequest)
            throws Exception {
        Map<String, Object> param = parseMap(baseRequest);
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            if(param!=null){
                url+="?";
                for (String key : param.keySet()) {
                    url+=key+":"+param.get(key).toString()+"&";
                }
                url=url.substring(0,url.length()-1);
            }
            httpGet = new HttpGet(url);
            httpGet.setConfig(RequestConfig.custom().setConnectTimeout(30000)
                    .setSocketTimeout(30000).build());
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode) {
                // 如果响应码是200
                String response1 = EntityUtils.toString(entity);
                logger.info("Response: " + response1);
                return response1;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            httpClient.close();
        }
        return null;
    }

    /**
     * 发送https，get请求
     *
     * @throws Exception
     */
    public static String gets(String url, String params)
            throws Exception {
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpGet httpGet = null;
        CloseableHttpResponse response = null;
        try {
            httpGet = new HttpGet(url+params);
            httpGet.setConfig(RequestConfig.custom().setConnectTimeout(30000)
                    .setSocketTimeout(30000).build());
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode) {
                // 如果响应码是200
                String response1 = EntityUtils.toString(entity);
                logger.info("Response: " + response1);
                return response1;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            httpClient.close();
        }
        return null;
    }

    // 创建链接
    public static CloseableHttpClient createSSLClientDefault()
            throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, new AllTrustStrategy()).build();
        SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(
                sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslSf).build();
    }

    // 加载证书
    private static class AllTrustStrategy implements TrustStrategy {
        public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                throws CertificateException {
            return true;
        }
    }

    public Map<String, Object> parseMap(Object o) throws IllegalArgumentException, IllegalAccessException {
        if(o!=null){
            Map<String, Object> map = new HashMap<String, Object>();
            for (Field param : o.getClass().getFields()) {
                String paramName = param.getName();
                map.put(paramName, param.get(this));
            }
            return map;
        }else{
            return Collections.EMPTY_MAP;
        }
    }

}
