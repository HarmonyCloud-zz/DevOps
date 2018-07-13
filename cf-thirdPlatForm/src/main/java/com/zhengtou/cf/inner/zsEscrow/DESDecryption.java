package com.zhengtou.cf.inner.zsEscrow;/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2007 All Rights Reserved.
 */

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class DESDecryption {
    @Autowired
    RestTemplate restTemplate;

    private static final String ALGORITHM = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish        //keybyte为加密密钥，长度为24字节    //src为被加密的数据缓冲区（源）
    /**
     * 设置数据流为解密模式
     * @param des3Key
     * @param inputStream
     * @return
     */
    public static CipherInputStream decryptMode(String des3Key, InputStream inputStream) {
        //生成密钥
        SecretKey deskey = new SecretKeySpec(des3Key.getBytes(), ALGORITHM); //解密
        return decryptMode(deskey, inputStream);
    }

    /**
     * 设置数据流为解密模式
     * @param secretKey
     * @param inputStream
     * @return
     */
    public static CipherInputStream decryptMode(SecretKey secretKey, InputStream inputStream) {
        try {
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(Cipher.DECRYPT_MODE, secretKey);
            return new CipherInputStream(inputStream, c1);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置数据流为加密模式
     * @param des3Key
     * @param inputStream
     * @return
     */
    public static CipherInputStream encryptMode(String des3Key, InputStream inputStream) {
        //生成密钥
        SecretKey deskey = new SecretKeySpec(des3Key.getBytes(), ALGORITHM); //解密
        return encryptMode(deskey, inputStream);
    }

    /**
     * 设置数据流为加密模式
     * @param secretKey
     * @param inputStream
     * @return
     */
    public static CipherInputStream encryptMode(SecretKey secretKey, InputStream inputStream) {
        try {
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(Cipher.ENCRYPT_MODE, secretKey);
            return new CipherInputStream(inputStream, c1);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String desDecryption(String fileUrl) throws IOException {
        StringBuffer fileContent = new StringBuffer();
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
        GetMethod post = new GetMethod(fileUrl);
        client.executeMethod(post);
        System.out.println(post.getResponseBodyAsString());
        InputStream fileInputStream= post.getResponseBodyAsStream();
        InputStream cipherInputStream=decryptMode("192837464637281964738291",fileInputStream);
        ZipInputStream zipInputStream=new ZipInputStream(cipherInputStream);
        ZipEntry entry = null;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (entry.isDirectory()) {
                zipInputStream.closeEntry();
                continue;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    zipInputStream));
            String s;
            while ((s = in.readLine()) != null)
                fileContent.append(new String(s.getBytes("iso8859-1"),"utf8")).append("\n");
            zipInputStream.closeEntry();
        }
        return fileContent.toString();
    }
}
