package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.register.AccountSetUpFourthPartPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountSetUpFourthPartPageDefinitionSteps implements En {

    @Autowired
    private AccountSetUpFourthPartPage accountSetUpFourthPartPage;

    public AccountSetUpFourthPartPageDefinitionSteps() {

        Then("User is displayed you did it page", () -> {

        });

        And("User clicks on sign in button", () ->
                accountSetUpFourthPartPage.signInAfterRegistration()
        );
    }
}
