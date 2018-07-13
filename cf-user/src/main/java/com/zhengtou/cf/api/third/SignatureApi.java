package com.zhengtou.cf.api.third;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.api.third.vo.SignInfo;
import com.zhengtou.cf.common.enums.RtnStatusEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.conf.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

/**
 * 签章服务api
 *
 * @author 葛文镇
 */
@Service
public class SignatureApi {
    @Autowired
    ApiConfig apiConfig;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 郑投签章
     */
    public void ztSign(String fileUrl, String outUrl, List<SignInfo> signInfoList) throws BaseException {
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/signAzt/ztSign?fileUrl=" + fileUrl + "&outUrl=" + outUrl,
                signInfoList, String.class).getBody();
        JSONObject jo = JSON.parseObject(entity);
        if (!"200".equals(jo.getString("code")) || !"SUCCESS".equals(jo.getString("status"))) {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 公司签章
     */
    public void companySign(String datUrl, String fileUrl, String outUrl, List<SignInfo> signInfoList) throws BaseException {
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/signAzt/companySign?datGzUrl=" + datUrl + "&fileUrl=" + fileUrl
                        + "&outUrl=" + outUrl,
                signInfoList,
                String.class).getBody();
        JSONObject jo = JSON.parseObject(entity);
        if (!"200".equals(jo.getString("code")) || !"SUCCESS".equals(jo.getString("status"))) {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * 个人签章
     */
    public void personSign(String realName, String idNo, String fileUrl, String outUrl, List<SignInfo> signInfoList) throws BaseException {
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/signAzt/personSign?realName=" + realName + "&idNo=" + idNo +
                "&fileUrl=" +
                fileUrl + "&outUrl=" + outUrl, signInfoList, String.class).getBody();
        JSONObject jo = JSON.parseObject(entity);
        if (!"200".equals(jo.getString("code")) || !"SUCCESS".equals(jo.getString("status"))) {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }

    /**
     * macdown 格式doc文件转pdf并替换字符
     */
    public void macdownDocToPdf(String macPath, String pdfPath, HashMap<String, String> map) throws BaseException {
        String entity = restTemplate.postForEntity(apiConfig.getThirdApiConf() + "/signAzt/macdownDocToPdf?macPath=" + macPath + "&pdfPath=" +
                pdfPath , map, String.class).getBody();
        JSONObject jo = JSON.parseObject(entity);
        if (!"200".equals(jo.getString("code")) || !"SUCCESS".equals(jo.getString("status"))) {
            throw new BaseException(RtnStatusEnum.FAIL, jo.getString("status"), jo.getString("desc"));
        }
    }
}
