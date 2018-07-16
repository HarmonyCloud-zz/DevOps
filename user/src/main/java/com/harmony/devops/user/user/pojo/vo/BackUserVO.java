package com.harmony.devops.user.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.user.pojo.entity.enums.UserTypeEnum;
import com.zhengtou.cf.common.utils.TimeUtil;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackUserVO extends PeakBaseVo{
    //登陆
    public BackUserVO(Long id, String userNo, long createTime, String nickName, String phone, UserTypeEnum userType, long orgId, Long
            storeId) {
        this.id = id;
        this.userNo = userNo;
        this.createTime = TimeUtil.timeToString(createTime);
        this.nickName = nickName;
        this.phone = phone;
        this.userType = userType;
        this.orgId = orgId;
        this.storeId = storeId;
    }
    //展示
    public BackUserVO(Long id, String userNo, long createTime, String nickName, String phone, UserTypeEnum userType, String orgName, String
            storeName) {
        this.id = id;
        this.userNo = userNo;
        this.createTime = TimeUtil.timeToString(createTime);
        this.nickName = nickName;
        this.phone = StringUtils.isNotBlank(phone)?phone:"";
        this.userType = userType;
        this.orgName = orgName;
        this.storeName = StringUtils.isNotBlank(storeName)?storeName:"";
    }
    //token
    private String token;
    //最近登陆时间
    private String lastLoginTime;
    //id
    private Long id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户昵称，默认为手机号
     */
    private String nickName;
    /**
     * 用户手机号
     */
    private String phone;
    /**
     * 用户类型
     */
    private UserTypeEnum userType;
    /**
     * 机构
     */
    private Long orgId;
    private String orgName;
    /**
     * 门店
     */
    private Long storeId;
    private String storeName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
