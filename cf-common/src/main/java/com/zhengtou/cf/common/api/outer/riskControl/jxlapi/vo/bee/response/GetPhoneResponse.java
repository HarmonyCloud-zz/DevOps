package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.*;

import java.util.List;

/**
 * 聚信立运营商数据
 * @author 葛文镇
 */
public class GetPhoneResponse extends BaseResponse{
    private List<RequestArgsVO> request_args;
    private List<PhoneTransactionsVO> transactions;

    public List<RequestArgsVO> getRequest_args() {
        return request_args;
    }

    public void setRequest_args(List<RequestArgsVO> request_args) {
        this.request_args = request_args;
    }

    public List<PhoneTransactionsVO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<PhoneTransactionsVO> transactions) {
        this.transactions = transactions;
    }

    public class PhoneTransactionsVO {
        private String token;
        private String version;
        private String datasource;
        private String juid;
        private BasicVO basic;
        private List<CallVO> calls;
        private List<NetVO> nets;
        private List<SmsVO> smses;
        private List<PhoneBillVO> transactions;

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

        public List<CallVO> getCalls() {
            return calls;
        }

        public void setCalls(List<CallVO> calls) {
            this.calls = calls;
        }

        public List<NetVO> getNets() {
            return nets;
        }

        public void setNets(List<NetVO> nets) {
            this.nets = nets;
        }

        public List<PhoneBillVO> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<PhoneBillVO> transactions) {
            this.transactions = transactions;
        }
    }
}
