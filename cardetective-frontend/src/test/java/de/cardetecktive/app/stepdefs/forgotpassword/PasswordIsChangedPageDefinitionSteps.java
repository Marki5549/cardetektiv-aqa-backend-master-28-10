package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.webpages.forgotpassword.PasswordIsChangedPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PasswordIsChangedPageDefinitionSteps implements En {

    @Autowired
    private PasswordIsChangedPage passwordIsChangedPage;

    public PasswordIsChangedPageDefinitionSteps() {
        Then("User is displayed your password has been successfully changed page", () ->
                Assertions.assertThat(passwordIsChangedPage.isCurrentPage())
                        .isTrue()
        );

        And("User clicks on sign in button on password is changed page", () ->
                passwordIsChangedPage.loginAfterPasswordRecovery()
        );
    }
}
