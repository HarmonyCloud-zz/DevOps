package com.harmony.devops.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 数据库基类
 * @author 葛文镇
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     * 自增数字类型id，主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    /**
     * 创建时间
     */
    protected long createTime = System.currentTimeMillis();
    /**
     * 最后修改时间
     */
    protected long lastModifyTime = createTime;
    /**
     * 逻辑删除标志
     */
    protected boolean isDeleted = false;
    /**
     * 版本号
     */
    @Version
    protected Long version;

}
