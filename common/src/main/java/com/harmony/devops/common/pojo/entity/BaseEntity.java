package com.harmony.devops.common.pojo.entity;

import com.harmony.devops.common.pojo.AbstractEntity;

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
    public long id;
    /**
     * 创建时间
     */
    public long createTime = System.currentTimeMillis();
    /**
     * 最后修改时间
     */
    public long lastModifyTime = createTime;
    /**
     * 逻辑删除标志
     */
    public boolean isDeleted = false;

}
