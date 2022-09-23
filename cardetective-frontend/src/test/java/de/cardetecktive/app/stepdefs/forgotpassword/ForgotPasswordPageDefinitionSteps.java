package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.webpages.forgotpassword.ForgotPasswordPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ForgotPasswordPageDefinitionSteps implements En {

    @Autowired
    private ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordPageDefinitionSteps() {

        Then("User is displayed forgot password page", () -> {

        });

        When("User enters {word} userName for password recovery", (String userName) ->
                forgotPasswordPage.forgotPasswordEmailInput(userName)
        );
    }
}
