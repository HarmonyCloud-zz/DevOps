package com.zhengtou.cf.api.app.v02;

import com.zhengtou.cf.api.APIResult.VersionResult;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("version")
@RequestMapping("api/v0.2/version")
@Api(description = "版本号信息")
@Validated
public class VersionApi {
    @Value("${android.versionCode}")
    private String versionCode;
    @Value("${android.versionName}")
    private String versionName;
    @Value("${android.path}")
    private String versionPath;

    /**
     * 获取android当前版本
     */
    @RequestMapping(value = "getAndroidVersion", method = RequestMethod.GET)
    @ApiOperation("获取android当前版本")
    @ApiResponses({@ApiResponse(code = 200, response = VersionResult.class, message = "android版本信息vo")})
    public NetVO getAndroidVersion() {
        return new ResponseVO(new VersionResult(versionCode, versionName, "更新内容描述", versionPath));
    }
}
