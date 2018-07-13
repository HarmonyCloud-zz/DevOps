package com.zhengtou.cf.thirdOperator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.thirdOperator.pojo.enums.ThirdTypeEnum;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdUserVO extends PeakBaseVo {
    public ThirdUserVO(long thirdUserId,String userNo, String phone, ThirdTypeEnum userType, String address, int zhimaScore, String idNo, String name, String
            cardNo, String city) {
        this.thirdUserId=thirdUserId;
        this.userNo = userNo;
        this.phone = phone;
        this.userType = userType;
        this.address = address;
        this.zhimaScore = zhimaScore;
        this.idNo = idNo;
        this.name = name;
        this.cardNo = cardNo;
        this.city = city;
    }

    private Long thirdUserId;
    private String userNo;
    private String phone;
    private ThirdTypeEnum userType;
    private String address;
    private int zhimaScore;
    private String idNo;
    private String name;
    private String cardNo;
    private String city;

    public Long getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(Long thirdUserId) {
        this.thirdUserId = thirdUserId;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

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

    public int getZhimaScore() {
        return zhimaScore;
    }

    public void setZhimaScore(int zhimaScore) {
        this.zhimaScore = zhimaScore;
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
