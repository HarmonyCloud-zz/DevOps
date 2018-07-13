package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo;

import java.util.List;

/**
 * 贷前反欺诈返回结构
 * @author 葛文镇
 */
public class AntifraudVO{
    private  Integer final_score;
    private String final_decision;
    private List<RiskItem> risk_items;

    public Integer getFinal_score() {
        return final_score;
    }

    public void setFinal_score(Integer final_score) {
        this.final_score = final_score;
    }

    public String getFinal_decision() {
        return final_decision;
    }

    public void setFinal_decision(String final_decision) {
        this.final_decision = final_decision;
    }

    public List<RiskItem> getRisk_items() {
        return risk_items;
    }

    public void setRisk_items(List<RiskItem> risk_items) {
        this.risk_items = risk_items;
    }

    /**
     * 规则项返回结构
     */
//    public class RiskItem{
//        private String rule_id;
//        private Integer score;
//        private String decision;
//        private String risk_name;
//
//        public String getRule_id() {
//            return rule_id;
//        }
//
//        public void setRule_id(String rule_id) {
//            this.rule_id = rule_id;
//        }
//
//        public Integer getScore() {
//            return score;
//        }
//
//        public void setScore(Integer score) {
//            this.score = score;
//        }
//
//        public String getDecision() {
//            return decision;
//        }
//
//        public void setDecision(String decision) {
//            this.decision = decision;
//        }
//
//        public String getRisk_name() {
//            return risk_name;
//        }
//
//        public void setRisk_name(String risk_name) {
//            this.risk_name = risk_name;
//        }
//    }
}
