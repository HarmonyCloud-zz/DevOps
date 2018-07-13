package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.*;

import java.util.List;

/**
 * 聚信立电商结构
 * @author 葛文镇
 */
public class GetBussinessResponse extends BaseResponse{
    private List<RequestArgsVO> request_args;
    private List<BussinessTransactionsVO> transactions;

    public List<RequestArgsVO> getRequest_args() {
        return request_args;
    }

    public void setRequest_args(List<RequestArgsVO> request_args) {
        this.request_args = request_args;
    }

    public List<BussinessTransactionsVO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BussinessTransactionsVO> transactions) {
        this.transactions = transactions;
    }

    public class BussinessTransactionsVO{
        private String token;
        private String version;
        private String datasource;
        private String juid;
        private BasicVO basic;
        private List<AddressVO> address;
        private List<BussinessBillVO> transactions;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDatasource() {
            return datasource;
        }

        public void setDatasource(String datasource) {
            this.datasource = datasource;
        }

        public String getJuid() {
            return juid;
        }

        public void setJuid(String juid) {
            this.juid = juid;
        }

        public BasicVO getBasic() {
            return basic;
        }

        public void setBasic(BasicVO basic) {
            this.basic = basic;
        }

        public List<AddressVO> getAddress() {
            return address;
        }

        public void setAddress(List<AddressVO> address) {
            this.address = address;
        }

        public List<BussinessBillVO> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<BussinessBillVO> transactions) {
            this.transactions = transactions;
        }
    }
}
