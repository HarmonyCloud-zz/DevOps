package com.harmony.devops.user.risk.service.impl;


import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.risk.pojo.entity.rule.enums.ApprovalRuleStatusEnum;
import com.harmony.devops.user.risk.pojo.vo.rule.RuleVO;
import com.harmony.devops.user.risk.service.RuleService;
import com.harmony.devops.user.user.service.ConsumerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    BaseDao dao;
    @Autowired
    ConsumerUserService consumerUserService;

    public List<RuleVO> getRuleList(RuleVO ruleVO, Integer page, Integer rows) {
        StringBuffer hql = new StringBuffer("select new com.harmony.devops.user.risk.pojo.vo.rule.RuleVO(r.outRuleId,r.ruleName,r.approvalCompany,r" +
                ".ruleType,r.ruleStatus,r" +
                ".id) from RuleEntity r where r.isDeleted=false ");
        Map<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(ruleVO.getRuleName())) {
            hql.append("and r.ruleName=:ruleName ");
            where.put("ruleName", DBUtil.generateLikeSql(ruleVO.getRuleName()));
        }
        if (ruleVO.getApprovalCompany() != null) {
            hql.append("and r.approvalCompany=:approvalCompany ");
            where.put("approvalCompany", ruleVO.getApprovalCompany());
        }
        if (StringUtils.isNotBlank(ruleVO.getOutRuleId())) {
            hql.append("and r.outRuleId=:outRuleId ");
            where.put("outRuleId", ruleVO.getOutRuleId());
        }
        if (ruleVO.getRuleStatus() != null) {
            hql.append("and r.ruleStatus=:ruleStatus ");
            where.put("ruleStatus", ruleVO.getRuleStatus());
        }
        if (ruleVO.getRuleType() != null) {
            hql.append("and r.ruleType=:ruleType ");
            where.put("ruleType", ruleVO.getRuleType());
        }
        return dao.find(hql.toString(), where, page, rows);
    }

    @Override
    public long countRuleList(RuleVO ruleVO) {
        StringBuffer hql = new StringBuffer("select count(*) from RuleEntity r where r.isDeleted=false ");
        Map<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(ruleVO.getRuleName())) {
            hql.append("and r.ruleName=:ruleName ");
            where.put("ruleName", DBUtil.generateLikeSql(ruleVO.getRuleName()));
        }
        if (ruleVO.getApprovalCompany() != null) {
            hql.append("and r.approvalCompany=:approvalCompany ");
            where.put("approvalCompany", ruleVO.getApprovalCompany());
        }
        if (StringUtils.isNotBlank(ruleVO.getOutRuleId())) {
            hql.append("and r.outRuleId=:outRuleId ");
            where.put("outRuleId", ruleVO.getOutRuleId());
        }
        if (ruleVO.getRuleStatus() != null) {
            hql.append("and r.ruleStatus=:ruleStatus ");
            where.put("ruleStatus", ruleVO.getRuleStatus());
        }
        if (ruleVO.getRuleType() != null) {
            hql.append("and r.ruleType=:ruleType ");
            where.put("ruleType", ruleVO.getRuleType());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public List<RuleVO> getRuleList() {
        return dao.get("select new com.harmony.devops.user.risk.pojo.vo.rule.RuleVO(r.outRuleId,r.ruleType) from RuleEntity r where r.isDeleted=false and r" +
                ".ruleStatus=?0 ", new Object[]{ApprovalRuleStatusEnum.启用});
    }
}
