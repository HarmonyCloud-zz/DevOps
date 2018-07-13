package com.zhengtou.cf.user.controller.reciveVO;

import com.zhengtou.cf.common.annotation.validator.Name;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class BackUserReciveVO extends PeakBaseVo{
    public BackUserReciveVO() {
    }
    @Name
    @ApiModelProperty(value="真实姓名（不可为空，支持中英文）",name="nickName")
    private String nickName;
    @Phone
    @ApiModelProperty(value="用户手机号（可为空）",name="phone")
    private String phone;
    @ApiModelProperty(value="用户类型（不可为空）",name="userType")
    private UserTypeEnum userType;
    @ApiModelProperty(value="机构id（可为空）",name="orgId")
    private Long orgId;
    @ApiModelProperty(value="门店id（可为空）",name="storeId")
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
