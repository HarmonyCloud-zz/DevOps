package com.zhengtou.cf.thirdOperator.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 三方风控数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThirdRiskVO extends PeakBaseVo{
    public ThirdRiskVO(String name, String idNo, String industryfocus) {
        this.name = name;
        this.idNo = idNo;
        this.industryfocus = industryfocus;
    }

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
