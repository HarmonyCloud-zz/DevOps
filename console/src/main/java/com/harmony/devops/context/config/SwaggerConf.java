package com.harmony.devops.context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
                .apis(RequestHandlerSelectors.basePackage("com.harmony.devops")) // 拦截的包路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("谐云")// 标题
                .description("谐云_devops_API")// 描述
                .contact(new Contact("gewenzhen", "",
                        "15258397904@163.com"))// 联系
                .version("1.0")// 版本
                .build();
    }
}