package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.api.signature.aztapi.AztApi;
import com.zhengtou.cf.api.signature.aztapi.vo.SignInfo;
import com.zhengtou.cf.common.exception.BaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 工具测试
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class SignatureTest {
    @Autowired
    AztApi aztApi;
    @Test
    public void getSysDateTimeString() throws BaseException {
        List<SignInfo> signInfoList=new ArrayList<>();
        SignInfo signInfo=new SignInfo(270,730,1);
        SignInfo signInfo1=new SignInfo(250,100,2);
        signInfoList.add(signInfo);
        signInfoList.add(signInfo1);
        //郑投网签章
        aztApi.companySign("d:/input/zhengtouwang.dat","d:/input/CN201804081639528988.pdf","d:/input/test11.pdf",signInfoList);

    }

    @Test
    public void getPersonSign() throws BaseException {
        List<SignInfo> signInfoList=new ArrayList<>();
        SignInfo signInfo=new SignInfo(130,710,1);
        SignInfo signInfo1=new SignInfo(120,780,3);
        signInfoList.add(signInfo);
        signInfoList.add(signInfo1);
        //郑投网签章
        aztApi.personSign("葛文镇","410881199012263158","d:/input/test11.pdf","d:/input/test22.pdf",signInfoList);
    }

    @Test
    public void htmlToPdf() throws BaseException {
        aztApi.html2pdf("D:/input/htmltopdf444.pdf","D:/input/22.html");
    }

    @Test
    public void macToHtml() throws IOException, BaseException {
        HashMap<String,String> map=new HashMap<>();
        aztApi.macdownToHtml("D:/input/consumer.docx","D:/input/22.html",map);
    }
}
