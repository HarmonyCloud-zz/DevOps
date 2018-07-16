package com.harmony.devops.thirdplatform.api.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.harmony.devops.thirdplatform.config.Conf;
import com.harmony.devops.thirdplatform.util.Md5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * 亿美 - 短信发送api
 */
@RestController
@RequestMapping("sms/yimei")
@Api(description = "亿美短信服务")
@Validated
public class SmsApi {
    @Autowired
    Conf conf;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 发送短信
     *
     * @param phone   需要发送的短信号码
     * @param content 需要发送的短信内容，内容必须以【郑投网】开头
     */
    @RequestMapping(value = "sendSms", method = RequestMethod.POST)
    @ApiOperation("短信发送")
    public NetVO sendMsg(@RequestParam @ApiParam(value = "手机号", name = "phone") @Phone String phone, @RequestParam @ApiParam(value = "短信内容", name =
            "content") String content) {

        String timeStamp = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String sign = Md5Util.getMD5(conf.getYmConf().getAppid() + conf.getYmConf().getSecretKey() + timeStamp);

        StringBuilder url = new StringBuilder(conf.getYmConf().getSms_url());
        url.append("?appId=");
        url.append(conf.getYmConf().getAppid());
        url.append("&timestamp=");
        url.append(timeStamp + "&sign=");
        url.append(sign + "&mobiles=");
        url.append(phone + "&content=【郑投网】");
        url.append(content);
        String result = restTemplate.getForObject(url.toString(), String.class);
        JSONObject object = JSONObject.parseObject(result);
        if ("SUCCESS".equals(object.getString("code"))) {
            return new SuccFessionVO();
        } else {
            return new ErrorFessionVO(object.getString("code"), "");
        }
    }
}
