package com.harmony.devops.user.operator.service;

import com.harmony.devops.user.operator.controller.vo.FetchOrganizaVO;
import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.operator.pojo.vo.OrganizaVO;

import java.util.List;

/**
 * 组织service
 * @author 葛文镇
 */
public interface OrgService {
    /**
     * 组织列表分页查询
     */
    List<OrganizaVO> getOrganizaList(FetchOrganizaVO fetchOrganizaVO, Integer page, Integer size);
    /**
     * 组织记录数
     */
    long countOrganiza(FetchOrganizaVO fetchOrganizaVO);

    /**
     * 根据id获取组织视图
     */
    OrganizaVO getOrganizaById(long orgId);

    /**
     * 获取所有组织列表（app）
     */
    List<OrganizaVO> getOrganizaSelectList();

    /**
     * 获取组织entity
     */
    OrganizationEntity getOrganizaByOrgNo(String orgNo);

}
