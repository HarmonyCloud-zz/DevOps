package com.harmony.devops.user.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_person_other")
public class OtherEntity extends BaseEntity {
    @OneToOne
    private PersonalEntity personal;
    //信用卡卡号
    private String creditCardno;
    //工作证明类型
    private String workProofType;
    //央行征信授权
    private String centralBankCreditAuthFlag;
    //社保授权
    private String socialAuthFlag;
    //运营商授权
    private String providersAuthFlag;
    //信用报告ID
    private String creaditReportId;
    //产品市场ID
    private String marketProductId;
    //电核标示
    private String telcheckFlag;
    //专员编码
    private String commissionerCode;
    //认证口令
    private String authenticationPassword;
    //芝麻评分
    private String zhimaScore;
    //芝麻评分图片（base64）
    private String zhimaImg;
    //风险等级
    private String riskLevel;
    //保单号
    private String guarantyId;
    //申请地址（省code)
    private String applyAdressState;
    //申请地址（市code）
    private String applyAdressCity;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
    }

    public String getCreditCardno() {
        return creditCardno;
    }

    public void setCreditCardno(String creditCardno) {
        this.creditCardno = creditCardno;
    }

    public String getWorkProofType() {
        return workProofType;
    }

    public void setWorkProofType(String workProofType) {
        this.workProofType = workProofType;
    }

    public String getCentralBankCreditAuthFlag() {
        return centralBankCreditAuthFlag;
    }

    public void setCentralBankCreditAuthFlag(String centralBankCreditAuthFlag) {
        this.centralBankCreditAuthFlag = centralBankCreditAuthFlag;
    }

    public String getSocialAuthFlag() {
        return socialAuthFlag;
    }

    public void setSocialAuthFlag(String socialAuthFlag) {
        this.socialAuthFlag = socialAuthFlag;
    }

    public String getProvidersAuthFlag() {
        return providersAuthFlag;
    }

    public void setProvidersAuthFlag(String providersAuthFlag) {
        this.providersAuthFlag = providersAuthFlag;
    }

    public String getCreaditReportId() {
        return creaditReportId;
    }

    public void setCreaditReportId(String creaditReportId) {
        this.creaditReportId = creaditReportId;
    }

    public String getMarketProductId() {
        return marketProductId;
    }

    public void setMarketProductId(String marketProductId) {
        this.marketProductId = marketProductId;
    }

    public String getTelcheckFlag() {
        return telcheckFlag;
    }

    public void setTelcheckFlag(String telcheckFlag) {
        this.telcheckFlag = telcheckFlag;
    }

    public String getCommissionerCode() {
        return commissionerCode;
    }

    public void setCommissionerCode(String commissionerCode) {
        this.commissionerCode = commissionerCode;
    }

    public String getAuthenticationPassword() {
        return authenticationPassword;
    }

    public void setAuthenticationPassword(String authenticationPassword) {
        this.authenticationPassword = authenticationPassword;
    }

    public String getZhimaScore() {
        return zhimaScore;
    }

    public void setZhimaScore(String zhimaScore) {
        this.zhimaScore = zhimaScore;
    }

    public String getZhimaImg() {
        return zhimaImg;
    }

    public void setZhimaImg(String zhimaImg) {
        this.zhimaImg = zhimaImg;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getGuarantyId() {
        return guarantyId;
    }

    public void setGuarantyId(String guarantyId) {
        this.guarantyId = guarantyId;
    }

    public String getApplyAdressState() {
        return applyAdressState;
    }

    public void setApplyAdressState(String applyAdressState) {
        this.applyAdressState = applyAdressState;
    }

    public String getApplyAdressCity() {
        return applyAdressCity;
    }

    public void setApplyAdressCity(String applyAdressCity) {
        this.applyAdressCity = applyAdressCity;
    }
}
