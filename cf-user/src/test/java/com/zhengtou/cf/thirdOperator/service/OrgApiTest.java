package com.zhengtou.cf.thirdOperator.service;

import com.zhengtou.cf.handler.task.trade.FundsTask;
import com.zhengtou.cf.operator.service.OrgService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class OrgApiTest {

    @Autowired
    OrgService orgService;
    @Autowired
    FundsTask task;

    @Test
    public void test(){
    }
}
