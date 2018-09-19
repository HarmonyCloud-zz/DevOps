package com.harmony.devops.context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConf {
    public static String password_key="123!@#";
    public static String password_init="Ab123456";
    private String file_path="/home/mysql/data/source/";
    private String file_show_path="http://jd.lestuffs.com/source/";

    @Bean(name = "filePath")
    public String getFilePath() {
        return file_path;
    }

    @Bean(name = "fileShowPath")
    public String getFileShowPath() {
        return file_show_path;
    }
}
