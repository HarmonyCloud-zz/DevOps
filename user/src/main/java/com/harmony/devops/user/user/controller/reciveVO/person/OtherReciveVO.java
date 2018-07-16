package com.harmony.devops.user.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 其他信息
 */
@ApiModel(discriminator = "郑投-其他信息vo")
public class OtherReciveVO extends PeakBaseVo {
    public OtherReciveVO() {
    }
    @ApiModelProperty(value="信用卡卡号",name="creditCardno")
    private String creditCardno;
    @ApiModelProperty(value="工作证明类型",name="workProofType")
    private String workProofType;
    @ApiModelProperty(value="央行征信授权",name="centralBankCreditAuthFlag")
    private String centralBankCreditAuthFlag;
    @ApiModelProperty(value="社保授权",name="socialAuthFlag")
    private String socialAuthFlag;
    @ApiModelProperty(value="运营商授权",name="providersAuthFlag")
    private String providersAuthFlag;
    @ApiModelProperty(value="信用报告ID",name="creaditReportId")
    private String creaditReportId;
    @ApiModelProperty(value="产品市场ID",name="产品市场ID")
    private String marketProductId;
    @ApiModelProperty(value="电核标示",name="telcheckFlag")
    private String telcheckFlag;
    @ApiModelProperty(value="专员编码",name="commissionerCode")
    private String commissionerCode;
    @ApiModelProperty(value="认证口令",name="authenticationPassword")
    private String authenticationPassword;
    @ApiModelProperty(value="芝麻评分",name="zhimaScore")
    private String zhimaScore;
    @ApiModelProperty(value="芝麻评分图片（base64）",name="zhimaImg")
    private String zhimaImg;
    @ApiModelProperty(value="风险等级",name="riskLevel")
    private String riskLevel;
    @ApiModelProperty(value="保单号",name="guarantyId")
    private String guarantyId;
    @ApiModelProperty(value="申请地址（省）",name="applyAdressState")
    private String applyAdressState;
    @ApiModelProperty(value="申请地址（市）",name="applyAdressCity")
    private String applyAdressCity;

    public String getZhimaScore() {
        return zhimaScore;
    }

    public void setZhimaScore(String zhimaScore) {
        this.zhimaScore = zhimaScore;
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
