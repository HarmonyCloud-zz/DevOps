package com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class YiBaoBaseRequest {
    public Map<String, String> parseMap() throws IllegalArgumentException, IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>();
        for (Field param : this.getClass().getFields()) {
            String paramName = param.getName();
            if(param.get(this)!=null){
                map.put(paramName, param.get(this).toString());
            }
        }
        return map;
    }
}
