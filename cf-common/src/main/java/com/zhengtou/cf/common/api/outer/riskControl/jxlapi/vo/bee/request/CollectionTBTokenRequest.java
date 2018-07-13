package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.request;

public class CollectionTBTokenRequest {
    private TempVO apply_info;

    public CollectionTBTokenRequest(String name,String idCard,String phone){
        this.apply_info=new TempVO(name,idCard,phone);
    }

    public TempVO getApply_info() {
        return apply_info;
    }

    public void setApply_info(TempVO apply_info) {
        this.apply_info = apply_info;
    }

    public class TempVO{
        private BasicInfo basic_info;

        public TempVO(String name,String idCard,String phone){
            this.basic_info=new BasicInfo(name,idCard,phone);
        }

        public BasicInfo getBasic_info() {
            return basic_info;
        }

        public void setBasic_info(BasicInfo basic_info) {
            this.basic_info = basic_info;
        }
    }
}
