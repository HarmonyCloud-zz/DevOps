package com.zhengtou.cf.common.utils;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.common.annotation.json.JsonFilterAnnotation;
import com.zhengtou.cf.common.api.outer.cl.apply.request.HouseLoanApplyVerifyRequest;
import com.zhengtou.cf.common.pojo.PeakBaseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * json工具类
 *
 * @author 葛文镇
 */
public class JsonUtils extends JSON{
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    public static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     */
    private static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰转下划线大写
     */
    private static String camelToUnderlineBlock(String name) {
        StringBuilder result = new StringBuilder();
        if(name != null && name.length() > 0) {
            result.append(name.substring(0,1).toUpperCase());
            for(int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if(s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append(UNDERLINE);
                }
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 对象转json，长亮规则，下划线大写
     */
    public static <T extends PeakBaseVo> String applyObjectToJsonStringCl(T o){
        StringBuffer json=new StringBuffer("{");
        for(Field f:o.getClass().getDeclaredFields()){
            JsonFilterAnnotation jsonFilterAnnotation=f.getAnnotation(JsonFilterAnnotation.class);
            if(jsonFilterAnnotation==null || jsonFilterAnnotation.serialize()){
                try {
                    f.setAccessible(true);
                    Object  value = f.get(o);
                    if(value!=null){
                        if(value instanceof List){
                            if(!((List) value).isEmpty()){
                                if(((List) value).get(0) instanceof PeakBaseVo){
                                    json.append("\"" + camelToUnderlineBlock(f.getName()) + "\":");
                                    json.append(objectToJsonStringClList((List<T>) value));
                                    json.append(",");
                                }else{
                                    json.append("\"" + camelToUnderlineBlock(f.getName()) + "\":");
                                    json.append( value.toString());
                                    json.append(",");
                                }
                            }
                        }else if( value instanceof PeakBaseVo){
                            if(value!=null){
                                json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                json.append(applyObjectToJsonStringClObject((T)value));
                                json.append(",");
                            }
                        }else if(value instanceof Enum){
                            if(value!=null){
                                json.append(camelToUnderlineBlock("\""+f.getName())+"\"");
                                json.append(":\""+((Enum) value).name()+"\"");
                                json.append(",");
                            }
                        }else{
                            if(f.getType().getName().equals("double")){
                                if(new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()!=0.0){
                                    json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                    json.append(new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                                    json.append(",");
                                }
                            }else if(f.getType().getName().equals("int")){
                                if(Integer.parseInt(value.toString())!=0) {
                                    json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                    json.append(Integer.parseInt(value.toString()));
                                    json.append(",");
                                }
                            }else{
                                json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                json.append("\""+value.toString()+"\"");
                                json.append(",");
                            }
                        }
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    logger.info("JsonUtils:解析错误");
                }
            }
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("}");
        return json.toString().replaceAll(",}","}");
    }

    /**
     * 做list一层解析
     */
    private static  <T extends PeakBaseVo> String applyObjectToJsonStringClList(List<T> lo) throws NoSuchFieldException, IllegalAccessException {
        StringBuffer json=new StringBuffer("[");
        for(T temp :lo){
            json.append(applyObjectToJsonStringClObject(temp));
            json.append(",");
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("]");
        return json.toString().replaceAll(",]","]");
    }

    /**
     * 做object一层解析
     */
    private static  <T extends PeakBaseVo> String applyObjectToJsonStringClObject(T t ) throws NoSuchFieldException, IllegalAccessException {
        StringBuffer json=new StringBuffer("{");
        for(Field f:t.getClass().getDeclaredFields()){
            JsonFilterAnnotation jsonFilterAnnotation=f.getAnnotation(JsonFilterAnnotation.class);
            if(jsonFilterAnnotation==null || jsonFilterAnnotation.serialize()){
                f.setAccessible(true);
                Object value=f.get(t);
                if(value!=null){
                    if(value instanceof List){
                        json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                        json.append(applyObjectToJsonStringClList((List<T>)value));
                        json.append(",");
                    }else if(value instanceof PeakBaseVo){
                        json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                        json.append(applyObjectToJsonStringClObject((T)value));
                        json.append(",");
                    }else if(value instanceof Enum){
                        if(value!=null){
                            json.append(camelToUnderlineBlock("\""+f.getName())+"\"");
                            json.append(":\""+((Enum) value).name()+"\"");
                            json.append(",");
                        }
                    }else if(StringUtils.isNotBlank(value.toString())){
                        if(f.getType().getName().equals("double")){
                            if(new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()!=0.0) {
                                json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                json.append(new BigDecimal(value.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + ",");
                            }
                        }else if(f.getType().getName().equals("int")){
                            if(Integer.parseInt(value.toString())!=0) {
                                json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                                json.append(Integer.parseInt(value.toString()) + ",");
                            }
                        }else{
                            json.append(camelToUnderlineBlock("\""+f.getName())+"\":");
                            json.append("\""+value.toString()+"\",");
                        }
                    }
                }
            }
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("}");
        return json.toString().replaceAll(",}","}");
    }


    /**
     * 对象转json，长亮规则
     */
    public static <T extends PeakBaseVo> String objectToJsonStringCl(T o){
        StringBuffer json=new StringBuffer("{");
        for(Field f:o.getClass().getDeclaredFields()){
            JsonFilterAnnotation jsonFilterAnnotation=f.getAnnotation(JsonFilterAnnotation.class);
            if(jsonFilterAnnotation==null || jsonFilterAnnotation.serialize()){
                try {
                    f.setAccessible(true);
                    Object  value = f.get(o);
                    if(value!=null){
                        if(value instanceof List){
                            if(!((List) value).isEmpty()) {
                                if(((List) value).get(0) instanceof PeakBaseVo){
                                    json.append("\"" + f.getName() + "\":");
                                    json.append(objectToJsonStringClList((List<T>) value));
                                    json.append(",");
                                }else{
                                    json.append("\"" + f.getName() + "\":");
                                    json.append( value.toString());
                                    json.append(",");
                                }
                            }
                        }else if( value instanceof PeakBaseVo){
                            if(value!=null){
                                json.append("\""+f.getName()+"\":");
                                json.append(objectToJsonStringClObject((T)value));
                                json.append(",");
                            }
                        }else if(value instanceof Enum){
                            if(value!=null){
                                json.append("\""+f.getName()+"\"");
                                json.append(":\""+((Enum) value).name()+"\"");
                                json.append(",");
                            }
                        }else{
                            json.append("\""+f.getName()+"\":");
                            json.append("\""+value.toString()+"\"");
                            json.append(",");
                        }
                    }
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    logger.info("JsonUtils:解析错误");
                }
            }
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("}");
        return json.toString().replaceAll(",}","}");
    }

    /**
     * 做list一层解析
     */
    private static  <T extends PeakBaseVo> String objectToJsonStringClList(List<T> lo) throws NoSuchFieldException, IllegalAccessException {
        StringBuffer json=new StringBuffer("[");
        for(T temp :lo){
            json.append(objectToJsonStringClObject(temp));
            json.append(",");
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("]");
        return json.toString().replaceAll(",]","]");
    }

    /**
     * 做object一层解析
     */
    private static  <T extends PeakBaseVo> String objectToJsonStringClObject(T t ) throws NoSuchFieldException, IllegalAccessException {
        StringBuffer json=new StringBuffer("{");
        for(Field f:t.getClass().getDeclaredFields()){
            JsonFilterAnnotation jsonFilterAnnotation=f.getAnnotation(JsonFilterAnnotation.class);
            if(jsonFilterAnnotation==null || jsonFilterAnnotation.serialize()){
                f.setAccessible(true);
                Object value=f.get(t);
                if(value!=null){
                    if(value instanceof List){
                        json.append("\""+f.getName()+"\":");
                        json.append(objectToJsonStringClList((List<T>)value));
                        json.append(",");
                    }else if(value instanceof PeakBaseVo){
                        json.append("\""+f.getName()+"\":");
                        json.append(objectToJsonStringClObject((T)value));
                        json.append(",");
                    }else if(value instanceof Enum){
                        if(value!=null){
                            json.append("\""+f.getName()+"\"");
                            json.append(":\""+((Enum) value).name()+"\"");
                            json.append(",");
                        }
                    }else if(StringUtils.isNotBlank(value.toString())){
                        json.append("\""+f.getName()+"\"");
                        json.append(":\""+value.toString()+"\",");
                    }
                }
            }
        }
        json.replace(json.length()-1,json.length(),"");
        json.append("}");
        return json.toString().replaceAll(",}","}");
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        HouseLoanApplyVerifyRequest buildAppNoRequest=new HouseLoanApplyVerifyRequest();
        buildAppNoRequest.setTaskNo("1111");
        System.out.println(JSON.toJSONString(buildAppNoRequest));
        System.out.println(applyObjectToJsonStringCl(buildAppNoRequest));
    }
}
