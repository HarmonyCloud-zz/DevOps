package com.harmony.devops.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 统一返回实体（批量）
 */
@ApiModel(discriminator = "谐云-列表返回vo")
public class ListResponseVO<T> extends SuccFessionVO {
    /**
     * 返回数据量
     */
    @ApiModelProperty(value="返回数据量",name="return_count")
    public int return_count;
    /**
     * 数据总量
     */
    @ApiModelProperty(value="数据总量",name="total_count")
    public long total_count;

    public ListResponseVO(List<T> data) {
        super();
        this.data = data;
        this.return_count=data.isEmpty()?0:data.size();
    }

    public ListResponseVO(List<T> data,long total_count) {
        super();
        this.data = data;
        this.return_count=data.isEmpty()?0:data.size();
        this.total_count=total_count;
    }
    @ApiModelProperty(value="数据内容",name="data")
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
