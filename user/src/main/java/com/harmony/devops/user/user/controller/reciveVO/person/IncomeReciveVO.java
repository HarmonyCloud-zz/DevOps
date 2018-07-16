package com.harmony.devops.user.user.controller.reciveVO.person;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收支信息
 */
@ApiModel(discriminator = "郑投-收支信息vo")
public class IncomeReciveVO extends PeakBaseVo {
    public IncomeReciveVO() {
    }
    @ApiModelProperty(value="月收入",name="monthIncome")
    private Double monthIncome;
    @ApiModelProperty(value="其他收入",name="otherIncome")
    private Double otherIncome;
    @ApiModelProperty(value="年收入",name="yearIncome")
    private Double yearIncome;
    @ApiModelProperty(value="其他贷款",name="otherLoan")
    private Double otherLoan;

    public Double getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(Double monthIncome) {
        this.monthIncome = monthIncome;
    }

    public Double getOtherIncome() {
        return otherIncome;
    }

    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }

    public Double getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(Double yearIncome) {
        this.yearIncome = yearIncome;
    }

    public Double getOtherLoan() {
        return otherLoan;
    }

    public void setOtherLoan(Double otherLoan) {
        this.otherLoan = otherLoan;
    }
}
