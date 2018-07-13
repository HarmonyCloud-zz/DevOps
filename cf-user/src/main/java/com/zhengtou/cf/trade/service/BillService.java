package com.zhengtou.cf.trade.service;

import com.zhengtou.cf.common.api.outer.cl.cls.response.LoanItem;
import com.zhengtou.cf.trade.pojo.entity.bill.BillEntity;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 借据服务
 */
public interface BillService {
    /**
     * 获取所有需改动借据信息
     */
    List<BillEntity> getAllNormalBill();
    /**
     * 保存借据信息
     */
    void saveBill(String contactNo,LoanItem loanItem);
}
