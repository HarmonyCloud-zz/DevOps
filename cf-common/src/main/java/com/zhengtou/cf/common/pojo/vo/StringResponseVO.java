package com.zhengtou.cf.common.pojo.vo;

/**
 * 统一返回实体
 */
public class StringResponseVO extends SuccFessionVO {

    public StringResponseVO(String data) {
        super();
        this.data = data;
    }

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
