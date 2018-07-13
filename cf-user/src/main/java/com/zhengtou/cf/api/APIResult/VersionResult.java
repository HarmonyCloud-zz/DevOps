package com.zhengtou.cf.api.APIResult;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@ApiModel(discriminator = "郑投-android版本信息vo")
public class VersionResult extends PeakBaseVo {
    @ApiModelProperty(value="版本编号",name="versionCode")
    private String versionCode;
    @ApiModelProperty(value="版本名称",name="versionName")
    private String versionName;
    @ApiModelProperty(value="版本描述",name="desc")
    private String desc;
    @ApiModelProperty(value="下载地址",name="path")
    private String path;

    public VersionResult(String versionCode, String versionName,String desc, String path) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.desc=desc;
        this.path = path;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
