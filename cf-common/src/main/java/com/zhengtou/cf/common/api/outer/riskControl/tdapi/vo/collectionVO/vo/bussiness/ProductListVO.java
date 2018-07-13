package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.bussiness;

/**
 * 商品列表
 */
public class ProductListVO {
    //商品名称
    private String product_name;
    //商品单价
    private String product_price;
    //商品数量
    private String product_amount;

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
