package de.cardetecktive.app.stepdefs.login;

import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Value("${application.token.url}")
    private String appTokenUrl;

    @Autowired
    private LoginPage loginPage;

    public LoginPageDefinitionSteps() {

        Given("User opens login page for login functionality", () ->
                open(appUrl)
        );

        Given("User gets token using post request and generates url", () -> {
            String token = loginPage.getToken();
            open(appTokenUrl + token);
        });

        When("User enters {word} defaultEmail and {word} defaultPassword", (String defaultEmail, String defaultPassword) ->
                loginPage.login(defaultEmail, defaultPassword)
        );

        When("User enters {word} invalidEmail and {word} invalidPassword", (String invalidEmail, String invalidPassword) ->
                loginPage.loginWithInvalidParams(invalidEmail, invalidPassword)
        );

        Then("Login attempt should be forbidden", () ->
                Assertions.assertThat(loginPage.getLoginErrorMessageText())
                        .as("Incorrect credentials message text")
                        .isEqualTo("Ein Fehler ist aufgetreten.")
        );
    }
}
