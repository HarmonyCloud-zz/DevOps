package com.harmony.devops.common.pojo.dao;

import com.harmony.devops.common.pojo.AbstractEntity;
import com.harmony.devops.common.pojo.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 数据库操作基类
 *
 * @author 葛文镇
 */
public interface BaseDao{
    /**
     * 保存一个对象
     *
     * @param o
     * @return
     */
    public <T extends BaseEntity> void save(T o);

    /**
     * 逻辑删除一个对象
     *
     * @param o
     */
    public <T extends BaseEntity> void delete(T o);

    /**
     * 物理删除一个对象
     *
     * @param o
     */
    public <T extends BaseEntity> void deleteForce(T o);

    /**
     * 更新一个对象
     *
     * @param o
     */
    public <T extends BaseEntity> void modify(T o);

    /**
     * 保存或更新对象
     *
     * @param o
     */
    public <T extends BaseEntity> void saveOrModify(T o);

    /**
     * 查询
     *
     * @param hql
     * @return
     */
    public <T extends AbstractEntity> List<T> find(String hql);

    /**
     * 查询集合
     *
     * @param hql
     * @param param
     * @return
     */
    public <T extends AbstractEntity> List<T> find(String hql, Object[] param);

    /**
     * 查询集合
     *
     * @param hql
     * @param param
     * @return
     */
    public <T extends AbstractEntity> List<T> find(String hql, Map<String,Object> param);

    /**
     * 查询集合(带分页)
     *
     * @param hql
     * @param param
     * @param page
     *            查询第几页
     * @param rows
     *            每页显示几条记录
     * @return
     */
    public <T extends AbstractEntity> List<T> find(String hql, Object[] param, Integer page, Integer rows);

    /**
     * 查询集合(带分页)
     *
     * @param hql
     * @param param
     * @param page
     * @param rows
     * @return
     */
    public <T extends AbstractEntity> List<T> find(String hql, Map<String,Object> param, Integer page, Integer rows);

    /**
     * 查询并锁定
     *
     * @param hql
     * @return
     */
    public <T extends AbstractEntity> List<T> findAndLock(String hql);

    /**
     * 查询集合并锁定
     *
     * @param hql
     * @param param
     * @return
     */
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Object[] param);

    /**
     * 查询集合并锁定
     *
     * @param hql
     * @param param
     * @return
     */
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Map<String,Object> param);

    /**
     * 查询集合并锁定(带分页)
     *
     * @param hql
     * @param param
     * @param page
     *            查询第几页
     * @param rows
     *            每页显示几条记录
     * @return
     */
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Object[] param, Integer page, Integer rows);

    /**
     * 查询集合并锁定(带分页)
     *
     * @param hql
     * @param param
     * @param page
     * @param rows
     * @return
     */
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Map<String,Object> param, Integer page, Integer rows);

    /**
     * 获得一个对象
     *
     * @param c
     *            对象类型
     * @param id
     * @return Object
     */
    public <T extends AbstractEntity> T get(Class<T> c, Serializable id);

    /**
     * 获得一个对象
     *
     * @param hql
     * @param param
     * @return Object
     */
    public <T extends AbstractEntity> T get(String hql, Object[] param);

    /**
     * 获得一个对象
     *
     * @param hql
     * @param param
     * @return
     */
    public <T extends AbstractEntity> T get(String hql, Map<String,Object> param);

    /**
     * 获得一个对象并锁定
     */
    public <T extends AbstractEntity> T getAndLock(Class<T> c, Serializable id);

    /**
     * 获得一个对象并锁定
     */
    public <T extends AbstractEntity> T getAndLock(String hql, Object[] param);

    /**
     * 获得一个对象并锁定
     */
    public <T extends AbstractEntity> T getAndLock(String hql, Map<String,Object> param);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @return
     */
    public Long count(String hql);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @param param
     * @return
     */
    public Long count(String hql, Object[] param);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @param param
     * @return
     */
    public Long count(String hql, Map<String,Object> param);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @return
     */
    public Long countAndLock(String hql);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @param param
     * @return
     */
    public Long countAndLock(String hql, Object[] param);

    /**
     * select count(*) from 类
     *
     * @param hql
     * @param param
     * @return
     */
    public Long countAndLock(String hql, Map<String,Object> param);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @return 响应数目
     */
    public Integer executeHql(String hql);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @param param
     * @return 响应数目
     */
    public Integer executeHql(String hql, Object[] param);

    /**
     * 执行HQL语句
     *
     * @param hql
     * @param param
     * @return
     */
    public Integer executeHql(String hql, Map<String,Object> param);
}
