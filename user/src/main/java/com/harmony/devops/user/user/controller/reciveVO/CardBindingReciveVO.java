package com.harmony.devops.user.user.controller.reciveVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 葛文镇
 * 绑卡vo
 */
@ApiModel(discriminator = "郑投-绑卡请求vo")
public class CardBindingReciveVO {
    public CardBindingReciveVO() {
    }
    @ApiModelProperty(value="卡号，（不可为空）",name="cardNo")
    private String cardNo;
    @ApiModelProperty(value="银行名称,（不可为空）",name="bankName")
    private String bankName;
    @ApiModelProperty(value="预留手机号，（不可为空）",name="phone")
    private String phone;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

