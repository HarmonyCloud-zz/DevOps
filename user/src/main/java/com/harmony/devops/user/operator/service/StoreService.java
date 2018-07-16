package com.harmony.devops.user.operator.service;

import com.harmony.devops.user.operator.controller.vo.FetchStoreVO;
import com.harmony.devops.user.operator.pojo.vo.StoreVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface StoreService {
    /**
     * 组织列表分页查询
     */
    List<StoreVO> getStoreList(FetchStoreVO fetchStoreVO, Integer page, Integer size);
    /**
     * 组织记录数
     */
    long countStore(FetchStoreVO fetchStoreVO);
    /**
     * 根据id获取门店视图
     */
    StoreVO getStoreById(long storeId);
    /**
     *根据客户编号获取门店视图
     */
    List<StoreVO> getStoreByOrgId(long orgId, Integer page, Integer size);
    /**
     * 获取所有门店列表（app）
     */
    List<StoreVO> getStoreSelectList(long orgId);
}
