package ec.com.banking.demo.account.client.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ec.com.banking.demo.account.client.controllers"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My REST API",
                "Some custom description of API.",
                "API BANK",
                "Terms of service",
                new Contact("Cesar Sevilla", "www.example.com", "cesarsevilla@company.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
