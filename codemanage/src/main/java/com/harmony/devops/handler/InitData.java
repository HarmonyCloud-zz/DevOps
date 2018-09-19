package com.harmony.devops.handler;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目初始化数据准备
 * @author 葛文镇
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
        }
    }
}
