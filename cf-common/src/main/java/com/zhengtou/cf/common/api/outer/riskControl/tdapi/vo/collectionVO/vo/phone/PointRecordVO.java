package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 积分记录
 */
public class PointRecordVO {
    //操作时间
    private String operation_time;
    //变更积分
    private String point_change;
    //积分类型
    private String point_type;
    //操作类型
    private String operation_type;
    //用户号码
    private String user_number;

    public String getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(String operation_time) {
        this.operation_time = operation_time;
    }

    public String getPoint_change() {
        return point_change;
    }

    public void setPoint_change(String point_change) {
        this.point_change = point_change;
    }

    public String getPoint_type() {
        return point_type;
    }

    public void setPoint_type(String point_type) {
        this.point_type = point_type;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }
}
