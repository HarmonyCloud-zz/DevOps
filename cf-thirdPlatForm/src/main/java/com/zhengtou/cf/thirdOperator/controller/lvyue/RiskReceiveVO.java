package com.zhengtou.cf.thirdOperator.controller.lvyue;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class RiskReceiveVO extends PeakBaseVo {
    public RiskReceiveVO() {
    }

    private String name;
    private String data7;
    private String industryfocus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData7() {
        return data7;
    }

    public void setData7(String data7) {
        this.data7 = data7;
    }

    public String getIndustryfocus() {
        return industryfocus;
    }

    public void setIndustryfocus(String industryfocus) {
        this.industryfocus = industryfocus;
    }
}
