package com.harmony.devops.common.pojo.vo;

/**
 * 分页查询vo
 * @author 葛文镇
 */
public abstract class FetchListVO {
    private int page;
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
