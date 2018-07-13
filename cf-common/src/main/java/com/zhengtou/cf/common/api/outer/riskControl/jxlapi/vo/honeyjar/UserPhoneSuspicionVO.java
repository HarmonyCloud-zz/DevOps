package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

import java.util.List;

/**
 * 手机号码存疑
 * @author 葛文镇
 */
public class UserPhoneSuspicionVO {
    private List<CardInfoVO> phone_with_other_idcards;
    private List<NameInfoVO> phone_with_other_names;
    private List<OperatorInfoVO> phone_applied_in_orgs;

    public List<CardInfoVO> getPhone_with_other_idcards() {
        return phone_with_other_idcards;
    }

    public void setPhone_with_other_idcards(List<CardInfoVO> phone_with_other_idcards) {
        this.phone_with_other_idcards = phone_with_other_idcards;
    }

    public List<NameInfoVO> getPhone_with_other_names() {
        return phone_with_other_names;
    }

    public void setPhone_with_other_names(List<NameInfoVO> phone_with_other_names) {
        this.phone_with_other_names = phone_with_other_names;
    }

    public List<OperatorInfoVO> getPhone_applied_in_orgs() {
        return phone_applied_in_orgs;
    }

    public void setPhone_applied_in_orgs(List<OperatorInfoVO> phone_applied_in_orgs) {
        this.phone_applied_in_orgs = phone_applied_in_orgs;
    }

}
