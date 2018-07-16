package com.harmony.devops.user.user.pojo.vo.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 通讯录vo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneBookVO extends PeakBaseVo{
    //姓名
    private String name;
    //手机号
    private String mobile;

    public PhoneBookVO(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
