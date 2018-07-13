package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo;

/**
 * 同盾风控返回内容结构
 * @author 葛文镇
 */
public class ResultDescVO{
    //贷前反欺诈
    private AntifraudVO ANTIFRAUD;
    //信用分
    private CreditScoreVO CREDITSCORE;

    public AntifraudVO getANTIFRAUD() {
        return ANTIFRAUD;
    }

    public void setANTIFRAUD(AntifraudVO ANTIFRAUD) {
        this.ANTIFRAUD = ANTIFRAUD;
    }

    public CreditScoreVO getCREDITSCORE() {
        return CREDITSCORE;
    }

    public void setCREDITSCORE(CreditScoreVO CREDITSCORE) {
        this.CREDITSCORE = CREDITSCORE;
    }
}