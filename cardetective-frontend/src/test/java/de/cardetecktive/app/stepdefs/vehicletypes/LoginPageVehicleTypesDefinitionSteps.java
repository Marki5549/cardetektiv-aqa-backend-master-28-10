package de.cardetecktive.app.stepdefs.vehicletypes;

import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageVehicleTypesDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private LoginPage loginPage;

    public LoginPageVehicleTypesDefinitionSteps() {

        Given("Open login page for vehicle types functionality", () ->
                open(appUrl)
        );

        When("Enter {word} defaultEmail and {word} defaultPassword for vehicle types functionality", (String defaultEmail, String defaultPassword) ->
                loginPage.login(defaultEmail, defaultPassword)
        );
    }
}
