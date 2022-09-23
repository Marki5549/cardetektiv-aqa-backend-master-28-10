package de.cardetecktive.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "de.cardetecktive.app")
@PropertySource(value = "classpath:application-${env:stage}.properties")
@Import({
        DatabaseConfiguration.class
})
public class TestContextConfiguration {

}
