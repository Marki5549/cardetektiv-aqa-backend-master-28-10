package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.database.DataBaseSteps;
import de.cardetecktive.app.webpages.forgotpassword.ChangePasswordPage;
import de.cardetecktive.app.webpages.forgotpassword.ForgotPasswordPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class ChangePasswordPageDefinitionSteps implements En {

    @Value("${application.forgot.password.url}")
    private String appForgotPasswordUrl;

    @Autowired
    private DataBaseSteps dataBaseSteps;

    @Autowired
    private ForgotPasswordPage forgotPasswordPage;

    @Autowired
    private ChangePasswordPage changePasswordPage;

    public ChangePasswordPageDefinitionSteps() {

        And("User resets his password from email address", () -> {
            URIBuilder uriBuilder = dataBaseSteps.getUserIdentity(forgotPasswordPage.getUserName());
            open(appForgotPasswordUrl + uriBuilder.toString());
        });

        Then("User is displayed change password page", () -> {

        });

        And("User enters {word} password twice", (String password) -> {
            password = password.replaceFirst("%random", String.valueOf(System.currentTimeMillis()));
            changePasswordPage.changePassword(password);
        });
    }
}
