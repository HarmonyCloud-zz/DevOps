package com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.response;

import com.zhengtou.cf.common.api.outer.riskControl.jxlapi.vo.bee.CollectionPhoneJDContentVO;

/**
 * 数据采集获取token返回结构
 * @author 葛文镇
 */
public class CollectionPhoneJDTokenResponse extends BaseCollectionResponse{
    private CollectionPhoneJDContentVO data;

    public CollectionPhoneJDContentVO getData() {
        return data;
    }

    public void setData(CollectionPhoneJDContentVO data) {
        this.data = data;
    }
}
