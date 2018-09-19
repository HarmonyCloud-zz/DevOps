package com.harmony.devops.common.vo;

import com.harmony.devops.common.enums.ReturnMsgEnum;
import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.enums.RtnStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务层VO,无实体
 * @author 葛文镇
 */
@ApiModel(discriminator = "谐云-成功返回vo")
public class SuccFessionVO extends NetVO {
    @ApiModelProperty(value="业务状态码",name="status")
    private String status;
    @ApiModelProperty(value="业务状态描述",name="status")
    private String desc;
    //无状态返回
    public SuccFessionVO() {
        super(ReturnMsgEnum.SUCCESS);
        this.status= RtnStatusEnum.SUCCESS.name();
        this.desc=RtnStatusEnum.SUCCESS.getMsg();
    }

    //状态返回
    public SuccFessionVO(String status, String desc) {
        super(ReturnMsgEnum.SUCCESS);
        this.status = status;
        this.desc = desc;
    }

    public SuccFessionVO(RtnResultEnum rtnResultEnum) {
        super(ReturnMsgEnum.SUCCESS);
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
