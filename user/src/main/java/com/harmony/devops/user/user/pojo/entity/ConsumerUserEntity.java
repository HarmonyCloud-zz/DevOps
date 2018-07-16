package com.harmony.devops.user.user.pojo.entity;

import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.harmony.devops.user.user.pojo.entity.enums.UserSourceTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.PersonalEntity;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialIdTypeEnum;

import javax.persistence.*;

@Entity
/**
 * 前台用户
 * @author 葛文镇
 */
public class ConsumerUserEntity extends UserEntity {
    /**
     * 合作方;引流机构
     */
    @ManyToOne
    private OrganizationEntity organiza;
    /**
     * 授信额度
     */
    private Long creditAmt;
    /**
     * 剩余额度
     */
    private Long canUseAmt;
    /**
     * 常住地址
     */
    private String address;
    /**
     * 居住状况
     */
    private String liveSituation;
    /**
     * 额度生成日期
     */
    private Long amtCreateTime;
    /**
     * 用户身份类型
     */
    @Enumerated(EnumType.STRING)
    private SocialIdTypeEnum socialTypeEnum=SocialIdTypeEnum.在职人员;

    /**
     * 用户信息是否完善
     * 0:未完善，1：已完善
     */
    @Enumerated(EnumType.STRING)
    private UserInfoCompleteEnum isUserInfoComplete = UserInfoCompleteEnum.未完善;

    /**
     * 渠道类型
     */
    @Enumerated(EnumType.STRING)
    private UserSourceTypeEnum userSourceTypeEnum;

    @OneToOne
    private PersonalEntity personal;


    public OrganizationEntity getOrganiza() {
        return organiza;
    }

    public void setOrganiza(OrganizationEntity organiza) {
        this.organiza = organiza;
    }

    public Long getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(Long creditAmt) {
        this.creditAmt = creditAmt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLiveSituation() {
        return liveSituation;
    }

    public void setLiveSituation(String liveSituation) {
        this.liveSituation = liveSituation;
    }

    public SocialIdTypeEnum getSocialTypeEnum() {
        return socialTypeEnum;
    }

    public void setSocialTypeEnum(SocialIdTypeEnum socialTypeEnum) {
        this.socialTypeEnum = socialTypeEnum;
    }

    public UserInfoCompleteEnum getIsUserInfoComplete() {
        return isUserInfoComplete;
    }

    public void setIsUserInfoComplete(UserInfoCompleteEnum isUserInfoComplete) {
        this.isUserInfoComplete = isUserInfoComplete;
    }

    public Long getCanUseAmt() {
        return canUseAmt;
    }

    public void setCanUseAmt(Long canUseAmt) {
        this.canUseAmt = canUseAmt;
    }

    public Long getAmtCreateTime() {
        return amtCreateTime;
    }

    public void setAmtCreateTime(Long amtCreateTime) {
        this.amtCreateTime = amtCreateTime;
    }

    public PersonalEntity getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalEntity personal) {
        this.personal = personal;
    }

    public UserSourceTypeEnum getUserSourceTypeEnum() {
        return userSourceTypeEnum;
    }

    public void setUserSourceTypeEnum(UserSourceTypeEnum userSourceTypeEnum) {
        this.userSourceTypeEnum = userSourceTypeEnum;
    }
}
