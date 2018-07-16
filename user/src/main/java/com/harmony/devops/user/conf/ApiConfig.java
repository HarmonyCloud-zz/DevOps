package com.harmony.devops.user.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对外访问接口配置
 */
@Configuration
public class ApiConfig {
    /**
     * 长亮接口服务配置
     */
    @Bean(name = "clApi")
    public String getClApiConf() {
        return "http://192.168.1.231:8082";
    }

    /**
     * 三方接口服务配置
     */
    @Bean(name = "thirdApi")
    public String getThirdApiConf() {
        return "http://192.168.1.231:8085";
    }

}
