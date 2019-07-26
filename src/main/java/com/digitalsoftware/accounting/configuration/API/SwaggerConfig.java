package com.digitalsoftware.accounting.configuration.API;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile({"dev", "prod"})
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.digitalsoftware.accounting.controllers"))
            .build()
            .apiInfo(metaData());
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null, "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib2F1dGgyX2lkIl0sInVzZXJfbmFtZSI6InRlc3RVc2VyMiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJpZCI6OTMsImV4cCI6MTUzNjQ5Njc5OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjQ3Yjk4ZmY5LTliMzUtNDE3ZS1iYjkyLTFhZThiOTczY2Y0NiIsImNsaWVudF9pZCI6InRydXN0ZWQtYXBwIn0.MwhuO6mIWMfS7mnYn-wFWheHgs8-PQ9wHERm2muxRsuQUlZY9ayQLEz0vPctWMxJjHeOgRC7Ow4r09Ex__cnClZg2t3Gn3P97CgISlpPMcsSh94tf7EazcC9xCbUP5Chhu9wHf5cxeF6oiU64M3c3fF1eeRkQcEhzXQDsjGMJE3dmWBICrTY7OVHIR7c6oje5K18X5KhX4vsEYH6Mw9A3aIgTIqQkiknhSXyUupW4hvTnupVxXQ2IxBtUKOcDPyArYloTPDCSfSWhB9eQ3H5GVXIFgdMnVlmEh0-IbBNH-5qmOPQ1AMHWpTtSkdvwcJ_O6EX-emgWH_HROqEsKxG5A", ApiKeyVehicle.HEADER, "Authorization", ",");
    }


    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
            "REST API",
            "REST API FOR Digital Software",
            "1.0",
            "Terms of service",
            new Contact("Shuai Zheng", "", "sszheng3@gmail.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}