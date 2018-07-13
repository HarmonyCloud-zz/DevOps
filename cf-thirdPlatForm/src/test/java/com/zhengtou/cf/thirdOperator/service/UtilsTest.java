package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.common.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 工具测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
    @Test
    public void getSysDateTimeString(){
        System.out.println(DateUtils.getSysDateTimeString());
    }
}
