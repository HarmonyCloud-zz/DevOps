package com.zhengtou.cf.common.pojo.entity;

import com.zhengtou.cf.common.pojo.AbstractEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 数据库基类
 * @author 葛文镇
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity implements Serializable {
    /**
     * 自增数字类型id，主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * 创建时间
     */
    private long createTime = System.currentTimeMillis();
    /**
     * 最后修改时间
     */
    private long lastModifyTime = createTime;
    /**
     * 逻辑删除标志
     */
    private boolean isDeleted = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
