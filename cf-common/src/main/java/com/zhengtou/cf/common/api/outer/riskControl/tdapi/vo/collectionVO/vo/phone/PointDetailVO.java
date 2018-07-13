package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 积分箱单
 */
public class PointDetailVO {
    //积分周期
    private String point_cycle;
    //本期末可用节分
    private String point_available;
    //上期末可用积分
    private String point_last_balance;
    //当前使用积分
    private String point_usage;
    //本期新增可用积分
    private String point_new_available;

    public String getPoint_cycle() {
        return point_cycle;
    }

    public void setPoint_cycle(String point_cycle) {
        this.point_cycle = point_cycle;
    }

    public String getPoint_available() {
        return point_available;
    }

    public void setPoint_available(String point_available) {
        this.point_available = point_available;
    }

    public String getPoint_last_balance() {
        return point_last_balance;
    }

    public void setPoint_last_balance(String point_last_balance) {
        this.point_last_balance = point_last_balance;
    }

    public String getPoint_usage() {
        return point_usage;
    }

    public void setPoint_usage(String point_usage) {
        this.point_usage = point_usage;
    }

    public String getPoint_new_available() {
        return point_new_available;
    }

    public void setPoint_new_available(String point_new_available) {
        this.point_new_available = point_new_available;
    }
}
