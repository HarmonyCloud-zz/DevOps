package com.zhengtou.cf.api;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.api.third.PayApi;
import com.zhengtou.cf.api.third.SignatureApi;
import com.zhengtou.cf.api.third.vo.SignInfo;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.BindCardEnum;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.exception.BaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class apiTest {
    @Autowired
    PayApi payApi;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    SignatureApi signatureApi;
    /**
     * 易宝测试
     */
    @Test
    public void authBankCard(){
        try {
            BindCardEnum bindCardEnum=payApi.authBankCard("111","6228270711228084774","410221199508020839","高来松","15225470606","2018-03-13 17:06:28");
        } catch (BaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMap(){
        Map<String, String> result	= new HashMap<String, String>();
        result.put("111","aaaaa");
        result.put("2222","bbbbb");
        result.put("333","cccc");
        System.out.println(result);
    }

    @Test
    public void testRedis(){
        System.out.println(myRedisComponent.hasKey("risk2"));
        System.out.println(redisTemplate.keys("risk*"));
        Set<String> strings=redisTemplate.keys("risk*");
        for(String k:strings){
            Object o=myRedisComponent.get(k);
            System.out.println(JSON.toJSONString(o));
        }
    }

    @Test
    public void testAnt() {
        List<SignInfo> signInfos=new ArrayList<>();
        SignInfo signInfo=new SignInfo(350,600,1);
        SignInfo signInfo1=new SignInfo(50,50,2);
        signInfos.add(signInfo);
        signInfos.add(signInfo1);
        try {
            signatureApi.companySign("d:/input/zhengtouwang.dat","d:/input/CN201804081639528988.pdf","d:/input/test111.pdf",signInfos);
        } catch (BaseException e) {
            System.out.println(e.getErrorCode()+"|"+e.getErrorMsg());
        }
    }

    @Test
    public void testPersonAnt() {
        List<SignInfo> signInfos=new ArrayList<>();
        SignInfo signInfo=new SignInfo(50,230,1);
        SignInfo signInfo1=new SignInfo(50,430,2);
        signInfos.add(signInfo);
        signInfos.add(signInfo1);
        try {
            signatureApi.personSign("葛文镇","410881199012263158","d:/input/222.pdf","d:/input/333.pdf",signInfos);
        } catch (BaseException e) {
            System.out.println(e.getErrorCode()+"|"+e.getErrorMsg());
        }
    }

    @Test
    public void testAntzs() {
        List<SignInfo> signInfos=new ArrayList<>();
        SignInfo signInfo=new SignInfo(50,230,1);
        SignInfo signInfo1=new SignInfo(50,430,2);
        signInfos.add(signInfo);
        signInfos.add(signInfo1);
        try {
            signatureApi.companySign("/home/source/azt/zhengtouwang.dat","/home/data/ZTO201804080764/D_233239.pdf","/home/data/111.pdf",signInfos);
        } catch (BaseException e) {
            System.out.println(e.getErrorCode()+"|"+e.getErrorMsg());
        }
    }

    @Test
    public void testPersonAntzs() {
        List<SignInfo> signInfos=new ArrayList<>();
        SignInfo signInfo=new SignInfo(400,230,1);
        SignInfo signInfo1=new SignInfo(500,430,2);
        signInfos.add(signInfo);
        signInfos.add(signInfo1);
        try {
            signatureApi.personSign("葛文镇","410881199012263158","/home/data/111.pdf","/home/data/2222.pdf",signInfos);
        } catch (BaseException e) {
            System.out.println(e.getErrorCode()+"|"+e.getErrorMsg());
        }
    }

    @Test
    public void testMacdownDocToPdf() {
        HashMap<String,String> map=new HashMap<>();
        map.put("DOCSEQNO","na123123123132");
        map.put("PAYNAME","郑投网");//出借人
        map.put("PAYIDCARD","444401545484");//出借人
        map.put("BORROWNAME","葛文镇");//出借人
        map.put("BORROWIDCARD","410881199012263158");//出借人
        map.put("AMOUNTUPPER","壹仟元整");//出借人
        map.put("AMOUNTLOWER","1000.00");//出借人
        map.put("DAYS","20");//出借人
        try {
            signatureApi.macdownDocToPdf("/home/data/contractTempl/ZTO201804080764/D_482029.docx","/home/data/test111111.pdf",map);
        } catch (BaseException e) {
            System.out.println(e.getErrorCode()+"|"+e.getErrorMsg());
        }
    }
}
