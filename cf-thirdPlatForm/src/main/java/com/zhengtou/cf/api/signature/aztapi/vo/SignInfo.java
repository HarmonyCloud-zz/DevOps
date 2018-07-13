package com.zhengtou.cf.api.signature.aztapi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 葛文镇
 */
@ApiModel(discriminator = "签章参数")
public class SignInfo {
    public SignInfo() {
    }

    //签章模板地址
    @ApiModelProperty(value="x轴坐标",name="xPosion")
    private int xPosion;
    //需要替换的数据
    @ApiModelProperty(value="y轴坐标",name="yPosion")
    private int yPosion;
    //公司签章数据
    @ApiModelProperty(value="在第几页签章",name="number")
    private int number;

    public SignInfo(int xPosion, int yPosion, int number) {
        this.xPosion = xPosion;
        this.yPosion = yPosion;
        this.number = number;
    }

    public int getxPosion() {
        return xPosion;
    }

    public void setxPosion(int xPosion) {
        this.xPosion = xPosion;
    }

    public int getyPosion() {
        return yPosion;
    }

    public void setyPosion(int yPosion) {
        this.yPosion = yPosion;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
