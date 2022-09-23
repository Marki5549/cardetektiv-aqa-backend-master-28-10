package de.cardetecktive.app.config;

import de.cardetecktive.app.managers.ExternalProvidersManager;
import de.cardetecktive.app.services.AbstractService;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
@ComponentScan(basePackageClasses = {AbstractService.class, ExternalProvidersManager.class})
public class ControllerConfiguration {

    @Bean
    public Supplier<RequestSpecification> requestSpecification(
            @Value("${application.base.url}") final String baseUrl) {
        return () -> new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
                .addFilter(new AllureRestAssured())
                .build();
    }
}