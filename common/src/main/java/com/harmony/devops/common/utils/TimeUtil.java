package com.harmony.devops.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 时间处理工具类
 */
public class TimeUtil {
    private static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_FORMAT_N="yyyyMMddHHmmss";
    /**
     * 还款日生成
     */
    public static long getRepaymentDay(int termNo){
        Long res = 0L;

        Date date =new Date(System.currentTimeMillis());//当前日期
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.MONTH, termNo);//月份减一为-1，加一为1
        res =calendar.getTime().getTime();
        //System.out.println(sdf.format());//输出格式化的日期
        return res;
    }

    /**
     * 根据期数试算天数
     */
    public static long getRepaymentDayNum(int termNo){
        Long end = 0L;
        Long start=0L;

        Date date =new Date(System.currentTimeMillis());//当前日期
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        start=calendar.getTime().getTime();
        calendar.add(Calendar.MONTH, termNo);//月份减一为-1，加一为1
        end =calendar.getTime().getTime();
        //System.out.println(sdf.format());//输出格式化的日期
        return (end-start)/1000/60/60/24;
    }

    /**
     * 格式化时间类型
     */
    public static String timeToString(Date time) {
        if (time == null) {
            return "";
        } else {
            return TimeUtil.timeToString(time, DEFAULT_TIME_FORMAT);
        }
    }

    /**
     * 格式化时间类型
     */
    public static String timeToString(Date time, String pattern) {
        return TimeUtil.timeToString(time, new SimpleDateFormat(pattern));
    }

    /**
     * 格式化时间类型
     */
    public static String timeToString(long longTime) {
        return timeToString(new Date(longTime), DEFAULT_TIME_FORMAT);
    }

    /**
     * 格式化时间类型
     */
    public static String timeToStringYMD(long longTime) {
        return timeToString(new Date(longTime), DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化时间，无分隔符
     */
    public static String timeToStringMS(){
        return timeToString(System.currentTimeMillis(),DEFAULT_DATE_FORMAT_N);
    }

    /**
     * 格式化时间，无分隔符
     */
    public static String timeToStringDD(){
        return timeToString(System.currentTimeMillis(),DEFAULT_DATE_FORMAT).replaceAll("-","");
    }

    /**
     * 格式化时间类型
     */
    public static String timeToString(long longTime, String pattern) {
        return TimeUtil.timeToString(new Date(longTime), new SimpleDateFormat(pattern));
    }

    private static String timeToString(Date time, SimpleDateFormat format) {
        return time == null ? null : format.format(time);
    }

    /**
     * 格式化时间类型
     */
    public static long timeToLong(String time) {
        try {
            return new SimpleDateFormat(DEFAULT_TIME_FORMAT).parse(time).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化时间类型
     */
    public static Date timeToDate(long longTime) {
        return new Date(longTime);
    }

    /**
     * 格式化时间类型
     */
    public static Date timeToDate(String strTime) {
        try {
            return new SimpleDateFormat(DEFAULT_TIME_FORMAT).parse(strTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化时间类型(精确到天)
     */
    public static String dateToString(Date time) {
        return TimeUtil.timeToString(time, DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化时间类型(精确到天)
     */
    public static String dateToString(long longTime) {
        return timeToString(new Date(longTime), DEFAULT_DATE_FORMAT);
    }

    /**
     * 格式化时间类型(精确到天)
     */
    public static long dateToLong(String date) {
        if (StringUtils.isBlank(date)) {
            return 0;
        }
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化时间类型(+1天)
     */
    public static long dateToLongAddOne(String date) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date).getTime() - 1 + 86400000L;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 格式化时间类型(+n天)
     */
    public static long dateToLongAddN(int n) {
        Long res = 0L;
        Date date =new Date(System.currentTimeMillis());//当前日期
        Calendar calendar = Calendar.getInstance();//日历对象
        calendar.setTime(date);//设置当前日期
        calendar.add(Calendar.DAY_OF_YEAR,  n);//月份减一为-1，加一为1
        res =calendar.getTime().getTime();
        return res;
    }

    /**
     * 格式化时间类型(精确到天)
     */
    public static Date dateToDate(String date) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 格式化时间类型(精确到天)
     */
    public static Date dateToDateAddOne(String date) {
        return new Date(dateToLongAddOne(date));
    }

    public static String mapToString(Map<String, String> args) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : args.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
