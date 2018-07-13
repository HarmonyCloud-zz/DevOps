package com.zhengtou.cf.risk.service;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.risk.pojo.entity.RiskEntity;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface TdRiskService {
    /**
     * 保存td风控
     */
    void saveTdRisk(String name, String phone, String idNo, TDApiResponse tdApiResponse) throws BaseException;

    /**
     * 根据idno和风控通道查询风控数据
     */
    List<RiskEntity> fetchRiskEntityByIdNo(String idNo, String classType);

    /**
     * 获取同盾风控结果
     */

}
