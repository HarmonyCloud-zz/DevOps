package com.zhengtou.cf.user.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import com.zhengtou.cf.common.utils.MoneyUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.enums.SocialIdTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户登陆vo
 *
 * @author 葛文镇
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(discriminator = "郑投-用户登陆vo")
public class ConsumerUserVO extends PeakBaseVo {
    /**
     * 客户用户登陆使用
     */
    public ConsumerUserVO(long id, String userNo, UserTypeEnum userType, Long lastLoginTime, String realname, String phone, UserInfoCompleteEnum
            userInfoCompleteEnum, SocialIdTypeEnum socialTypeEnum, Long canUseAmt, Long creditAmt,Long amtCreateTime) {
        this.id = id;
        this.userNo = userNo;
        this.userType = userType;
        this.lastLoginTime = lastLoginTime == null ? "" : TimeUtil.timeToString(lastLoginTime);
        this.realname = StringUtils.isBlank(realname)?"":realname;
        this.phone = phone;
        this.userInfoCompleteEnum = userInfoCompleteEnum;
        this.socialTypeEnum = socialTypeEnum;
        this.canUseAmt=canUseAmt!=null? MoneyUtil.moneyToString(canUseAmt):"";
        this.creditAmt=creditAmt!=null?MoneyUtil.moneyToString(creditAmt):"";
        this.amtCreateTime=amtCreateTime!=null?TimeUtil.timeToString(amtCreateTime):"";
    }

    /**
     * 展示
     */
    public ConsumerUserVO(long id, String userNo, Long lastLoginTime, String realname, String phone, UserInfoCompleteEnum userInfoCompleteEnum,
                          SocialIdTypeEnum socialTypeEnum, Long canUseAmt, Long creditAmt) {
        this.id = id;
        this.userNo = userNo;
        this.lastLoginTime = lastLoginTime==null?"":TimeUtil.timeToString(lastLoginTime);
        this.realname = realname;
        this.phone = phone;
        this.userInfoCompleteEnum = userInfoCompleteEnum;
        this.socialTypeEnum = socialTypeEnum;
        this.canUseAmt=canUseAmt!=null?MoneyUtil.moneyToString(canUseAmt):"";
        this.creditAmt=creditAmt!=null?MoneyUtil.moneyToString(creditAmt):"";
    }

    public ConsumerUserVO() {
    }

    @ApiModelProperty(value="用户token",name="token")
    private String token;
    @ApiModelProperty(value="用户id",name="id")
    private long id;
    @ApiModelProperty(value="用户编号",name="userNo")
    private String userNo;
    @ApiModelProperty(value="用户资料id",name="personId")
    private Long personId;
    @ApiModelProperty(value="用户类型：*后台管理员,*运营人员,*风控人员,*客服人员,*法人,*店员,*店长,*机构管理员,*消费用户,*静默注册消费用户;",name="userType")
    private UserTypeEnum userType;
    @ApiModelProperty(value="最近登陆时间",name="lastLoginTime")
    private String lastLoginTime;
    @ApiModelProperty(value="用户昵称",name="nickName")
    private String nickName;
    @ApiModelProperty(value="用户真实姓名",name="realname")
    private String realname;
    @ApiModelProperty(value="用户手机号",name="phone")
    private String phone;
    @ApiModelProperty(value="信息是否完善：*未完善,*已完善;",name="userInfoCompleteEnum")
    private UserInfoCompleteEnum userInfoCompleteEnum;
    @ApiModelProperty(value="是否设置交易密码",name="hasTradePwd")
    private boolean hasTradePwd;
    @ApiModelProperty(value="社会身份：" +
            "   *学生(\"SI01\"),\n" +
            "    *在职人员(\"SI02\"),\n" +
            "    *自由职业(\"SI03\"),\n" +
            "    *企业负责人(\"SI04\"),\n" +
            "    *无业(\"SI05\"),\n" +
            "    *退休(\"SI06\");",name="socialTypeEnum")
    private SocialIdTypeEnum socialTypeEnum;
    @ApiModelProperty(value="可用额度",name="canUseAmt")
    private String canUseAmt;
    @ApiModelProperty(value="总额度",name="creditAmt")
    private String creditAmt;
    @ApiModelProperty(value="额度生成日期",name="amtCreateTime")
    private String amtCreateTime;
    @ApiModelProperty(value="头像地址",name="headPath")
    private String headPath;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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
}
