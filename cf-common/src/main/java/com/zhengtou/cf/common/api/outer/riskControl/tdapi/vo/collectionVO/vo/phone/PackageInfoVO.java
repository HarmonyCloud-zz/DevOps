package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

import java.util.List;

/**
 * 业务信息
 * @author 葛文镇
 */
public class PackageInfoVO {
    //品牌
    private String brand_name;
    //缴费类型
    private String pay_type;
    //业务详情
    private List<PackageDetailVO> package_detail;


    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public List<PackageDetailVO> getPackage_detail() {
        return package_detail;
    }

    public void setPackage_detail(List<PackageDetailVO> package_detail) {
        this.package_detail = package_detail;
    }
}
