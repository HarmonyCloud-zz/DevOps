package com.harmony.devops.user.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.harmony.devops.user.user.pojo.entity.enums.UserTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.EducationEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.HouseConditionEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.MarryStatusEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialIdTypeEnum;

/**
 * 用户详细信息
 *
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumerUserInfo extends PeakBaseVo {
    public ConsumerUserInfo(long id, String userNo, UserTypeEnum userType, Long lastLoginTime, String nickName, String realname, String phone,
                            UserInfoCompleteEnum userInfoCompleteEnum, boolean hasTradePwd, SocialIdTypeEnum socialTypeEnum, Long canUseAmt,
                            Long creditAmt, Long amtCreateTime, EducationEnum educationEnum, String address, MarryStatusEnum maritalStatus, Integer
                                    age) {
        this.id = id;
        this.userNo = userNo;
        this.userType = userType;
        this.lastLoginTime = lastLoginTime != null ? TimeUtil.timeToString(lastLoginTime) : "";
        this.nickName = StringUtils.isBlank(nickName) ? "" : nickName;
        this.realname = StringUtils.isBlank(realname) ? "" : realname;
        this.phone = phone;
        this.userInfoCompleteEnum = userInfoCompleteEnum;
        this.hasTradePwd = hasTradePwd;
        this.socialTypeEnum = socialTypeEnum;
        this.canUseAmt = canUseAmt != null ? MoneyUtil.moneyToString(canUseAmt) : "";
        this.creditAmt = creditAmt != null ? MoneyUtil.moneyToString(creditAmt) : "";
        this.amtCreateTime = TimeUtil.timeToString(amtCreateTime);
        this.educationEnum = educationEnum;
        this.address = StringUtils.isBlank(address) ? "" : address;
        this.maritalStatus = maritalStatus;
        this.age = age == null ? "" : age + "";
    }

    public ConsumerUserInfo(long id, UserTypeEnum userType, Long lastLoginTime, String realname, String idNo,
                            UserInfoCompleteEnum userInfoCompleteEnum, boolean hasTradePwd, SocialIdTypeEnum socialTypeEnum, EducationEnum
                                    educationEnum, String address, MarryStatusEnum maritalStatus, Integer age,HouseConditionEnum houseCondition) {
        this.id = id;
        this.idNo=idNo;
        this.userType = userType;
        this.lastLoginTime = lastLoginTime != null ? TimeUtil.timeToString(lastLoginTime) : "";
        this.realname = StringUtils.isBlank(realname) ? "" : realname;
        this.userInfoCompleteEnum = userInfoCompleteEnum;
        this.hasTradePwd = hasTradePwd;
        this.socialTypeEnum = socialTypeEnum;
        this.educationEnum = educationEnum;
        this.address = StringUtils.isBlank(address) ? "" : address;
        this.maritalStatus = maritalStatus;
        this.age = age == null ? "" : age + "";
        this.houseCondition=houseCondition;
    }

    /**
     * 用户id
     */
    private long id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 用户类型
     */
    private UserTypeEnum userType;
    /**
     * 身份证
     */
    private String idNo;
    /**
     * 最近登陆时间
     */
    private String lastLoginTime;
    /**
     * 年龄
     */
    private String age;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 信息是否完善
     */
    private UserInfoCompleteEnum userInfoCompleteEnum;
    /**
     * 是否设置交易密码
     */
    private boolean hasTradePwd;
    /**
     * 社会身份
     */
    private SocialIdTypeEnum socialTypeEnum;
    /**
     * 可用额度
     */
    private String canUseAmt;
    /**
     * 总额度
     */
    private String creditAmt;
    /**
     * 额度生成日期
     */
    private String amtCreateTime;
    /**
     * 头像地址
     */
    private String headPath;
    /**
     * 最高学历
     */
    private EducationEnum educationEnum;
    /**
     * 住房地址
     */
    private String address;
    /**
     * 住房情况
     */
    private HouseConditionEnum houseCondition;
    /**
     * 婚姻状况
     */
    private MarryStatusEnum maritalStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
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

    public UserInfoCompleteEnum getUserInfoCompleteEnum() {
        return userInfoCompleteEnum;
    }

    public void setUserInfoCompleteEnum(UserInfoCompleteEnum userInfoCompleteEnum) {
        this.userInfoCompleteEnum = userInfoCompleteEnum;
    }

    public SocialIdTypeEnum getSocialTypeEnum() {
        return socialTypeEnum;
    }

    public void setSocialTypeEnum(SocialIdTypeEnum socialTypeEnum) {
        this.socialTypeEnum = socialTypeEnum;
    }

    public boolean getHasTradePwd() {
        return hasTradePwd;
    }

    public void setHasTradePwd(boolean hasTradePwd) {
        this.hasTradePwd = hasTradePwd;
    }

    public boolean isHasTradePwd() {
        return hasTradePwd;
    }

    public String getCanUseAmt() {
        return canUseAmt;
    }

    public void setCanUseAmt(String canUseAmt) {
        this.canUseAmt = canUseAmt;
    }

    public String getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(String creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getAmtCreateTime() {
        return amtCreateTime;
    }

    public void setAmtCreateTime(String amtCreateTime) {
        this.amtCreateTime = amtCreateTime;
    }

    public EducationEnum getEducationEnum() {
        return educationEnum;
    }

    public void setEducationEnum(EducationEnum educationEnum) {
        this.educationEnum = educationEnum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MarryStatusEnum getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MarryStatusEnum maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
