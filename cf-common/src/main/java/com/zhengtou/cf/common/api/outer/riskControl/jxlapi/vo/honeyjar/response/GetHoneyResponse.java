package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.response;

import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.*;

import java.util.List;

/**
 * 聚信立蜜罐返回数据结构
 * @author 葛文镇
 */
public class GetHoneyResponse extends HoneyBaseResponse{
    private Content data;

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }

    public class Content{
        private UserGrayVO user_gray;
        private UserPhoneSuspicionVO user_phone_suspicion;
        private String update_time;
        private UserIdCardSuspicionVO user_idcard_suspicion;
        private List<UserSearchedHistoryVO> user_searched_history_by_orgs;
        private String user_searched_statistic;
        private UserBlackVO user_blacklist;
        private UserBasicVO user_basic;
        private UserRegisterVO user_register_orgs;
        private String auth_org;
        private String user_grid_id;

        public UserGrayVO getUser_gray() {
            return user_gray;
        }

        public void setUser_gray(UserGrayVO user_gray) {
            this.user_gray = user_gray;
        }

        public UserPhoneSuspicionVO getUser_phone_suspicion() {
            return user_phone_suspicion;
        }

        public void setUser_phone_suspicion(UserPhoneSuspicionVO user_phone_suspicion) {
            this.user_phone_suspicion = user_phone_suspicion;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public UserIdCardSuspicionVO getUser_idcard_suspicion() {
            return user_idcard_suspicion;
        }

        public void setUser_idcard_suspicion(UserIdCardSuspicionVO user_idcard_suspicion) {
            this.user_idcard_suspicion = user_idcard_suspicion;
        }

        public List<UserSearchedHistoryVO> getUser_searched_history_by_orgs() {
            return user_searched_history_by_orgs;
        }

        public void setUser_searched_history_by_orgs(List<UserSearchedHistoryVO> user_searched_history_by_orgs) {
            this.user_searched_history_by_orgs = user_searched_history_by_orgs;
        }

        public String getUser_searched_statistic() {
            return user_searched_statistic;
        }

        public void setUser_searched_statistic(String user_searched_statistic) {
            this.user_searched_statistic = user_searched_statistic;
        }

        public UserBlackVO getUser_blacklist() {
            return user_blacklist;
        }

        public void setUser_blacklist(UserBlackVO user_blacklist) {
            this.user_blacklist = user_blacklist;
        }

        public UserBasicVO getUser_basic() {
            return user_basic;
        }

        public void setUser_basic(UserBasicVO user_basic) {
            this.user_basic = user_basic;
        }

        public UserRegisterVO getUser_register_orgs() {
            return user_register_orgs;
        }

        public void setUser_register_orgs(UserRegisterVO user_register_orgs) {
            this.user_register_orgs = user_register_orgs;
        }

        public String getAuth_org() {
            return auth_org;
        }

        public void setAuth_org(String auth_org) {
            this.auth_org = auth_org;
        }

        public String getUser_grid_id() {
            return user_grid_id;
        }

        public void setUser_grid_id(String user_grid_id) {
            this.user_grid_id = user_grid_id;
        }
    }
}
