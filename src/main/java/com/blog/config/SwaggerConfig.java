package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(getInfo1()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo getInfo1() {

        return new ApiInfo("Blogger Application : Backend Course","This project develop by Tashfeen Kutub","1.0","Terms of service",
                new Contact("Tashfeen","https://learncodewithdurgesh.com","ktashfeen35@gmail.com"),
                "Licence of Apis","Api Licence Url", Collections.emptyList());
    }
}
