package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.webpages.forgotpassword.ChangePasswordPage;
import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageForgotPasswordDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private ChangePasswordPage changePasswordPage;

    public LoginPageForgotPasswordDefinitionSteps() {

        Given("User opens login page for password recovery", () ->
                open(appUrl)
        );

        When("User clicks on forgot your password button", () ->
                loginPage.forgotPasswordPageButton()
        );

        Then("User is displayed login page after password recovery", () ->
                open(appUrl)
        );

        When("User enters {word} userName and new {word} password", (String userName, String password) ->
                loginPage.loginAfterPasswordRecovery(userName, changePasswordPage.getPassword())
        );
    }
}
