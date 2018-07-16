package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 收货地址信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_buss_receive")
public class BussinessRiskReceivEntity  extends BaseEntity {
    @ManyToOne
    private BaseBussinessRiskEntity baseBussinessRisk;
    //收货人
    private String name;
    //地区
    private String area;
    //地址
    private String address;
    //手机号码
    private String mobile;
    //固定电话
    private String telephone;
    //默认地址
    private String default1;
    //收货地址右边
    private String zip_code;


    public BaseBussinessRiskEntity getBaseBussinessRisk() {
        return baseBussinessRisk;
    }

    public void setBaseBussinessRisk(BaseBussinessRiskEntity baseBussinessRisk) {
        this.baseBussinessRisk = baseBussinessRisk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDefault1() {
        return default1;
    }

    public void setDefault1(String default1) {
        this.default1 = default1;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
