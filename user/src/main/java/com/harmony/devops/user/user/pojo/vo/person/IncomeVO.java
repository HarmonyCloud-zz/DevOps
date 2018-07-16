package com.harmony.devops.user.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收支信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-收支信息vo")
public class IncomeVO extends PeakBaseVo {
    public IncomeVO() {
    }

    //展示
    public IncomeVO(long incomeId, Double monthIncome, Double otherIncome, Double yearIncome, Double otherLoan) {
        this.incomeId= incomeId;
        this.monthIncome = monthIncome;
        this.otherIncome = otherIncome;
        this.yearIncome = yearIncome;
        this.otherLoan = otherLoan;
    }
    //上传长亮
    public IncomeVO(Double monthIncome, Double otherIncome, Double yearIncome, Double otherLoan) {
        this.monthIncome = monthIncome;
        this.otherIncome = otherIncome;
        this.yearIncome = yearIncome;
        this.otherLoan = otherLoan;
    }
    @ApiModelProperty(value="收入信息id",name="incomeId")
    private Long incomeId;
    @ApiModelProperty(value="月收入",name="monthIncome")
    private Double monthIncome;
    @ApiModelProperty(value="其他收入",name="otherIncome")
    private Double otherIncome;
    @ApiModelProperty(value="年收入",name="yearIncome")
    private Double yearIncome;
    @ApiModelProperty(value="其他贷款",name="otherLoan")
    private Double otherLoan;


    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

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
