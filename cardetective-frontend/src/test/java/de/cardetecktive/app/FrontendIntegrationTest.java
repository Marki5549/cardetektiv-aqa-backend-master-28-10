package de.cardetecktive.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "not @ignore",
        features = "classpath:features")
public final class FrontendIntegrationTest {

}
