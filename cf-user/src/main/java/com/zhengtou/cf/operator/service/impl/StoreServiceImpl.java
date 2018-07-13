package com.zhengtou.cf.operator.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.operator.controller.vo.FetchStoreVO;
import com.zhengtou.cf.operator.pojo.vo.StoreVO;
import com.zhengtou.cf.operator.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    BaseDao dao;

    @Override
    public List<StoreVO> getStoreList(FetchStoreVO fetchStoreVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.operator.pojo.vo.StoreVO(o.id,o.storeName,o.telephone,o.contactName,o" +
                ".address,o.status,o.storeNo) from StoreEntity o where o.isDeleted=false and o.class<>'NullStoreEntity' ");
        Map<String, Object> where = new HashMap<>();
        if (fetchStoreVO.getOrgId()!=null) {
            hql.append("and o.organiza.id=:orgId ");
            where.put("orgId", fetchStoreVO.getOrgId());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getStoreName())) {
            hql.append("and o.storeName like :storeName ");
            where.put("storeName", DBUtil.generateLikeSql(fetchStoreVO.getStoreName()));
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getTelephone())) {
            hql.append("and o.telephone=:telephone ");
            where.put("telephone", fetchStoreVO.getTelephone());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getContactName())) {
            hql.append("and o.contactName=:contactName ");
            where.put("contactName", fetchStoreVO.getContactName());
        }
        if (fetchStoreVO.getStatus() != null) {
            hql.append("and o.status=:status ");
            where.put("status", fetchStoreVO.getStatus());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getAddress())) {
            hql.append("and o.address=:address ");
            where.put("address", fetchStoreVO.getAddress());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getStoreNo())) {
            hql.append("and o.storeNo=:storeNo ");
            where.put("storeNo", fetchStoreVO.getStoreNo());
        }
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public long countStore(FetchStoreVO fetchStoreVO) {
        StringBuffer hql = new StringBuffer("select count(*) from StoreEntity o where o.isDeleted=false and o.class<>'NullStoreEntity' ");
        Map<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(fetchStoreVO.getStoreName())) {
            hql.append("and o.address=:storeName ");
            where.put("storeName", fetchStoreVO.getStoreName());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getTelephone())) {
            hql.append("and o.telephone=:telephone ");
            where.put("telephone", fetchStoreVO.getTelephone());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getContactName())) {
            hql.append("and o.contactName=:contactName ");
            where.put("contactName", fetchStoreVO.getContactName());
        }
        if (fetchStoreVO.getStatus() != null) {
            hql.append("and o.status=:status ");
            where.put("status", fetchStoreVO.getStatus());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getAddress())) {
            hql.append("and o.address=:address ");
            where.put("address", fetchStoreVO.getAddress());
        }
        if (StringUtils.isNotBlank(fetchStoreVO.getStoreNo())) {
            hql.append("and o.storeNo=:storeNo ");
            where.put("storeNo", fetchStoreVO.getStoreNo());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public StoreVO getStoreById(long storeId) {
        return dao.get("select new com.zhengtou.cf.operator.pojo.vo.StoreVO(o.id,o.storeName,o.telephone,o.contactName,o.address,o" +
                ".status,o.storeNo) from StoreEntity o where o.isDeleted=false and o.id=?0", new Object[]{storeId});
    }

    @Override
    public List<StoreVO> getStoreByOrgId(long orgId, Integer page, Integer size) {
        return dao.find("select new com.zhengtou.cf.operator.pojo.vo.StoreVO(o.id,o.storeName,o.telephone,o.contactName,o.address,o" +
                ".status,o.storeNo) from StoreEntity o where o.isDeleted=false and o.organiza.id=?0", new Object[]{orgId}, page, size);
    }

    @Override
    public List<StoreVO> getStoreSelectList(long orgId) {
        return dao.find("select new com.zhengtou.cf.operator.pojo.vo.StoreVO(o.id,o.storeName) from StoreEntity o where o.isDeleted=false and o" +
                ".class<>'NullStoreEntity' and o.organiza.id=?0", new Object[]{orgId});
    }
}
