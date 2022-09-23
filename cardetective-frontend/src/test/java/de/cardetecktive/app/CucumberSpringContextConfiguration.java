package de.cardetecktive.app;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import de.cardetecktive.app.config.TestContextConfiguration;
import de.cardetecktive.app.database.DataBaseSteps;
import io.cucumber.java8.En;
import io.cucumber.spring.CucumberContextConfiguration;
import io.qameta.allure.selenide.AllureSelenide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestContextConfiguration.class)
public class CucumberSpringContextConfiguration implements En {

    @Autowired
    private DataBaseSteps dataBaseSteps;

    @Value("${application.user.email}")
    private String defaultEmail;

    public CucumberSpringContextConfiguration() {

        Before(() -> {
            dataBaseSteps.clearUserSession(defaultEmail);
            Configuration.headless = false;
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(true));
        });
    }
}