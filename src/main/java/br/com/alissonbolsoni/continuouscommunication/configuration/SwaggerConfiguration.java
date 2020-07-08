package br.com.alissonbolsoni.continuouscommunication.configuration;

import org.springframework.beans.factory.annotation.Value;
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
public class SwaggerConfiguration {
    private final static String EMAIL = "alisson@alissonbolsoni.com.br";
    private final static String SITE = "https://www.alissonbolsoni.com.br";
    private final static String TITLE = "Alisson Bolsoni";
    private final static String TITLE_LICENSE = "GC License";
    private final static String URL_TERMS_OF_SERVICE = "Terms of Service";
    private final static String LICENCE_URL = SITE;
    private final static String BASE_PACKAGE = "br.com.alissonbolsoni.continuouscommunication.controller";

    @Value("${application.version}")
    private String version;

    @Value("${application.name}")
    private String name;

    @Value("${application.description}")
    private String description;

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                name, description, version,
                URL_TERMS_OF_SERVICE,
                new Contact(
                        TITLE,
                        SITE,
                        EMAIL
                ),
                TITLE_LICENSE,
                LICENCE_URL,
                Collections.emptyList()
        );
    }
}
