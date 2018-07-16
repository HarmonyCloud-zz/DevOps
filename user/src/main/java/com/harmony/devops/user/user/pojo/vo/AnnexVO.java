package com.harmony.devops.user.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.enums.ZtProductEnum;
import com.harmony.devops.user.user.pojo.entity.enums.AnnexStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 附件
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-贷款附件vo")
public class AnnexVO extends PeakBaseVo{
    @ApiModelProperty(value="附件编码",name="annexCode")
    private String annexCode;
    @ApiModelProperty(value="附件地址",name="url")
    private String url;
    @ApiModelProperty(value="对应产品类型",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="合同状态： 启用,停用;",name="annexStatusEnum")
    private AnnexStatusEnum annexStatusEnum;

    public String getAnnexCode() {
        return annexCode;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public AnnexStatusEnum getAnnexStatusEnum() {
        return annexStatusEnum;
    }

    public void setAnnexStatusEnum(AnnexStatusEnum annexStatusEnum) {
        this.annexStatusEnum = annexStatusEnum;
    }
}
