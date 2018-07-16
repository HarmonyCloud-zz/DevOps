package com.harmony.devops.thirdplatform.api.phone.controller;

import com.alibaba.fastjson.JSONObject;
import com.harmony.devops.thirdplatform.config.Conf;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 深海捷呼叫中心接口实现类
 */
@Service
@RestController
@RequestMapping("phone/shj")
@Api(description = "深海捷电话服务")
public class ShjApi {
    @Autowired
    Conf conf;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 电话呼出
     *
     * @param innerNo   内部分机号码
     * @param destPhone 呼出外部手机号，非郑州地区手机号码前面需要加0
     * @return
     */
    @RequestMapping(value = "call", method = RequestMethod.POST)
    @ApiOperation("电话呼出")
    public NetVO dail(@RequestParam @ApiParam(value = "内部座机号", name = "innerNo") String innerNo, @RequestParam @ApiParam(value = "内部座机号", name =
            "呼出电话号") String destPhone) {

        StringBuilder url = new StringBuilder();
        url.append(conf.getShjConf().getPhone_url());
        url.append("?m=interface&c=api&a=dial&extension=");
        url.append(innerNo);
        url.append("&extensionDst=");
        url.append(destPhone);

        String result = restTemplate.getForObject(url.toString(), String.class);
        JSONObject object = JSONObject.parseObject(result);
        if ("1".equals(object.getString("result")))
            return new SuccFessionVO();
        else
            return new ErrorFessionVO();
    }
}
