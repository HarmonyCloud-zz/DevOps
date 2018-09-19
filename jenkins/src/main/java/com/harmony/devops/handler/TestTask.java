package com.harmony.devops.handler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TestTask {
    @Scheduled(cron="*/5 * * * * ?")
    public void test(){
        System.out.println("111");
    }
}
