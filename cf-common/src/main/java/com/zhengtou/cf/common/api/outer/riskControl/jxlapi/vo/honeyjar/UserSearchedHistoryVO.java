package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

/**
 * 用户被机构查询历史
 * @author 葛文镇
 */
public class UserSearchedHistoryVO {
    private String searched_org;
    private String org_self;
    private String searched_date;

    public String getSearched_org() {
        return searched_org;
    }

    public void setSearched_org(String searched_org) {
        this.searched_org = searched_org;
    }

    public String getOrg_self() {
        return org_self;
    }

    public void setOrg_self(String org_self) {
        this.org_self = org_self;
    }

    public String getSearched_date() {
        return searched_date;
    }

    public void setSearched_date(String searched_date) {
        this.searched_date = searched_date;
    }
}
