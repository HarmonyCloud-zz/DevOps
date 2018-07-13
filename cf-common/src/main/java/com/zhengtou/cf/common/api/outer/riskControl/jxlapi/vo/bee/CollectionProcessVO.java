package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee;

import java.io.Serializable;

/**
 * 聚信立采集流程控制
 * @author 葛文镇
 */
public class CollectionProcessVO implements Serializable {
    private String name;
    private String idCard;
    private String phone;
    private String token;
    private String category_name;
    private String website;

    public CollectionProcessVO(String name,String idCard,String phone,String token, String category_name,String website) {
        this.token = token;
        this.category_name = category_name;
        this.website=website;
        this.name=name;
        this.idCard=idCard;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "CollectionProcessVO{" +
                "name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", category_name='" + category_name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
