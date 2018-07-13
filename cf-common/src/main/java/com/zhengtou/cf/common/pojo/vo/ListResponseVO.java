package com.zhengtou.cf.common.pojo.vo;

import com.zhengtou.cf.common.pojo.AbstractEntity;

import java.util.List;

/**
 * 统一返回实体（批量）
 */
public class ListResponseVO<T extends AbstractEntity> extends SuccFessionVO {
    /**
     * 返回数据量
     */
    public int return_count;
    /**
     * 数据总量
     */
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

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
