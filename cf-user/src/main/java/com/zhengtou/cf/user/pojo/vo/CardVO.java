package com.zhengtou.cf.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.api.outer.cl.apply.enums.BankCodeEnum;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行卡信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-银行卡信息")
public class CardVO extends PeakBaseVo {
    //展示
    public CardVO(long cardId,String bankCardNo, String bankReservePhoneNumber, BankCodeEnum bankNameSub,Boolean isDefault,Boolean isRepayDefault) {
        this.cardId=cardId;
        this.bankCardNo = bankCardNo;
        this.bankReservePhoneNumber = bankReservePhoneNumber;
        this.bankNameSub = bankNameSub.name();
        this.isDefault=isDefault;
        this.isRepayDefault=isRepayDefault;
    }
    @ApiModelProperty(value="银行卡id",name="cardId")
    private Long cardId;
    //    银行行号枚举值参见《数据字典文档》
    @ApiModelProperty(value="银行行号",name="bankCode")
    private String bankCode;
    @ApiModelProperty(value="银行卡号",name="bankCardNo")
    private String bankCardNo;
    @ApiModelProperty(value="银行预留手机号",name="bankReservePhoneNumber")
    private String bankReservePhoneNumber;
    @ApiModelProperty(value="银行分行省code",name="bankProvinceCode")
    private String bankProvinceCode;
    @ApiModelProperty(value="银行分行市code",name="bankCityCode")
    private String bankCityCode;
    @ApiModelProperty(value="银行名称",name="bankNameSub")
    private String bankNameSub;
    //银行所在省名称    不带“省”或“自治区”，如 广东，广西，内蒙古等。
    @ApiModelProperty(value="银行所在省名称",name="bankProvinceName")
    private String bankProvinceName;
    //银行所在市名称    "不带“市”，如 广州，南宁等。
    @ApiModelProperty(value="银行所在市名称",name="bankCityName")
    private String bankCityName;
    //银行开户行名称    如果是直辖市，则填区，如北京 （市）朝阳（区）。"
    @ApiModelProperty(value="银行开户行名称",name="bankBranchName")
    private String bankBranchName;
    @ApiModelProperty(value="是否默认收款卡",name="isDefault")
    private Boolean isDefault;
    @ApiModelProperty(value="是否默认还款卡",name="isRepayDefault")
    private Boolean isRepayDefault;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankReservePhoneNumber() {
        return bankReservePhoneNumber;
    }

    public void setBankReservePhoneNumber(String bankReservePhoneNumber) {
        this.bankReservePhoneNumber = bankReservePhoneNumber;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public String getBankNameSub() {
        return bankNameSub;
    }

    public void setBankNameSub(String bankNameSub) {
        this.bankNameSub = bankNameSub;
    }

    public String getBankProvinceName() {
        return bankProvinceName;
    }

    public void setBankProvinceName(String bankProvinceName) {
        this.bankProvinceName = bankProvinceName;
    }

    public String getBankCityName() {
        return bankCityName;
    }

    public void setBankCityName(String bankCityName) {
        this.bankCityName = bankCityName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Boolean getIsRepayDefault() {
        return isRepayDefault;
    }

    public void setIsRepayDefault(Boolean repayDefault) {
        isRepayDefault = repayDefault;
    }
}
