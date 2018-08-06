package com.harmony.devops.thirdplatform.api.service;

import com.harmony.devops.user.thirdApi.handler.task.trade.FundsTask;
import com.harmony.devops.user.operator.service.OrgService;
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
