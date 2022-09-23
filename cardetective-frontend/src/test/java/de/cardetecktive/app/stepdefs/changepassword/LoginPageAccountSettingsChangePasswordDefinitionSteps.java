package de.cardetecktive.app.stepdefs.changepassword;

import de.cardetecktive.app.database.DataBaseSteps;
import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageAccountSettingsChangePasswordDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private DataBaseSteps dataBaseSteps;

    @Autowired
    private LoginPage loginPage;

    public LoginPageAccountSettingsChangePasswordDefinitionSteps() {

        Given("Open login page for change password functionality", () ->
                open(appUrl)
        );

        When("Enter {word} userName {word} oldPassword", (String userName, String oldPassword) -> {
            dataBaseSteps.setDefaultPassword(userName);
            loginPage.changePasswordLoginWithOldPassword(userName, oldPassword);
        });

        And("Enter {word} userName {word} newPassword", (String userName, String newPassword) -> {
            loginPage.clearLoginCredentials();
            loginPage.changePasswordLoginWithNewPassword(userName, loginPage.getNewPassword());
        });
    }
}
