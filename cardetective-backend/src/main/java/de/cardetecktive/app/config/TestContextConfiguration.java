package de.cardetecktive.app.config;

import de.cardetecktive.app.steps.AbstractBackendSteps;
import de.cardetecktive.app.storages.ScenarioStorage;
import io.restassured.response.Response;
import org.springframework.context.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = "classpath:application-${env:stage}.properties")
@ComponentScan(basePackageClasses = AbstractBackendSteps.class)
@Import({
        ControllerConfiguration.class,
        WebSocketConfiguration.class,
        DatabaseConfiguration.class
})
public class TestContextConfiguration {

    @Bean
    public ScenarioStorage<Response> responseStorage() {
        return new ScenarioStorage<>();
    }

    @Bean
    public ScenarioStorage<Map<String, String>> scenarioStorage() {
        final ScenarioStorage<Map<String, String>> scenarioStorage = new ScenarioStorage<>();
        scenarioStorage.save(new HashMap<>());
        return scenarioStorage;
    }

}
