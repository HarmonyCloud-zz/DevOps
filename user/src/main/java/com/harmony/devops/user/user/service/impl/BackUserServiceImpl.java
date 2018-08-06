package com.harmony.devops.user.user.service.impl;

import com.harmony.devops.common.pojo.dao.BaseDao;
import com.harmony.devops.common.utils.DBUtil;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.user.controller.reciveVO.BackUserReciveVO;
import com.harmony.devops.user.user.pojo.vo.BackUserVO;
import com.harmony.devops.user.user.service.BackUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class BackUserServiceImpl implements BackUserService {
    @Autowired
    BaseDao dao;

    @Override
    public BackUserVO getBackUserByUserId(long userId) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.BackUserVO(b.id,b.userNo,b.createTime,b.nickName,b.phone,b.userType,b.organiza" +
                ".name,b.store.storeName) from BackUserEntity b where b.isDeleted=false and b.id=?0", new Object[]{userId});
    }

    @Override
    public BackUserVO getBackUserByLoginNameAndPwd(String loginName, String passwd) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.BackUserVO(b.id,b.userNo,b.createTime,b.nickName,b.phone,b.userType,b.organiza.id,b" +
                ".store.id) from BackUserEntity b where b.isDeleted=false and b.loginName=?0 and b.password=?1", new Object[]{loginName, passwd});
    }

    @Override
    public List<BackUserVO> getBackUserList(BackUserReciveVO backUserReciveVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.harmony.devops.user.user.pojo.vo.BackUserVO(b.id,b.userNo,b.createTime,b.nickName,b.phone,b" +
                ".userType,b.organiza.name,b.store.storeName) from BackUserEntity b where b.isDeleted=false and b.class='BackUserEntity' ");
        HashMap<String, Object> where = new HashMap<>();
        if (backUserReciveVO.getOrgId() != null) {
            hql.append("and b.organiza.id=:orgId ");
            where.put("orgId", backUserReciveVO.getOrgId());
        }
        if (backUserReciveVO.getStoreId() != null) {
            hql.append("and b.store.id=:storeId ");
            where.put("storeId", backUserReciveVO.getStoreId());
        }
        if (StringUtils.isNotBlank(backUserReciveVO.getPhone())) {
            hql.append(" and b.phone=:phone ");
            where.put("phone", backUserReciveVO.getPhone());
        }
        if (StringUtils.isNotBlank(backUserReciveVO.getNickName())) {
            hql.append(" and b.nickName like :nickName ");
            where.put("nickName", DBUtil.generateLikeSql(backUserReciveVO.getNickName()));
        }
        if (backUserReciveVO.getUserType()!=null) {
            hql.append(" and b.userType=:userType ");
            where.put("userType", backUserReciveVO.getUserType());
        }
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public Long countBackUser(BackUserReciveVO backUserReciveVO) {
        StringBuffer hql = new StringBuffer("select count(*) from BackUserEntity b where b.isDeleted=false and b.class='BackUserEntity' ");
        HashMap<String, Object> where = new HashMap<>();
        if (backUserReciveVO.getOrgId() != null) {
            hql.append("and b.organiza.id=:orgId ");
            where.put("orgId", backUserReciveVO.getOrgId());
        }
        if (backUserReciveVO.getStoreId() != null) {
            hql.append("and b.store.id=:storeId ");
            where.put("storeId", backUserReciveVO.getStoreId());
        }
        if (StringUtils.isNotBlank(backUserReciveVO.getPhone())) {
            hql.append(" and b.phone=:phone ");
            where.put("phone", backUserReciveVO.getPhone());
        }
        if (StringUtils.isNotBlank(backUserReciveVO.getNickName())) {
            hql.append(" and b.nickName like :nickName ");
            where.put("nickName", DBUtil.generateLikeSql(backUserReciveVO.getNickName()));
        }
        if (backUserReciveVO.getUserType()!=null) {
            hql.append(" and b.userType=:userType ");
            where.put("userType", backUserReciveVO.getUserType());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public List<BackUserVO> getAllBackUser(Long orgId, Long storeId) {
        StringBuffer hql = new StringBuffer("select new com.harmony.devops.user.user.pojo.vo.BackUserVO(b.id,b.userNo,b.createTime,b.nickName,b.phone,b" +
                ".userType,b.organiza.name,b.store.storeName) from BackUserEntity b where b.isDeleted=false and b.class='BackUserEntity' ");
        HashMap<String, Object> where = new HashMap<>();
        if (orgId != null) {
            hql.append("and b.organiza.id=:orgId ");
            where.put("orgId", orgId);
        }
        if (orgId != null) {
            hql.append("and b.store.id=:storeId ");
            where.put("storeId", storeId);
        }
        return dao.find(hql.toString(), where);
    }
}
