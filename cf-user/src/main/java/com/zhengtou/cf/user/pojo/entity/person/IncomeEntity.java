package com.zhengtou.cf.user.pojo.entity.person;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 收支信息
 */
@Entity
@Table(name = "t_person_income")
public class IncomeEntity extends BaseEntity {
    @OneToOne
    private PersonalEntity personal;
    //    月收入
    private Double monthIncome;
    //    其他收入
    private Double otherIncome;
    //    年收入
    private Double yearIncome;
    //    其他贷款
    private Double otherLoan;

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
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
