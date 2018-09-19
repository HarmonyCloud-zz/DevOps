package com.harmony.devops.common.vo;

import com.harmony.devops.common.enums.ReturnMsgEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 网络通信层vo
 * @author 葛文镇
 */
@ApiModel(discriminator = "谐云-网络层返回vo")
public abstract class NetVO implements Serializable {
    @ApiModelProperty(value="网络层返回码",name="code")
    private String code;
    @ApiModelProperty(value="异常返回信息",name="message")
    private String message;

    public NetVO(ReturnMsgEnum returnMsgEnum) {
        this.code = returnMsgEnum.getCode();
        this.message = returnMsgEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
