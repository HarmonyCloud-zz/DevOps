package com.harmony.devops.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一返回实体
 */
@ApiModel(discriminator = "谐云-字符串返回vo")
public class StringResponseVO extends SuccFessionVO {

    public StringResponseVO(String data) {
        super();
        this.data = data;
    }

    @ApiModelProperty(value="数据内容",name="data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
