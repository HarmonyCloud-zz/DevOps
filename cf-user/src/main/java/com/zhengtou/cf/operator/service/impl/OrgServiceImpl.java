package com.zhengtou.cf.operator.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.operator.controller.vo.FetchOrganizaVO;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.vo.OrganizaVO;
import com.zhengtou.cf.operator.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    BaseDao dao;

    @Override
    public List<OrganizaVO> getOrganizaList(FetchOrganizaVO fetchOrganizaVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.operator.pojo.vo.OrganizaVO(o.id,o.name,o.principal,o.phone,o.descript,o" +
                ".type,o" +
                ".address,o.status,o.orgNo) from OrganizationEntity o where o.isDeleted=false and o.class<>'NullOrganizationEntity' ");
        Map<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(fetchOrganizaVO.getName())) {
            hql.append("and o.name like :name ");
            where.put("name", DBUtil.generateLikeSql(fetchOrganizaVO.getName()));
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getPrincipal())) {
            hql.append("and o.principal like :principal ");
            where.put("principal", DBUtil.generateLikeSql(fetchOrganizaVO.getPrincipal()));
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getAddress())) {
            hql.append("and o.address=:address ");
            where.put("address", fetchOrganizaVO.getAddress());
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getOrgNo())) {
            hql.append("and o.orgNo=:orgNo ");
            where.put("orgNo", fetchOrganizaVO.getOrgNo());
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getPhone())) {
            hql.append("and o.phone=:phone ");
            where.put("phone", fetchOrganizaVO.getPhone());
        }
        if (fetchOrganizaVO.getStatus() != null) {
            hql.append("and o.status=:status ");
            where.put("status", fetchOrganizaVO.getStatus());
        }
        if (fetchOrganizaVO.getType() != null) {
            hql.append("and o.type=:type ");
            where.put("type", fetchOrganizaVO.getType());
        }
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public long countOrganiza(FetchOrganizaVO fetchOrganizaVO) {
        StringBuffer hql = new StringBuffer("select count(*) from OrganizationEntity o where o.isDeleted=false and o" +
                ".class<>'NullOrganizationEntity' ");
        Map<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(fetchOrganizaVO.getName())) {
            hql.append("and o.name=:name ");
            where.put("name", fetchOrganizaVO.getName());
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getAddress())) {
            hql.append("and o.address=:address ");
            where.put("address", fetchOrganizaVO.getAddress());
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getOrgNo())) {
            hql.append("and o.orgNo=:orgNo ");
            where.put("orgNo", fetchOrganizaVO.getOrgNo());
        }
        if (StringUtils.isNotBlank(fetchOrganizaVO.getPhone())) {
            hql.append("and o.phone=:phone ");
            where.put("phone", fetchOrganizaVO.getPhone());
        }
        if (fetchOrganizaVO.getStatus() != null) {
            hql.append("and o.status=:status ");
            where.put("status", fetchOrganizaVO.getStatus());
        }
        if (fetchOrganizaVO.getType() != null) {
            hql.append("and o.type=:type ");
            where.put("type", fetchOrganizaVO.getType());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public OrganizaVO getOrganizaById(long orgId) {
        return dao.get("select new com.zhengtou.cf.operator.pojo.vo.OrganizaVO(o.id,o.name,o.principal,o.phone,o.descript,o.type,o" +
                ".address,o.status,o.orgNo) from OrganizationEntity o where o.isDeleted=false and o.id=?0", new Object[]{orgId});
    }

    @Override
    public List<OrganizaVO> getOrganizaSelectList() {
        return dao.find("select new com.zhengtou.cf.operator.pojo.vo.OrganizaVO(o.id,o.name) from OrganizationEntity o where o.isDeleted=false and " +
                "o.class<>'NullOrganizationEntity' ");
    }

    @Override
    public OrganizationEntity getOrganizaByOrgNo(String orgNo) {
        return dao.get("from OrganizationEntity o where o.isDeleted=false and o.orgNo=?0",new Object[]{orgNo});
    }
}
