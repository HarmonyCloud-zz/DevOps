package com.zhengtou.cf.thirdOperator.controller.lvyue;

import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public class UserReceiveVO extends PeakBaseVo {
    public UserReceiveVO() {
    }
    //订单标识
    private String order_id;
    //真是姓名
    private String user_name;
    //手机号
    private String user_phone;
    //身份证号
    private String user_id_card;
    //商品名称
    private String goods_name;
    //商品类目
    private String goods_order_attr;
    //商品进价
    private String goods_pur_price;
    //商品总价
    private String goods_price;
    //总租金
    private String order_gross_rent;
    //月租
    private String order_monthly_rent;
    //租期
    private String order_tenancy_term;
    //信用分
    private String credit_score;
    //下单时间
    private String create_time;
    //订单来源
    private String order_comefrom;
    //订单状态
    private String order_status;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_id_card() {
        return user_id_card;
    }

    public void setUser_id_card(String user_id_card) {
        this.user_id_card = user_id_card;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_order_attr() {
        return goods_order_attr;
    }

    public void setGoods_order_attr(String goods_order_attr) {
        this.goods_order_attr = goods_order_attr;
    }

    public String getGoods_pur_price() {
        return goods_pur_price;
    }

    public void setGoods_pur_price(String goods_pur_price) {
        this.goods_pur_price = goods_pur_price;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getOrder_gross_rent() {
        return order_gross_rent;
    }

    public void setOrder_gross_rent(String order_gross_rent) {
        this.order_gross_rent = order_gross_rent;
    }

    public String getOrder_monthly_rent() {
        return order_monthly_rent;
    }

    public void setOrder_monthly_rent(String order_monthly_rent) {
        this.order_monthly_rent = order_monthly_rent;
    }

    public String getOrder_tenancy_term() {
        return order_tenancy_term;
    }

    public void setOrder_tenancy_term(String order_tenancy_term) {
        this.order_tenancy_term = order_tenancy_term;
    }

    public String getCredit_score() {
        return credit_score;
    }

    public void setCredit_score(String credit_score) {
        this.credit_score = credit_score;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOrder_comefrom() {
        return order_comefrom;
    }

    public void setOrder_comefrom(String order_comefrom) {
        this.order_comefrom = order_comefrom;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
