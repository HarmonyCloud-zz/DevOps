package com.zhengtou.cf.jpush.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-用户通知信息vo")
public class MsgVO extends PeakBaseVo {
    public MsgVO(String title, String message,Long createTime) {
        this.message = title+message;
        this.createTime= TimeUtil.timeToString(createTime);
    }

    @ApiModelProperty(value="内容",name="message")
    private String message;
    @ApiModelProperty(value="创建时间",name="createTime")
    private String createTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
