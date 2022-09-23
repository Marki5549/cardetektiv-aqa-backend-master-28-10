package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.register.RegisterPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RegisterPageDefinitionSteps implements En {

    @Autowired
    private RegisterPage registerPage;

    public RegisterPageDefinitionSteps() {

        Then("User is displayed register page", () -> {

        });

        When("User enters {word} email and {word} secretFieldForTests and selects branch and product for registration and confirms agreement", (String email, String secretFieldForTests) -> {
            email = email.replaceFirst("%random%", String.valueOf(System.currentTimeMillis()));
            registerPage.registration(email, secretFieldForTests);
        });
    }
}
