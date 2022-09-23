package de.cardetecktive.app.stepdefs.makemodeldropdown;

import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageMakeModelDuplicatesDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private LoginPage loginPage;

    public LoginPageMakeModelDuplicatesDefinitionSteps() {

        Given("Open login page for make model dropdown duplicate values", () ->
                open(appUrl)
        );

        When("Enter {word} defaultEmail and {word} defaultPassword for make model dropdown duplicate values", (String defaultEmail, String defaultPassword) ->
                loginPage.login(defaultEmail, defaultPassword)
        );
    }
}
