package com.harmony.devops.user.user.controller.reciveVO;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
import io.swagger.annotations.ApiModelProperty;

/**
 * 前台用户vo，检索使用
 * @author 葛文镇
 */
public class QueryConsumerUserReciveVO extends PeakBaseVo {
    @ApiModelProperty(value="合作方;引流机构",name="orgId")
    private Long orgId;
    @ApiModelProperty(value="真实姓名",name="realname")
    private String realname;
    @ApiModelProperty(value="身份证号",name="idnum")
    private String idnum;
    @ApiModelProperty(value="手机号",name="phone")
    private String phone;
    @ApiModelProperty(value="用户信息是否完善",name="isUserInfoComplete")
    private UserInfoCompleteEnum isUserInfoComplete;


    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public UserInfoCompleteEnum getIsUserInfoComplete() {
        return isUserInfoComplete;
    }

    public void setIsUserInfoComplete(UserInfoCompleteEnum isUserInfoComplete) {
        this.isUserInfoComplete = isUserInfoComplete;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
