package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.bussiness;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

import java.util.List;

/**
 * 任务数据（电商）
 * @author 葛文镇
 */
public class BussinessTaskDataVO extends PeakBaseVo{
    private BaseInfoVO base_info;
    private AccountInfoVO account_info;
    private List<String> receiver_list;
    private List<OrderListVO> order_list;

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

    public List<String> getReceiver_list() {
        return receiver_list;
    }

    public void setReceiver_list(List<String> receiver_list) {
        this.receiver_list = receiver_list;
    }

    public List<OrderListVO> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListVO> order_list) {
        this.order_list = order_list;
    }
}
