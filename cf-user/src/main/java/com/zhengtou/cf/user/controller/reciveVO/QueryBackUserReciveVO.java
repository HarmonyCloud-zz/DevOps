package com.zhengtou.cf.user.controller.reciveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class QueryBackUserReciveVO extends PeakBaseVo{
    public QueryBackUserReciveVO() {
    }
    @ApiModelProperty(value="真实姓名",name="nickName")
    private String nickName;
    @ApiModelProperty(value="用户手机号",name="phone")
    private String phone;
    @ApiModelProperty(value="用户类型",name="userType")
    private UserTypeEnum userType;
    @ApiModelProperty(value="机构id",name="orgId")
    private Long orgId;
    @ApiModelProperty(value="门店id",name="storeId")
    private Long storeId;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
