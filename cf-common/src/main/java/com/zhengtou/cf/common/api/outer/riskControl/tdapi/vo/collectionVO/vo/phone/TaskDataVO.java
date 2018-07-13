package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

import java.util.List;

/**
 * 任务数据信息
 * @author 葛文镇
 */
public class TaskDataVO extends PeakBaseVo{
    private BaseInfoVO base_info;
    private AccountInfoVO account_info;
    private PackageInfoVO package_info;
    private List<CallInfoVO> call_info;
    private List<SmsInfoVO> sms_info;
    private List<DataInfoVO> data_info;
    private List<PaymentInfoVO> payment_info;
    private List<BillInfoVO> bill_info;
    private List<PointInfoVO> package_usage;
    private List<FamilyInfoVO> family_info;

    public BaseInfoVO getBase_info() {
        return base_info;
    }

    public void setBase_info(BaseInfoVO base_info) {
        this.base_info = base_info;
    }

    public AccountInfoVO getAccount_info() {
        return account_info;
    }

    public void setAccount_info(AccountInfoVO account_info) {
        this.account_info = account_info;
    }

    public PackageInfoVO getPackage_info() {
        return package_info;
    }

    public void setPackage_info(PackageInfoVO package_info) {
        this.package_info = package_info;
    }

    public List<CallInfoVO> getCall_info() {
        return call_info;
    }

    public void setCall_info(List<CallInfoVO> call_info) {
        this.call_info = call_info;
    }

    public List<SmsInfoVO> getSms_info() {
        return sms_info;
    }

    public void setSms_info(List<SmsInfoVO> sms_info) {
        this.sms_info = sms_info;
    }

    public List<DataInfoVO> getData_info() {
        return data_info;
    }

    public void setData_info(List<DataInfoVO> data_info) {
        this.data_info = data_info;
    }

    public List<PaymentInfoVO> getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(List<PaymentInfoVO> payment_info) {
        this.payment_info = payment_info;
    }

    public List<BillInfoVO> getBill_info() {
        return bill_info;
    }

    public void setBill_info(List<BillInfoVO> bill_info) {
        this.bill_info = bill_info;
    }

    public List<PointInfoVO> getPackage_usage() {
        return package_usage;
    }

    public void setPackage_usage(List<PointInfoVO> package_usage) {
        this.package_usage = package_usage;
    }

    public List<FamilyInfoVO> getFamily_info() {
        return family_info;
    }

    public void setFamily_info(List<FamilyInfoVO> family_info) {
        this.family_info = family_info;
    }
}
