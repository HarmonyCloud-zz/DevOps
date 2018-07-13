package com.zhengtou.cf.common.utils;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;

import java.text.DecimalFormat;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 金额处理工具类
 */
public class MoneyUtil {
    private static final char [] ChineseNum ={'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
    private static final char [] ChineseUnit={'里','分','角','元','拾','佰','仟','万','拾','佰','仟','亿','拾','佰','仟'};

    /**
     * 小写转大写
     */
    private static String moneyToChinessRMB(String moneyNum) throws BaseException{
        String res="";
        int i=3;
        int len=moneyNum.length();
        if(len>12){
            throw new BaseException(RtnResultEnum.E010000);
        }
        if("0".equals(moneyNum))
            return "零元";
        for(len--;len>=0;len--){
            res=ChineseUnit[i++]+res;
            int num=Integer.parseInt(moneyNum.charAt(len)+"");
            res=ChineseNum[num]+res;
        }
        return res.replaceAll("零[拾佰仟]", "零")
                .replaceAll("零+亿", "亿").replaceAll("零+万", "万")
                .replaceAll("零+元", "元").replaceAll("零+", "零");

    }

    private static String arabNumToChineseRMB(double moneyNum) throws BaseException{
        String res="";
        String money=String.format("%.3f",moneyNum);
        //System.out.println(money);
        int i=0;
        if(moneyNum==0.0)
            return "零元";
        String inte = money.split("\\.")[0];
        int deci=Integer.parseInt(money.split("\\.")[1].substring(0, 3));
        while(deci>0){
            res=ChineseUnit[i++]+res;
            res=ChineseNum[deci%10]+res;
            deci/=10;
        }
        res=res.replaceAll("零[里分角]", "零");
        if(i<3)
            res="零"+res;
        res=res.replaceAll("零+", "零");
        if(res.endsWith("零"))
            res=res.substring(0, res.length()-1);
        return moneyToChinessRMB(inte)+res;
    }

    public static String arabNumToChineseRMB(String moneyNum) throws BaseException{
        return arabNumToChineseRMB(Double.parseDouble(moneyNum));
    }

    /**
     * 格式化金额类型
     */
    public static long moneyToLong(String money) {
        if (money == null || !money.matches("[\\d]+(|\\.[\\d]{1,2})")) {
            return 0;
        }
        String[] moneyTemp = money.split("\\.");
        // 计算整数部分
        long result = Long.parseLong(moneyTemp[0]) * 100;

        // 计算小数部分
        if (moneyTemp.length == 2) {
            if (moneyTemp[1].length() == 2) {
                result += Long.parseLong(moneyTemp[1]);
            } else {
                result += Long.parseLong(moneyTemp[1] + "0");
            }
        }
        return result;
    }

    /**
     * 格式化金额类型
     */
    public static long moneyToLong(double money) {
        String temp=money+"";
        return moneyToLong(temp);
    }

    /**
     * 格式化金额类型
     */
    public static String moneyToString(long fee) {
        return new DecimalFormat("0.00").format(fee / 100.00);
    }
}
