package com.harmony.devops.common.pojo.dao.impl;

import com.harmony.devops.common.pojo.AbstractEntity;
import com.harmony.devops.common.pojo.dao.BaseDao;
import com.harmony.devops.common.pojo.entity.BaseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public <T extends BaseEntity>  void save(T o) {
        this.entityManager.persist(o);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> void delete(T o) {
        o.isDeleted=true;
        this.entityManager.merge(o);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> void modify(T o) {
        this.entityManager.merge(o);
    }

    @Override
    @Transactional
    public <T extends BaseEntity> void saveOrModify(T o) {
        this.entityManager.merge(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> find(String hql) {
        return this.entityManager.createQuery(hql).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> find(String hql, Object[] param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> find(String hql, Map<String,Object> param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> find(String hql, Map<String,Object> param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> findAndLock(String hql) {
        return this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE ).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Object[] param) {
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE );
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Map<String,Object> param) {
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE );
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Object[] param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE );
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> List<T> findAndLock(String hql, Map<String,Object> param, Integer page, Integer rows) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE );
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> T get(Class<T> c, Serializable id) {
        return (T) this.entityManager.find(c, id);
    }

    @Override
    public <T extends AbstractEntity> T get(String hql, Object[] param) {
        List<T> l = this.find(hql, param, 1, 1);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T extends AbstractEntity> T get(String hql, Map<String,Object> param) {
        List<T> l = this.find(hql, param, 1, 1);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractEntity> T getAndLock(Class<T> c, Serializable id) {
        return (T) this.entityManager.find(c, id, LockModeType.PESSIMISTIC_WRITE);
    }

    @Override
    public <T extends AbstractEntity> T getAndLock(String hql, Object[] param) {
        List<T> l = this.findAndLock(hql, param, 1, 1);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T extends AbstractEntity> T getAndLock(String hql, Map<String,Object> param) {
        List<T> l = this.findAndLock(hql, param, 1, 1);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Long count(String hql) {
        return new Long((long) this.entityManager.createQuery(hql).getResultList().get(0));
    }

    @Override
    public Long count(String hql, Object[] param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return new Long((long) q.getResultList().get(0));
    }

    @Override
    public Long count(String hql, Map<String,Object> param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return new Long((long) q.getResultList().get(0));
    }

    @Override
    public Long countAndLock(String hql) {
        return new Long((long)this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE).getResultList().get(0));
    }

    @Override
    public Long countAndLock(String hql, Object[] param) {
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return new Long((long) q.getResultList().get(0));
    }

    @Override
    public Long countAndLock(String hql, Map<String,Object> param) {
        Query q = this.entityManager.createQuery(hql).setLockMode(LockModeType.PESSIMISTIC_WRITE);
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return new Long((long) q.getResultList().get(0));
    }

    @Override
    @Transactional
    public Integer executeHql(String hql) {
        return this.entityManager.createQuery(hql).executeUpdate();
    }

    @Override
    @Transactional
    public Integer executeHql(String hql, Object[] param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }

    @Override
    public Integer executeHql(String hql, Map<String,Object> param) {
        Query q = this.entityManager.createQuery(hql);
        if (param != null && param.keySet().size() > 0) {
            for (String key:param.keySet()) {
                q.setParameter(key, param.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    @Transactional
    public <T extends BaseEntity> void deleteForce(T o) {
        this.entityManager.remove(o);
    }

}
