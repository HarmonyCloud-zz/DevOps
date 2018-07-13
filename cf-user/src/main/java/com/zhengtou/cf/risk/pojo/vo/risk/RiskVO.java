package com.zhengtou.cf.risk.pojo.vo.risk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalResultTypeEnum;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskVO extends PeakBaseVo{

    public RiskVO(Integer credit_score, List<RiskItemVO> riskItemVOS,ApprovalResultTypeEnum result) {
        this.credit_score = credit_score;
        this.riskItemVOS = riskItemVOS;
        this.result=result;
    }
    //同盾信用分
    private Integer credit_score;
    //命中风控规则
    private List<RiskItemVO> riskItemVOS;
    //风控结果
    private ApprovalResultTypeEnum result;

    public Integer getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(Integer credit_score) {
        this.credit_score = credit_score;
    }

    public List<RiskItemVO> getRiskItemVOS() {
        return riskItemVOS;
    }

    public void setRiskItemVOS(List<RiskItemVO> riskItemVOS) {
        this.riskItemVOS = riskItemVOS;
    }

    public ApprovalResultTypeEnum getResult() {
        return result;
    }

    public void setResult(ApprovalResultTypeEnum result) {
        this.result = result;
    }
}
