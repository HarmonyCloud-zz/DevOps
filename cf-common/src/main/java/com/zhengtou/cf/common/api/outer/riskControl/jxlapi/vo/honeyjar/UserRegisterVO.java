package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar;

import java.util.List;

/**
 * 用户注册信息
 * @author 葛文镇
 */
public class UserRegisterVO {
    //查询手机号码
    private String phone_num;
    //注册数量
    private String register_cnt;
    //注册结构名称
    private List<String> register_orgs;
    //用户注册App统计详情
    private List<ResigisterDetailVO> register_orgs_statistics;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getRegister_cnt() {
        return register_cnt;
    }

    public void setRegister_cnt(String register_cnt) {
        this.register_cnt = register_cnt;
    }

    public List<String> getRegister_orgs() {
        return register_orgs;
    }

    public void setRegister_orgs(List<String> register_orgs) {
        this.register_orgs = register_orgs;
    }

    public List<ResigisterDetailVO> getRegister_orgs_statistics() {
        return register_orgs_statistics;
    }

    public void setRegister_orgs_statistics(List<ResigisterDetailVO> register_orgs_statistics) {
        this.register_orgs_statistics = register_orgs_statistics;
    }

    public class ResigisterDetailVO{
        private String count;
        private String label;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
