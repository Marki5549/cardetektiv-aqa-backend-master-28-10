package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.register.ConfirmRegistrationPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConfirmRegistrationPageDefinitionSteps implements En {

    @Autowired
    private ConfirmRegistrationPage confirmRegistrationPage;

    public ConfirmRegistrationPageDefinitionSteps() {
        Then("User is displayed confirm registration page", () ->
                Assertions.assertThat(confirmRegistrationPage.isCurrentPage())
                        .isTrue()
        );
    }
}
