package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;
/**
 * 聚信立采集数据权限认证
 * @author 葛文镇
 */
public class AuthCollectionResponse {
    private String success;
    private JxlCollectionContentVO data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public JxlCollectionContentVO getData() {
        return data;
    }

    public void setData(JxlCollectionContentVO data) {
        this.data = data;
    }

    public class JxlCollectionContentVO{
        private String type;
        private String process_code;
        private String finish;
        private String next_datasource;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProcess_code() {
            return process_code;
        }

        public void setProcess_code(String process_code) {
            this.process_code = process_code;
        }

        public String getFinish() {
            return finish;
        }

        public void setFinish(String finish) {
            this.finish = finish;
        }

        public String getNext_datasource() {
            return next_datasource;
        }

        public void setNext_datasource(String next_datasource) {
            this.next_datasource = next_datasource;
        }
    }
}
