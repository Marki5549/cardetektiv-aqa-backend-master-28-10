package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.webpages.forgotpassword.ConfirmPasswordRecoveryPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConfirmPasswordRecoveryPageDefinitionSteps implements En {

    @Autowired
    private ConfirmPasswordRecoveryPage confirmPasswordRecoveryPage;

    public ConfirmPasswordRecoveryPageDefinitionSteps() {
        Then("User is displayed confirm password recovery page", () ->
                Assertions.assertThat(confirmPasswordRecoveryPage.isCurrentPage())
                        .isTrue()
        );
    }
}
