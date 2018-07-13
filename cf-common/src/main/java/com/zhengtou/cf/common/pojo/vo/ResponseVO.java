package com.zhengtou.cf.common.pojo.vo;

import com.zhengtou.cf.common.pojo.AbstractEntity;

/**
 * 统一返回实体
 */
public class ResponseVO<T extends AbstractEntity> extends SuccFessionVO {
    public ResponseVO(String status, String desc,T data) {
        super(status, desc);
        this.data = data;
    }

    public ResponseVO(T data) {
        super();
        this.data = data;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
