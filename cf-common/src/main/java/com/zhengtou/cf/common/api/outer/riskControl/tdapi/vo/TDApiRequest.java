package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.enums.*;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

/**
 * 同盾请求参数封装
 * @author 葛文镇
 */
public class TDApiRequest {
    //	业务流	STRING	建议有多个工作流的客户，传入biz_code以作区分。不传biz_code时默认执行当前存在的第一个工作流。详情参考业务流
    private FlowNoEnum biz_code;
    //	公司性质	STRING
    private String company_type;
    //共同借款人家庭地址	STRING
    private String coborrower_home_address;
    //	职业	STRING
    private String career;
    //	职位	STRING	参照职位表
    private PositionEnum occupation;
    //	第三联系人社会关系	STRING	参照第三联系人社会关系表
    private ContactRelationsEnum contact3_relation;
    //	获客渠道	STRING
    private String customer_channel;
    //	第五联系人姓名	STRING
    private String contact5_name;
    //	单位座机	STRING
    private String work_phone;
    //	担保人姓名	STRING
    private String surety_name;
    //	第一联系人身份证	STRING	应为正确的身份证格式
    private String contact1_id_number;
    //	第五联系人身份证	STRING	应为正确的身份证格式
    private String contact5_id_number;
    //	借款用途	STRING
    private String loan_purpose;
    //	共同借款人身份证	STRING	应为正确的身份证格式
    private String coborrower_id_number;
    //	共同借款人座机	STRING
    private String coborrower_phone;
    //	担保人座机	STRING
    private String surety_phone;
    //	trueIP	STRING
    private String trueip_address;
    //	TokenId	STRING
    private String token_id;
    //	房产情况	STRING
    private String house_property;
    //	第二联系人身份证	STRING	应为正确的身份证格式
    private String contact2_id_number;
    //	学历	STRING
    private String diploma;
    //	年收入	STRING	参照年收入表
    private YearIncomeEnum annual_income;
    //	借款人身份证	STRING	必填 应为正确的身份证格式
    private String id_number;
    //	担保人身份证	STRING	应为正确的身份证格式
    private String surety_id_number;
    //	借款人卡号	STRING
    private String card_number;
    //	第一联系人手机号	STRING	应为正确的手机号格式
    private String contact1_mobile;
    //	借款人座机	STRING
    private String account_phone;
    //	贷款金额	DOUBLE	应大于0
    private String loan_amount;
    //	借款人QQ	STRING
    private String qq_number;
    //	月均收入	STRING
    private String monthly_income;
    //	进件省份	STRING
    private String apply_province;
    //	担保人手机	STRING	应为正确的手机号格式
    private String surety_mobile;
    //	第四联系人社会关系	STRING	参照第四联系人社会关系表
    private ContactRelationsEnum contact4_relation;
    //	第五联系人手机号	STRING	应为正确的手机号格式
    private String contact5_mobile;
    //	贷款期限	INT	应大于0
    private String loan_term;
    //	借款人手机	STRING	必填 应为正确的手机号格式
    private String account_mobile;
    //	工作单位地址	STRING
    private String organization_address;
    //	第三联系人手机号	STRING	应为正确的手机号格式
    private String contact3_mobile;
    //	工作时间	STRING	参照工作时间表
    private WorkExperienceEnum work_time;
    //	第三联系人身份证	STRING	应为正确的身份证格式
    private String contact3_id_number;
    //	第三联系人姓名	STRING
    private String contact3_name;
    //	共同借款人姓名	STRING
    private String coborrower_name;
    //	贷款日期	STRING	日期格式应为:yyyy-MM-dd
    private String loan_date;
    //	人员类别	STRING
    private String applyer_type;
    //	是否曾跨平台借款	STRING
    private String is_cross_loan;
    //	所属行业	STRING
    private String industry;
    //	担保人公司地址	STRING
    private String surety_company_address;
    //	第二联系人姓名	STRING
    private String contact2_name;
    //	归属地详情类型	STRING
    private String resp_detail_type;
    //	进件城市	STRING
    private String apply_city;
    //	借款人邮箱	STRING
    private String account_email;
    //	担保人家庭地址	STRING
    private String surety_home_address;
    //	家庭地址	STRING
    private String home_address;
    //	婚姻情况	STRING
    private String marriage;
    //	借款人姓名	STRING	必填
    private String account_name;
    //	第五联系人社会关系	STRING	参照第五联系人社会关系表
    private ContactRelationsEnum contact5_relation;
    //	房产类型	STRING
    private String house_type;
    //	通讯地址	STRING
    private String contact_address;
    //	第一联系人姓名	STRING
    private String contact1_name;
    //	第四联系人身份证	STRING	应为正确的身份证格式
    private String contact4_id_number;
    //	第二联系人社会关系	STRING	参照第二联系人社会关系表
    private ContactRelationsEnum contact2_relation;
    //	共同借款人手机	STRING	应为正确的手机号格式
    private String coborrower_mobile;
    //	进件渠道	STRING
    private String apply_channel;
    //	第四联系人姓名	STRING
    private String contact4_name;
    //	IP	STRING
    private String ip_address;
    //	共同借款人公司地址	STRING
    private String coborrower_company_address;
    //	借款人毕业院校	STRING
    private String graduate_school;
    //	第一联系人社会关系	STRING	参照第一联系人社会关系表
    private ContactRelationsEnum contact1_relation;
    //	第四联系人手机号	STRING	应为正确的手机号格式
    private String contact4_mobile;
    //	借款人工作单位	STRING
    private String organization;
    //	第二联系人手机号	STRING	应为正确的手机号格式
    private String contact2_mobile;

    public TDApiRequest() {}

    public TDApiRequest(FlowNoEnum biz_code, String company_type, String coborrower_home_address, String career, PositionEnum occupation,
                        ContactRelationsEnum contact3_relation, String customer_channel, String contact5_name, String work_phone, String
                                       surety_name, String contact1_id_number, String contact5_id_number, String loan_purpose, String
                                       coborrower_id_number, String coborrower_phone, String surety_phone, String trueip_address, String token_id,
                        String house_property, String contact2_id_number, String diploma, YearIncomeEnum annual_income, String id_number,
                        String surety_id_number, String card_number, String contact1_mobile, String account_phone, String loan_amount,
                        String qq_number, String monthly_income, String apply_province, String surety_mobile, ContactRelationsEnum
                                       contact4_relation, String contact5_mobile, String loan_term, String account_mobile, String
                                       organization_address, String contact3_mobile, WorkExperienceEnum work_time, String contact3_id_number,
                        String contact3_name, String coborrower_name, String loan_date, String applyer_type, String is_cross_loan, String
                                       industry, String surety_company_address, String contact2_name, String resp_detail_type, String apply_city,
                        String account_email, String surety_home_address, String home_address, String marriage, String account_name,
                        ContactRelationsEnum contact5_relation, String house_type, String contact_address, String contact1_name, String
                                       contact4_id_number, ContactRelationsEnum contact2_relation, String coborrower_mobile, String apply_channel,
                        String contact4_name, String ip_address, String coborrower_company_address, String graduate_school,
                        ContactRelationsEnum contact1_relation, String contact4_mobile, String organization, String contact2_mobile) {
        this.biz_code = biz_code!=null?biz_code: FlowNoEnum.郑投网;
        this.company_type = StringUtils.isNotBlank(company_type)?company_type:"";
        this.coborrower_home_address = StringUtils.isNotBlank(coborrower_home_address)?coborrower_home_address:"";
        this.career = StringUtils.isNotBlank(career)?career:"";
        this.occupation = occupation;
        this.contact3_relation = contact3_relation;
        this.customer_channel = customer_channel;
        this.contact5_name = contact5_name;
        this.work_phone = work_phone;
        this.surety_name = surety_name;
        this.contact1_id_number = contact1_id_number;
        this.contact5_id_number = contact5_id_number;
        this.loan_purpose = loan_purpose;
        this.coborrower_id_number = coborrower_id_number;
        this.coborrower_phone = coborrower_phone;
        this.surety_phone = surety_phone;
        this.trueip_address = trueip_address;
        this.token_id = token_id;
        this.house_property = house_property;
        this.contact2_id_number = contact2_id_number;
        this.diploma = diploma;
        this.annual_income = annual_income;
        this.id_number = id_number;
        this.surety_id_number = surety_id_number;
        this.card_number = card_number;
        this.contact1_mobile = contact1_mobile;
        this.account_phone = account_phone;
        this.loan_amount = loan_amount;
        this.qq_number = qq_number;
        this.monthly_income = monthly_income;
        this.apply_province = apply_province;
        this.surety_mobile = surety_mobile;
        this.contact4_relation = contact4_relation;
        this.contact5_mobile = contact5_mobile;
        this.loan_term = loan_term;
        this.account_mobile = account_mobile;
        this.organization_address = organization_address;
        this.contact3_mobile = contact3_mobile;
        this.work_time = work_time;
        this.contact3_id_number = contact3_id_number;
        this.contact3_name = contact3_name;
        this.coborrower_name = coborrower_name;
        this.loan_date = loan_date;
        this.applyer_type = applyer_type;
        this.is_cross_loan = is_cross_loan;
        this.industry = industry;
        this.surety_company_address = surety_company_address;
        this.contact2_name = contact2_name;
        this.resp_detail_type = resp_detail_type;
        this.apply_city = apply_city;
        this.account_email = account_email;
        this.surety_home_address = surety_home_address;
        this.home_address = home_address;
        this.marriage = marriage;
        this.account_name = account_name;
        this.contact5_relation = contact5_relation;
        this.house_type = house_type;
        this.contact_address = contact_address;
        this.contact1_name = contact1_name;
        this.contact4_id_number = contact4_id_number;
        this.contact2_relation = contact2_relation;
        this.coborrower_mobile = coborrower_mobile;
        this.apply_channel = apply_channel;
        this.contact4_name = contact4_name;
        this.ip_address = ip_address;
        this.coborrower_company_address = coborrower_company_address;
        this.graduate_school = graduate_school;
        this.contact1_relation = contact1_relation;
        this.contact4_mobile = contact4_mobile;
        this.organization = organization;
        this.contact2_mobile = contact2_mobile;
    }

    /**
     * 身份证号
     * @param id_number
     * 电话
     * @param account_mobile
     * 姓名
     * @param account_name
     */
    public TDApiRequest(String id_number, String account_mobile, String account_name) {
        this.id_number = id_number;
        this.account_mobile = account_mobile;
        this.account_name = account_name;
    }

    /**
     * 公司性质
     * @param company_type
     * 职业
     * @param career
     * 职位
     * @param occupation
     * 借款用途
     * @param loan_purpose
     * 房产情况
     * @param house_property
     * 学历
     * @param diploma
     * 年收入
     * @param annual_income
     * 借款人身份证
     * @param id_number
     * 借款人卡号
     * @param card_number
     * 第一联系人手机号
     * @param contact1_mobile
     * 贷款金额
     * @param loan_amount
     * 借款人QQ
     * @param qq_number
     * 月均收入
     * @param monthly_income
     * 进件省份
     * @param apply_province
     * 贷款期限
     * @param loan_term
     * 借款人手机
     * @param account_mobile
     * 工作时间
     * @param work_time
     * 贷款日期
     * @param loan_date
     * 所属行业
     * @param industry
     * 第二联系人姓名
     * @param contact2_name
     * 进件城市
     * @param apply_city
     * 家庭地址
     * @param home_address
     * 婚姻情况
     * @param marriage
     * 借款人姓名
     * @param account_name
     * 房产类型
     * @param house_type
     * 通讯地址
     * @param contact_address
     * 第一联系人姓名
     * @param contact1_name
     * 第二联系人社会关系
     * @param contact2_relation
     * IP
     * @param ip_address
     * 借款人毕业院校
     * @param graduate_school
     * 第一联系人社会关系
     * @param contact1_relation
     * 借款人工作单位
     * @param organization
     * 第二联系人手机号
     * @param contact2_mobile
     * 工作单位地址
     * @param organization_address
     */
    public TDApiRequest(String company_type, String career, PositionEnum occupation, String loan_purpose, String house_property, String
            diploma, YearIncomeEnum annual_income, String id_number, String card_number, String contact1_mobile, String loan_amount, String
            qq_number, String monthly_income, String apply_province, String loan_term, String account_mobile, WorkExperienceEnum work_time, String
            loan_date, String industry, String contact2_name, String apply_city, String home_address, String marriage, String account_name, String
            house_type, String contact_address, String contact1_name, ContactRelationsEnum contact2_relation, String ip_address, String
            graduate_school, ContactRelationsEnum contact1_relation, String organization, String contact2_mobile, String organization_address) {
        this.company_type = company_type;
        this.career = career;
        this.occupation = occupation;
        this.loan_purpose = loan_purpose;
        this.house_property = house_property;
        this.diploma = diploma;
        this.annual_income = annual_income;
        this.id_number = id_number;
        this.card_number = card_number;
        this.contact1_mobile = contact1_mobile;
        this.loan_amount = loan_amount;
        this.qq_number = qq_number;
        this.monthly_income = monthly_income;
        this.apply_province = apply_province;
        this.loan_term = loan_term;
        this.account_mobile = account_mobile;
        this.work_time = work_time;
        this.loan_date = loan_date;
        this.industry = industry;
        this.contact2_name = contact2_name;
        this.apply_city = apply_city;
        this.home_address = home_address;
        this.marriage = marriage;
        this.account_name = account_name;
        this.house_type = house_type;
        this.contact_address = contact_address;
        this.contact1_name = contact1_name;
        this.contact2_relation = contact2_relation;
        this.ip_address = ip_address;
        this.graduate_school = graduate_school;
        this.contact1_relation = contact1_relation;
        this.organization = organization;
        this.contact2_mobile = contact2_mobile;
        this.organization_address=organization_address;
    }

    public FlowNoEnum getBiz_code() {
        return biz_code;
    }

    public void setBiz_code(FlowNoEnum biz_code) {
        this.biz_code = biz_code;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getCoborrower_home_address() {
        return coborrower_home_address;
    }

    public void setCoborrower_home_address(String coborrower_home_address) {
        this.coborrower_home_address = coborrower_home_address;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public PositionEnum getOccupation() {
        return occupation;
    }

    public void setOccupation(PositionEnum occupation) {
        this.occupation = occupation;
    }

    public ContactRelationsEnum getContact3_relation() {
        return contact3_relation;
    }

    public void setContact3_relation(ContactRelationsEnum contact3_relation) {
        this.contact3_relation = contact3_relation;
    }

    public String getCustomer_channel() {
        return customer_channel;
    }

    public void setCustomer_channel(String customer_channel) {
        this.customer_channel = customer_channel;
    }

    public String getContact5_name() {
        return contact5_name;
    }

    public void setContact5_name(String contact5_name) {
        this.contact5_name = contact5_name;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }

    public String getSurety_name() {
        return surety_name;
    }

    public void setSurety_name(String surety_name) {
        this.surety_name = surety_name;
    }

    public String getContact1_id_number() {
        return contact1_id_number;
    }

    public void setContact1_id_number(String contact1_id_number) {
        this.contact1_id_number = contact1_id_number;
    }

    public String getContact5_id_number() {
        return contact5_id_number;
    }

    public void setContact5_id_number(String contact5_id_number) {
        this.contact5_id_number = contact5_id_number;
    }

    public String getLoan_purpose() {
        return loan_purpose;
    }

    public void setLoan_purpose(String loan_purpose) {
        this.loan_purpose = loan_purpose;
    }

    public String getCoborrower_id_number() {
        return coborrower_id_number;
    }

    public void setCoborrower_id_number(String coborrower_id_number) {
        this.coborrower_id_number = coborrower_id_number;
    }

    public String getCoborrower_phone() {
        return coborrower_phone;
    }

    public void setCoborrower_phone(String coborrower_phone) {
        this.coborrower_phone = coborrower_phone;
    }

    public String getSurety_phone() {
        return surety_phone;
    }

    public void setSurety_phone(String surety_phone) {
        this.surety_phone = surety_phone;
    }

    public String getTrueip_address() {
        return trueip_address;
    }

    public void setTrueip_address(String trueip_address) {
        this.trueip_address = trueip_address;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
    }

    public String getHouse_property() {
        return house_property;
    }

    public void setHouse_property(String house_property) {
        this.house_property = house_property;
    }

    public String getContact2_id_number() {
        return contact2_id_number;
    }

    public void setContact2_id_number(String contact2_id_number) {
        this.contact2_id_number = contact2_id_number;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public YearIncomeEnum getAnnual_income() {
        return annual_income;
    }

    public void setAnnual_income(YearIncomeEnum annual_income) {
        this.annual_income = annual_income;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getSurety_id_number() {
        return surety_id_number;
    }

    public void setSurety_id_number(String surety_id_number) {
        this.surety_id_number = surety_id_number;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getContact1_mobile() {
        return contact1_mobile;
    }

    public void setContact1_mobile(String contact1_mobile) {
        this.contact1_mobile = contact1_mobile;
    }

    public String getAccount_phone() {
        return account_phone;
    }

    public void setAccount_phone(String account_phone) {
        this.account_phone = account_phone;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getQq_number() {
        return qq_number;
    }

    public void setQq_number(String qq_number) {
        this.qq_number = qq_number;
    }

    public String getMonthly_income() {
        return monthly_income;
    }

    public void setMonthly_income(String monthly_income) {
        this.monthly_income = monthly_income;
    }

    public String getApply_province() {
        return apply_province;
    }

    public void setApply_province(String apply_province) {
        this.apply_province = apply_province;
    }

    public String getSurety_mobile() {
        return surety_mobile;
    }

    public void setSurety_mobile(String surety_mobile) {
        this.surety_mobile = surety_mobile;
    }

    public ContactRelationsEnum getContact4_relation() {
        return contact4_relation;
    }

    public void setContact4_relation(ContactRelationsEnum contact4_relation) {
        this.contact4_relation = contact4_relation;
    }

    public String getContact5_mobile() {
        return contact5_mobile;
    }

    public void setContact5_mobile(String contact5_mobile) {
        this.contact5_mobile = contact5_mobile;
    }

    public String getLoan_term() {
        return loan_term;
    }

    public void setLoan_term(String loan_term) {
        this.loan_term = loan_term;
    }

    public String getAccount_mobile() {
        return account_mobile;
    }

    public void setAccount_mobile(String account_mobile) {
        this.account_mobile = account_mobile;
    }

    public String getOrganization_address() {
        return organization_address;
    }

    public void setOrganization_address(String organization_address) {
        this.organization_address = organization_address;
    }

    public String getContact3_mobile() {
        return contact3_mobile;
    }

    public void setContact3_mobile(String contact3_mobile) {
        this.contact3_mobile = contact3_mobile;
    }

    public WorkExperienceEnum getWork_time() {
        return work_time;
    }

    public void setWork_time(WorkExperienceEnum work_time) {
        this.work_time = work_time;
    }

    public String getContact3_id_number() {
        return contact3_id_number;
    }

    public void setContact3_id_number(String contact3_id_number) {
        this.contact3_id_number = contact3_id_number;
    }

    public String getContact3_name() {
        return contact3_name;
    }

    public void setContact3_name(String contact3_name) {
        this.contact3_name = contact3_name;
    }

    public String getCoborrower_name() {
        return coborrower_name;
    }

    public void setCoborrower_name(String coborrower_name) {
        this.coborrower_name = coborrower_name;
    }

    public String getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(String loan_date) {
        this.loan_date = loan_date;
    }

    public String getApplyer_type() {
        return applyer_type;
    }

    public void setApplyer_type(String applyer_type) {
        this.applyer_type = applyer_type;
    }

    public String getIs_cross_loan() {
        return is_cross_loan;
    }

    public void setIs_cross_loan(String is_cross_loan) {
        this.is_cross_loan = is_cross_loan;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSurety_company_address() {
        return surety_company_address;
    }

    public void setSurety_company_address(String surety_company_address) {
        this.surety_company_address = surety_company_address;
    }

    public String getContact2_name() {
        return contact2_name;
    }

    public void setContact2_name(String contact2_name) {
        this.contact2_name = contact2_name;
    }

    public String getResp_detail_type() {
        return resp_detail_type;
    }

    public void setResp_detail_type(String resp_detail_type) {
        this.resp_detail_type = resp_detail_type;
    }

    public String getApply_city() {
        return apply_city;
    }

    public void setApply_city(String apply_city) {
        this.apply_city = apply_city;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getSurety_home_address() {
        return surety_home_address;
    }

    public void setSurety_home_address(String surety_home_address) {
        this.surety_home_address = surety_home_address;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public ContactRelationsEnum getContact5_relation() {
        return contact5_relation;
    }

    public void setContact5_relation(ContactRelationsEnum contact5_relation) {
        this.contact5_relation = contact5_relation;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getContact1_name() {
        return contact1_name;
    }

    public void setContact1_name(String contact1_name) {
        this.contact1_name = contact1_name;
    }

    public String getContact4_id_number() {
        return contact4_id_number;
    }

    public void setContact4_id_number(String contact4_id_number) {
        this.contact4_id_number = contact4_id_number;
    }

    public ContactRelationsEnum getContact2_relation() {
        return contact2_relation;
    }

    public void setContact2_relation(ContactRelationsEnum contact2_relation) {
        this.contact2_relation = contact2_relation;
    }

    public String getCoborrower_mobile() {
        return coborrower_mobile;
    }

    public void setCoborrower_mobile(String coborrower_mobile) {
        this.coborrower_mobile = coborrower_mobile;
    }

    public String getApply_channel() {
        return apply_channel;
    }

    public void setApply_channel(String apply_channel) {
        this.apply_channel = apply_channel;
    }

    public String getContact4_name() {
        return contact4_name;
    }

    public void setContact4_name(String contact4_name) {
        this.contact4_name = contact4_name;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCoborrower_company_address() {
        return coborrower_company_address;
    }

    public void setCoborrower_company_address(String coborrower_company_address) {
        this.coborrower_company_address = coborrower_company_address;
    }

    public String getGraduate_school() {
        return graduate_school;
    }

    public void setGraduate_school(String graduate_school) {
        this.graduate_school = graduate_school;
    }

    public ContactRelationsEnum getContact1_relation() {
        return contact1_relation;
    }

    public void setContact1_relation(ContactRelationsEnum contact1_relation) {
        this.contact1_relation = contact1_relation;
    }

    public String getContact4_mobile() {
        return contact4_mobile;
    }

    public void setContact4_mobile(String contact4_mobile) {
        this.contact4_mobile = contact4_mobile;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getContact2_mobile() {
        return contact2_mobile;
    }

    public void setContact2_mobile(String contact2_mobile) {
        this.contact2_mobile = contact2_mobile;
    }

    @Override
    /**
     * param='value'&param1='value1'
     */
    public String toString() {
        StringBuilder postBody=new StringBuilder();
        for(Field param:this.getClass().getDeclaredFields()){
            String paramName=param.getName();
            Object value=null;
            try {
                value=param.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if(value!=null){
                postBody.append(paramName).append("=").append(value.toString()).append("&");
            }
        }
        return StringUtils.isNotBlank(postBody.toString())?postBody.deleteCharAt(postBody.length() - 1).toString():"";
    }
}
