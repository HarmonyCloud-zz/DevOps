package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

import java.util.List;

/**
 * 黑名单信息
 * @author 葛文镇
 */
public class UserBlackVO {
    //身份证和姓名是否在黑名单
    private String blacklist_name_with_idcard;
    //身份证和姓名黑名单信息更新时间
    private String blacklist_update_time_name_idcard;
    //姓名和手机是否在黑名单
    private String blacklist_name_with_phone;
    //姓名和手机黑名单信息更新时间
    private String blacklist_update_time_name_phone;
    //黑名单分类
    private List<String> blacklist_category;
    //黑名单详情
    private List<BlackDetailVO> blacklist_details;

    public String getBlacklist_name_with_idcard() {
        return blacklist_name_with_idcard;
    }

    public void setBlacklist_name_with_idcard(String blacklist_name_with_idcard) {
        this.blacklist_name_with_idcard = blacklist_name_with_idcard;
    }

    public String getBlacklist_update_time_name_idcard() {
        return blacklist_update_time_name_idcard;
    }

    public void setBlacklist_update_time_name_idcard(String blacklist_update_time_name_idcard) {
        this.blacklist_update_time_name_idcard = blacklist_update_time_name_idcard;
    }

    public String getBlacklist_name_with_phone() {
        return blacklist_name_with_phone;
    }

    public void setBlacklist_name_with_phone(String blacklist_name_with_phone) {
        this.blacklist_name_with_phone = blacklist_name_with_phone;
    }

    public String getBlacklist_update_time_name_phone() {
        return blacklist_update_time_name_phone;
    }

    public void setBlacklist_update_time_name_phone(String blacklist_update_time_name_phone) {
        this.blacklist_update_time_name_phone = blacklist_update_time_name_phone;
    }

    public List<String> getBlacklist_category() {
        return blacklist_category;
    }

    public void setBlacklist_category(List<String> blacklist_category) {
        this.blacklist_category = blacklist_category;
    }

    public List<BlackDetailVO> getBlacklist_details() {
        return blacklist_details;
    }

    public void setBlacklist_details(List<BlackDetailVO> blacklist_details) {
        this.blacklist_details = blacklist_details;
    }

    public class BlackDetailVO{
        private String details_key;
        private String details_value;

        public String getDetails_key() {
            return details_key;
        }

        public void setDetails_key(String details_key) {
            this.details_key = details_key;
        }

        public String getDetails_value() {
            return details_value;
        }

        public void setDetails_value(String details_value) {
            this.details_value = details_value;
        }
    }
}
