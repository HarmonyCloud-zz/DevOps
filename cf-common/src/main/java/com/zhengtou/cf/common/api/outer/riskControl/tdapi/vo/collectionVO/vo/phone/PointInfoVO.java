package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

import java.util.List;

/**
 * 积分信息
 * @author 葛文镇
 */
public class PointInfoVO {
    private List<PointDetailVO> point_detail;
    private List<PointRecordVO> point_record;

    public List<PointDetailVO> getPoint_detail() {
        return point_detail;
    }

    public void setPoint_detail(List<PointDetailVO> point_detail) {
        this.point_detail = point_detail;
    }

    public List<PointRecordVO> getPoint_record() {
        return point_record;
    }

    public void setPoint_record(List<PointRecordVO> point_record) {
        this.point_record = point_record;
    }
}
