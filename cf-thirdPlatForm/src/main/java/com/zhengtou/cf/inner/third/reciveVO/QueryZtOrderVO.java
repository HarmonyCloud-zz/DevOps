package com.zhengtou.cf.inner.third.reciveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.thirdOperator.pojo.enums.SignStatusEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class QueryZtOrderVO extends PeakBaseVo{
    public QueryZtOrderVO() {
    }
    @ApiModelProperty(value="创建时间起",name="createTimeStart")
    private Long createTimeStart;
    @ApiModelProperty(value="创建时间止",name="createTimeEnd")
    private Long createTimeEnd;
    @ApiModelProperty(value="发标时间起",name="signTimeStart")
    private Long signTimeStart;
    @ApiModelProperty(value="发标时间止",name="signTimeStart")
    private Long signTimeEnd;
    @ApiModelProperty(value="发标状态",name="signStatusEnum")
    private SignStatusEnum signStatusEnum;

    public Long getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Long createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Long getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Long createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Long getSignTimeStart() {
        return signTimeStart;
    }

    public void setSignTimeStart(Long signTimeStart) {
        this.signTimeStart = signTimeStart;
    }

    public Long getSignTimeEnd() {
        return signTimeEnd;
    }

    public void setSignTimeEnd(Long signTimeEnd) {
        this.signTimeEnd = signTimeEnd;
    }

    public SignStatusEnum getSignStatusEnum() {
        return signStatusEnum;
    }

    public void setSignStatusEnum(SignStatusEnum signStatusEnum) {
        this.signStatusEnum = signStatusEnum;
    }
}
