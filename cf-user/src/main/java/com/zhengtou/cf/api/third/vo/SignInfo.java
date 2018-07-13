package com.zhengtou.cf.api.third.vo;

/**
 * @author 葛文镇
 */
public class SignInfo {

    //签章模板地址
    private int xPosion;
    //需要替换的数据
    private int yPosion;
    //公司签章数据
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
