package com.harmony.devops.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一返回实体
 */
@ApiModel(discriminator = "谐云-单实体返回vo")
public class ResponseVO<T> extends SuccFessionVO {
    public ResponseVO(String status, String desc,T data) {
        super(status, desc);
        this.data = data;
    }

    public ResponseVO(T data) {
        super();
        this.data = data;
    }

    @ApiModelProperty(value="数据内容",name="data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
