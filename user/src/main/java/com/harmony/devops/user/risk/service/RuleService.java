package com.harmony.devops.user.risk.service;


import com.harmony.devops.user.risk.pojo.vo.rule.RuleVO;

import java.util.List;

public interface RuleService {
    /**
     * 分页查询使用
     */
    List<RuleVO> getRuleList(RuleVO ruleVO, Integer page, Integer rows);

    long countRuleList(RuleVO ruleVO);

    /**
     * 风控标志位
     */
    List<RuleVO> getRuleList();
}
