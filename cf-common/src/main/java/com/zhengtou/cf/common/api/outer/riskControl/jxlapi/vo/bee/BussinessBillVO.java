package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee;

import java.util.List;

public class BussinessBillVO {
    private String update_time;
    private String order_id;
    private String bill_type;
    private String delivery_fee;
    private String bill_title;
    private String receiver_addr;
    private String delivery_type;
    private String is_success;
    private String trans_time;
    private String total_price;
    private String receiver_cell_phone;
    private List<ItemVO> items;
    private String zipcode;
    private String receiver_title;
    private String payment_type;
    private String receiver_name;
    private String receiver_phone;

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getBill_title() {
        return bill_title;
    }

    public void setBill_title(String bill_title) {
        this.bill_title = bill_title;
    }

    public String getReceiver_addr() {
        return receiver_addr;
    }

    public void setReceiver_addr(String receiver_addr) {
        this.receiver_addr = receiver_addr;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }

    public String getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(String trans_time) {
        this.trans_time = trans_time;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getReceiver_cell_phone() {
        return receiver_cell_phone;
    }

    public void setReceiver_cell_phone(String receiver_cell_phone) {
        this.receiver_cell_phone = receiver_cell_phone;
    }

    public List<ItemVO> getItems() {
        return items;
    }

    public void setItems(List<ItemVO> items) {
        this.items = items;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getReceiver_title() {
        return receiver_title;
    }

    public void setReceiver_title(String receiver_title) {
        this.receiver_title = receiver_title;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public class ItemVO{
        private String trans_time;
        private String product_price;
        private String product_cnt;
        private String product_name;

        public String getTrans_time() {
            return trans_time;
        }

        public void setTrans_time(String trans_time) {
            this.trans_time = trans_time;
        }

        public String getProduct_price() {
            return product_price;
        }

        public void setProduct_price(String product_price) {
            this.product_price = product_price;
        }

        public String getProduct_cnt() {
            return product_cnt;
        }

        public void setProduct_cnt(String product_cnt) {
            this.product_cnt = product_cnt;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }
    }
}
