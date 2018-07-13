package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;

import java.util.List;

/**
 * 附件服务
 * @author 葛文镇
 */
public interface AnnexService {
    /**
     * 查看合同模板
     */
    List<AnnexVO> getAllContractAnnex(OrganizationEntity organizationEntity);
    /**
     * 查看用户头像附件
     */
    AnnexVO getConsumerUserHeadAnnex(long consumerId);
}
