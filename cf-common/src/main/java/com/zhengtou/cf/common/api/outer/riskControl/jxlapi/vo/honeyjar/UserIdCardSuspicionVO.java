package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

import java.util.List;

/**
 * 身份证号码存疑
 * @author 葛文镇
 */
public class UserIdCardSuspicionVO {
    private List<PhoneInfoVO> idcard_with_other_phones;
    private List<NameInfoVO> idcard_with_other_names;
    private List<OperatorInfoVO> idcard_applied_in_orgs;

    public List<PhoneInfoVO> getIdcard_with_other_phones() {
        return idcard_with_other_phones;
    }

    public void setIdcard_with_other_phones(List<PhoneInfoVO> idcard_with_other_phones) {
        this.idcard_with_other_phones = idcard_with_other_phones;
    }

    public List<NameInfoVO> getIdcard_with_other_names() {
        return idcard_with_other_names;
    }

    public void setIdcard_with_other_names(List<NameInfoVO> idcard_with_other_names) {
        this.idcard_with_other_names = idcard_with_other_names;
    }

    public List<OperatorInfoVO> getIdcard_applied_in_orgs() {
        return idcard_applied_in_orgs;
    }

    public void setIdcard_applied_in_orgs(List<OperatorInfoVO> idcard_applied_in_orgs) {
        this.idcard_applied_in_orgs = idcard_applied_in_orgs;
    }
}
