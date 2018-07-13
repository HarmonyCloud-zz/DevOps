package com.zhengtou.cf.trade.controller.receiveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStageEnum;
import com.zhengtou.cf.trade.pojo.entity.trade.enums.TradeStatusEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class QueryTradeReciveVO extends PeakBaseVo{
    public QueryTradeReciveVO() {
    }

    @ApiModelProperty(value="真实姓名",name="realname")
    private String realname;
    @ApiModelProperty(value="身份证号",name="idnum")
    private String idnum;
    @ApiModelProperty(value="产品类型",name="ztProductEnum")
    private ZtProductEnum ztProductEnum;
    @ApiModelProperty(value="本地流水号",name="tradeFlowNo")
    private String tradeFlowNo;
    @ApiModelProperty(value="外部流水单号",name="outTradeFlowNo")
    private String outTradeFlowNo;
    @ApiModelProperty(value="流水的阶段",name="flowStage")
    private TradeStageEnum flowStage;
    @ApiModelProperty(value="订单状态",name="tradeStatus")
    private TradeStatusEnum tradeStatus;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public ZtProductEnum getZtProductEnum() {
        return ztProductEnum;
    }

    public void setZtProductEnum(ZtProductEnum ztProductEnum) {
        this.ztProductEnum = ztProductEnum;
    }

    public String getTradeFlowNo() {
        return tradeFlowNo;
    }

    public void setTradeFlowNo(String tradeFlowNo) {
        this.tradeFlowNo = tradeFlowNo;
    }

    public String getOutTradeFlowNo() {
        return outTradeFlowNo;
    }

    public void setOutTradeFlowNo(String outTradeFlowNo) {
        this.outTradeFlowNo = outTradeFlowNo;
    }

    public TradeStageEnum getFlowStage() {
        return flowStage;
    }

    public void setFlowStage(TradeStageEnum flowStage) {
        this.flowStage = flowStage;
    }

    public TradeStatusEnum getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeStatusEnum tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
