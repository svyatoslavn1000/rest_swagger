package ru.geekbrains.navy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.geekbrains.navy.controller"))
                .build()
                .apiInfo(metaData());
    }

    protected ApiInfo metaData() {
        return new ApiInfo(
                "NAVY REST API",
                "Rest API description of Navy project",
                "1.0.1",
                "Terms of service",
                new Contact("Me", "", "me@geekbrains.ru"),
                "License of API", "", Collections.emptyList());
    }
}
