package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee;

public class CollectionPhoneJDContentVO {
    private String token;
    private String cell_phone_num;
    private CollectionDataSourceVO datasource;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCell_phone_num() {
        return cell_phone_num;
    }

    public void setCell_phone_num(String cell_phone_num) {
        this.cell_phone_num = cell_phone_num;
    }

    public CollectionDataSourceVO getDatasource() {
        return datasource;
    }

    public void setDatasource(CollectionDataSourceVO datasource) {
        this.datasource = datasource;
    }
}
