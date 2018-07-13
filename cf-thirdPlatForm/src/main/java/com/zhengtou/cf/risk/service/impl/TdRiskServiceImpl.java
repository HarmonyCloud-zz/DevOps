package com.zhengtou.cf.risk.service.impl;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.AntifraudVO;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.CreditScoreVO;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.RiskItem;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.risk.pojo.entity.AntifraudEntity;
import com.zhengtou.cf.risk.pojo.entity.RiskEntity;
import com.zhengtou.cf.risk.pojo.entity.TdRiskEntity;
import com.zhengtou.cf.risk.service.TdRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class TdRiskServiceImpl implements TdRiskService {
    @Autowired
    BaseDao dao;

    @Override
    public void saveTdRisk(String name, String phone, String idNo, TDApiResponse tdApiResponse) throws BaseException {
        if (tdApiResponse == null) {
            throw new BaseException(RtnResultEnum.E130000);
        }
        AntifraudVO antifraudVO = tdApiResponse.getResult_desc().getANTIFRAUD();
        CreditScoreVO creditScoreVO = tdApiResponse.getResult_desc().getCREDITSCORE();
        TdRiskEntity tdRiskEntity = new TdRiskEntity(name, phone, idNo, System.currentTimeMillis(), tdApiResponse
                .getReason_desc(), tdApiResponse.getReason_code(), antifraudVO.getFinal_decision(), antifraudVO.getFinal_score(), creditScoreVO
                .getCredit_score(), creditScoreVO.getDecision());
        dao.save(tdRiskEntity);
        List<RiskItem> riskItems = antifraudVO.getRisk_items();
        if (riskItems != null && !riskItems.isEmpty()) {
            for (RiskItem riskItem : riskItems) {
                AntifraudEntity antifraudEntity = new AntifraudEntity(idNo, riskItem.getRule_id(), riskItem.getScore(), riskItem.getDecision(),
                        riskItem.getRisk_name());
                dao.save(antifraudEntity);
            }
        }
    }

    @Override
    public List<RiskEntity> fetchRiskEntityByIdNo(String idNo, String classType) {
        return dao.get("from RiskEntity r where r.idNo=?0 and r.class=?1", new Object[]{idNo, classType});
    }
}
