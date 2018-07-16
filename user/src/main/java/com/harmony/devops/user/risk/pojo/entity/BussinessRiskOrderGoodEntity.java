package com.harmony.devops.user.risk.pojo.entity;

import com.zhengtou.cf.common.pojo.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 电商订单商品信息
 * @author 葛文镇
 */
@Entity
@Table(name = "t_risk_buss_order_good")
public class BussinessRiskOrderGoodEntity  extends BaseEntity {
    @ManyToOne
    private BussinessRiskOrderEntity bussinessRiskOrder;
    //商品名称
    private String product_name;
    //商品单价
    private String product_price;
    //商品数量
    private String product_amount;


    public BussinessRiskOrderEntity getBussinessRiskOrder() {
        return bussinessRiskOrder;
    }

    public void setBussinessRiskOrder(BussinessRiskOrderEntity bussinessRiskOrder) {
        this.bussinessRiskOrder = bussinessRiskOrder;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(String product_amount) {
        this.product_amount = product_amount;
    }
}
