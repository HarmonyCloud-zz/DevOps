package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.honeyjar.response;

/**
 * 拿到蜜罐token返回参数封装
 * @author 葛文镇
 */
public class GetHoneyTokenResponse extends HoneyBaseResponse{
    /**
     * token类型,过期时间
     */
    private  Content data;

    public Content getData() {
        return data;
    }

    public void setData(Content data) {
        this.data = data;
    }

    public class Content{
        private String access_token;
        private String create_time;
        private String expire_in;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getExpire_in() {
            return expire_in;
        }

        public void setExpire_in(String expire_in) {
            this.expire_in = expire_in;
        }
    }
}
