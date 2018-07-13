package com.zhengtou.cf.inner.third.reciveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class QueryThirdUserVO extends PeakBaseVo {
    public QueryThirdUserVO() {
    }
    @ApiModelProperty(value="用户手机号",name="phone")
    private String phone;
    @ApiModelProperty(value="用户类型",name="userType")
    private ThirdTypeEnum userType;
    @ApiModelProperty(value="详细街道地址",name="address")
    private String address;
    @ApiModelProperty(value="芝麻分下限",name="zhimaScoreStart")
    private Integer zhimaScoreStart;
    @ApiModelProperty(value="芝麻分上限",name="zhimaScoreEnd")
    private Integer zhimaScoreEnd;
    @ApiModelProperty(value="身份证",name="idNo")
    private String idNo;
    @ApiModelProperty(value="真实姓名",name="name")
    private String name;
    @ApiModelProperty(value="银行卡号",name="cardNo")
    private String cardNo;
    @ApiModelProperty(value="省市区",name="city")
    private String city;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ThirdTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(ThirdTypeEnum userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZhimaScoreStart() {
        return zhimaScoreStart;
    }

    public void setZhimaScoreStart(Integer zhimaScoreStart) {
        this.zhimaScoreStart = zhimaScoreStart;
    }

    public Integer getZhimaScoreEnd() {
        return zhimaScoreEnd;
    }

    public void setZhimaScoreEnd(Integer zhimaScoreEnd) {
        this.zhimaScoreEnd = zhimaScoreEnd;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
