package com.zhengtou.cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@ServletComponentScan
@SpringBootApplication
public class CfThirdPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfThirdPlatformApplication.class, args);
	}
}
