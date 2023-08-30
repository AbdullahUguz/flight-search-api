package com.uguz.flightsearch.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uguz"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());
    }


//    @Bean
//    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.basePackage("com.uguz"))
////                .paths(PathSelectors.any())
////                .build().apiInfo(apiEndPointsInfo());
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiEndPointsInfo())
//                .pathMapping("/")
//                .apiInfo(ApiInfo.DEFAULT)
//                .forCodeGeneration(true)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .ignoredParameterTypes(Pageable.class)
//                .ignoredParameterTypes(java.sql.Date.class)
//                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
//                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
//                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
//                .securityContexts(Lists.newArrayList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()))
//                .useDefaultResponseMessages(false);
//
//        return docket;
//
//    }
//
//
//
    private ApiInfo apiEndPointsInfo(){
        return new ApiInfoBuilder().title("Flight Search Api")
                .description("Rest Api Documentation")
                .contact(new Contact("Abdullah UĞUZ", "","abdullah@gmail.com"))
                .license("Apache 2.0")
                .build();
    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "token", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//    }
}