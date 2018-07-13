package com.zhengtou.cf.trade.service.impl;

import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.trade.service.AnnexService;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class AnnexServiceImpl implements AnnexService {
    @Autowired
    BaseDao dao;

    @Override
    public List<AnnexVO> getAllContractAnnex(OrganizationEntity organizationEntity) {
        return dao.find("select new com.zhengtou.cf.user.pojo.vo.AnnexVO(a.annexCode,a.annexTypeEnum,a.url,a.ztProductEnum,a.annexStatus) from " +
                "AnnexEntity a where a.isDeleted=false and a.org=?0", new Object[]{organizationEntity});
    }

    @Override
    public AnnexVO getConsumerUserHeadAnnex(long consumerId) {
        return dao.get("select new com.zhengtou.cf.user.pojo.vo.AnnexVO(a.annexTypeEnum,a.url) from AnnexEntity a where a.isDeleted=false and a" +
                ".user.id=?0 and a.annexTypeEnum=?1 order by a.createTime desc",new Object[]{consumerId, AnnexTypeEnum.头像});
    }
}
