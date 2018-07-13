package com.zhengtou.cf.utils;

import com.zhengtou.cf.api.pay.yibaoapi.util.merchant.YbMerchantService;
import com.zhengtou.cf.common.utils.TimeUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LvyueUtils {
    @Test
    public void lvyueTest() throws IOException {
        List<String> data = FileUtils.readLines(
                new File(YbMerchantService.class.getClassLoader().getResource("/")
                        .getPath().replaceAll("%20", " ")
                        + "ThirdService/com/zhengtou/cf/logs"), "utf8");
        List<String> orderList=new ArrayList<>();
        List<String> riskList=new ArrayList<>();
        List<String> rePayList=new ArrayList<>();
        for(String temp:data){
            if(temp.contains("save personalInfo result:")){
                orderList.add(temp);
            }
            if(temp.contains("save personalRisk result:")){
                riskList.add(temp);
            }
            if(temp.contains("save pay result:")){
                rePayList.add(temp);
            }
        }
    }

    private String logFind(String resource,String pattern){
        return null;
    }

    @Test
    public void test(){
        System.out.println(TimeUtil.timeToStringYMD(TimeUtil.dateToLongAddN(0)));
        System.out.println(TimeUtil.timeToStringYMD(TimeUtil.dateToLongAddN(-1)));
        System.out.println(TimeUtil.timeToStringYMD(TimeUtil.dateToLongAddN(1)));
    }
}
