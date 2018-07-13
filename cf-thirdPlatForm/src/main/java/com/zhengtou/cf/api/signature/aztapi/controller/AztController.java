package com.zhengtou.cf.api.signature.aztapi.controller;

import com.zhengtou.cf.api.signature.aztapi.AztApi;
import com.zhengtou.cf.api.signature.aztapi.vo.SignInfo;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @Description :
 * @Author: chengzx
 * @Date: Created in  2018/1/11
 */
@RestController
@RequestMapping("signAzt")
@Api(description = "签章服务")
public class AztController {
    @Autowired
    AztApi aztApi;
    @Value("${sign.azt.datgz}")
    private String DATGZ;

    /**
     * 郑投签章
     */
    @RequestMapping(value = "ztSign", method = RequestMethod.POST)
    @ApiOperation(value = "郑投签章")
    public NetVO ztSign(@RequestParam @ApiParam(value = "需签章文件地址：（pdf）", name = "fileUrl") String fileUrl, @RequestParam @ApiParam(value =
            "签章后文件输出地址：（pdf）",
            name = "outUrl") String outUrl, @RequestBody List<SignInfo> signInfoList) throws BaseException {
        if (fileUrl.equals(outUrl)) {
            return new SuccFessionVO(RtnResultEnum.E090002);
        }
        aztApi.companySign(DATGZ, fileUrl, outUrl, signInfoList);
        return new SuccFessionVO();
    }

    /**
     * 三方公司签章
     */
    @RequestMapping(value = "companySign", method = RequestMethod.POST)
    @ApiOperation(value = "三方公司签章")
    public NetVO companySign(@RequestParam @ApiParam(value = "三方公司证书地址", name = "datGzUrl") String datGzUrl, @RequestParam @ApiParam(value =
            "需签章文件地址：（pdf）", name = "fileUrl") String fileUrl, @RequestParam @ApiParam(value = "签章后文件输出地址：（pdf）", name = "outUrl") String outUrl,
                             @RequestBody List<SignInfo> signInfoList) throws BaseException {
        if (fileUrl.equals(outUrl)) {
            return new SuccFessionVO(RtnResultEnum.E090002);
        }
        aztApi.companySign(datGzUrl, fileUrl, outUrl, signInfoList);
        return new SuccFessionVO();
    }

    /**
     * 个人签章
     */
    @RequestMapping(value = "personSign", method = RequestMethod.POST)
    @ApiOperation(value = "个人签章")
    public NetVO personSign(@RequestParam @ApiParam(value = "姓名", name = "realName") String realName, @RequestParam @ApiParam(value = "身份证号", name
            = "idNo") String idNo, @RequestParam @ApiParam(value = "需签章文件地址：（pdf）", name = "fileUrl") String fileUrl, @RequestParam @ApiParam(value =
            "签章后文件输出地址：（pdf）", name = "outUrl") String outUrl, @RequestBody List<SignInfo> signInfoList) throws BaseException {
        if (fileUrl.equals(outUrl)) {
            return new SuccFessionVO(RtnResultEnum.E090002);
        }
        aztApi.personSign(realName, idNo, fileUrl, outUrl, signInfoList);
        return new SuccFessionVO();
    }

    /**
     * macdown 格式doc文件转pdf并替换字符
     */
    @RequestMapping(value = "macdownDocToPdf", method = RequestMethod.POST)
    @ApiOperation(value = "生成签章pdf")
    public NetVO macdownDocToPdf(@RequestParam @ApiParam(value = "macdown格式合同地址：（doc）", name = "macPath") String macPath, @RequestParam @ApiParam
            (value = "pdf输出地址", name = "pdfPath") String pdfPath, @RequestBody @ApiParam(value = "替换字段", name = "map") HashMap<String, String> map) throws
            BaseException {
        if (StringUtils.isBlank(macPath) || StringUtils.isBlank(pdfPath)) {
            return new SuccFessionVO(RtnResultEnum.E100002);
        }
        String htmlPath = macPath.substring(0, macPath.lastIndexOf(".")) + ".html";
        aztApi.macdownToHtml(macPath, htmlPath, map);
        aztApi.html2pdf(htmlPath, pdfPath);
        FileUtils.deleteQuietly(new File(htmlPath));
        return new SuccFessionVO();
    }
}
