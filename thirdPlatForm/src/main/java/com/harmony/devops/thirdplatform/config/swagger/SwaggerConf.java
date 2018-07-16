package com.harmony.devops.thirdplatform.config.swagger;

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
    public Docket lvApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("lvApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.thirdOperator.controller")) // 拦截的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.trade.controller"))
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("commonApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.harmony.devops.thirdplatform.api")) // 拦截的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.trade.controller"))
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    @Bean
    public Docket innerApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("innerApi")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.inner")) // 拦截的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.zhengtou.cf.trade.controller"))
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("郑投网")// 标题
                .description("消费金融三方服务API")// 描述
//                .termsOfServiceUrl("http://www.zhengtou.com")//
//                .contact(new Contact("xiaohui", "http://www.zhengtou.com",
//                        "331592721@qq.com"))// 联系
                .version("1.0")// 版本
                .build();
    }
}