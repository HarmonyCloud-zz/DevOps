package com.zhengtou.cf.thirdOperator.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 三方风控数据
 */
@Entity
@Table(name = "t_third_risk")
public class ThirdRiskEntity extends BaseEntity{
    @ManyToOne
    private ThirdUserEntity thirdUser;
    /**
     * 姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 行业关注名单
     */
    private String industryfocus;

    public ThirdUserEntity getThirdUser() {
        return thirdUser;
    }

    public void setThirdUser(ThirdUserEntity thirdUser) {
        this.thirdUser = thirdUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIndustryfocus() {
        return industryfocus;
    }

    public void setIndustryfocus(String industryfocus) {
        this.industryfocus = industryfocus;
    }
}
