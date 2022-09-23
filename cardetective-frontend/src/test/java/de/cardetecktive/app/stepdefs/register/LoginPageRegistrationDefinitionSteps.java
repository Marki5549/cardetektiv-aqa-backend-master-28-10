package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.database.DataBaseSteps;
import de.cardetecktive.app.webpages.login.LoginPage;
import de.cardetecktive.app.webpages.register.RegisterPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageRegistrationDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private RegisterPage registerPage;

    @Autowired
    private DataBaseSteps dataBaseSteps;

    public LoginPageRegistrationDefinitionSteps() {

        Given("User opens login page for registration attempt", () ->
                open(appUrl)
        );

        When("User clicks on sign up button", () ->
                loginPage.registerPageButton()
        );

        Then("User is displayed login page", () ->
                open(appUrl)
        );

        When("User enters {word} email and {word} password", (String email, String password) -> {
            dataBaseSteps.updateRegisteredUser(registerPage.getEmail());
            dataBaseSteps.changeAppClientVersion(registerPage.getEmail());
            loginPage.loginAfterRegistration(registerPage.getEmail(), password);
        });
    }
}
