package com.harmony.devops.common.vo;

import com.harmony.devops.common.enums.ReturnMsgEnum;
import com.harmony.devops.common.enums.RtnResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务层VO,无实体
 * @author 葛文镇
 */
@ApiModel(discriminator = "谐云-错误返回vo")
public class ErrorFessionVO extends NetVO {
    @ApiModelProperty(value="业务状态码",name="status")
    private String status;
    @ApiModelProperty(value="业务状态描述",name="desc")
    private String desc;

    public ErrorFessionVO() {
        super(ReturnMsgEnum.ERROR);
    }

    public ErrorFessionVO(String status, String desc) {
        super(ReturnMsgEnum.ERROR);
        this.status = status;
        this.desc = desc;
    }

    public ErrorFessionVO(RtnResultEnum rtnResultEnum) {
        super(ReturnMsgEnum.ERROR);
        this.status = rtnResultEnum.name();
        this.desc = rtnResultEnum.getMsg();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
