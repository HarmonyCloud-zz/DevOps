package com.zhengtou.cf.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf")) // 拦截的包路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    @Bean
    public Docket outApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("outApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.api.app.v01")) // 拦截的包路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    @Bean
    public Docket mobileApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("mobileApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.api.app.v02")) // 拦截的包路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    @Bean
    public Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("innerApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf")) // 拦截的包路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("郑投网")// 标题
                .description("消费金融API")// 描述
//                .termsOfServiceUrl("http://www.zhengtou.com")//
//                .contact(new Contact("xiaohui", "http://www.zhengtou.com",
//                        "331592721@qq.com"))// 联系
                .version("1.0")// 版本
                .build();
    }
}