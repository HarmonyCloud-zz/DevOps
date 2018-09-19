package com.harmony.devops.common.api;

import com.alibaba.fastjson.JSON;
import com.harmony.devops.common.exception.BaseException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/28
 * @描述 与外部接口交互request封装
 */
public abstract class BaseRequest {
    public Map<String, Object> parseMap() throws BaseException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Field param : this.getClass().getFields()) {
            String paramName = param.getName();
            try {
                map.put(paramName, param.get(this));
            } catch (IllegalAccessException e) {
                throw new BaseException("IllegalAccessException",e.getMessage());
            }
        }
        return map;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        try {
                for (Map.Entry<String, Object> entry : parseMap().entrySet()) {
                    sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
                }
        } catch (IllegalArgumentException e) {
        } catch (BaseException e){

        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public String toXML() {
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8",
                new XmlFriendlyReplacer("-_", "_")));
        xStreamForRequestPostData.alias("xml", this.getClass());
        // 将要提交给API的数据对象转换成XML格式数据Post给API
        return xStreamForRequestPostData.toXML(this);
    }

    /**
     * get请求数据处理
     * @param map
     * @return
     */
    public static String toGet(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        sb.substring(0, result.length()-1);
        return result;

    }


    public String toJson() {
        String json = JSON.toJSONString(this);
        return json;
    }
}
