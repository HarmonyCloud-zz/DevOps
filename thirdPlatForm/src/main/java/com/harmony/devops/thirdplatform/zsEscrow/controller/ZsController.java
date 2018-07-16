package com.harmony.devops.thirdplatform.zsEscrow.controller;

import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.StringResponseVO;
import com.harmony.devops.thirdplatform.zsEscrow.DESDecryption;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("inner/zs/")
@Api(description = "浙商服务")
public class ZsController {
    @Autowired
    DESDecryption desDecryption;

    //有短验绑卡申请
    @RequestMapping(value = "desDecryption", method = RequestMethod.POST)
    @ApiOperation("解密服务")
    public NetVO desDecryption(@RequestParam String url) throws Exception {
        return new StringResponseVO(desDecryption.desDecryption(url));
    }

}
