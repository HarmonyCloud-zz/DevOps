package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

/**
 * 亲情网信息
 * @author 葛文镇
 */
public class FamilyInfoVO {
    //成员号码
    private String member_number;
    //成员短号
    private String member_cornet;
    //成员类型
    private String member_type;
    //创建时间
    private String member_create_time;

    public String getMember_number() {
        return member_number;
    }

    public void setMember_number(String member_number) {
        this.member_number = member_number;
    }

    public String getMember_cornet() {
        return member_cornet;
    }

    public void setMember_cornet(String member_cornet) {
        this.member_cornet = member_cornet;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getMember_create_time() {
        return member_create_time;
    }

    public void setMember_create_time(String member_create_time) {
        this.member_create_time = member_create_time;
    }
}
