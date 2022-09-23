package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.register.AccountSetUpSecondPartPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountSetUpSecondPartPageDefinitionSteps implements En {

    @Autowired
    private AccountSetUpSecondPartPage accountSetUpSecondPartPage;

    public AccountSetUpSecondPartPageDefinitionSteps() {

        Then("User is displayed tell us about your business page", () -> {

        });

        And("User enters {word} nameOfBusiness and {word} plz and selects country", (String nameOfBusiness, String plz) ->
                accountSetUpSecondPartPage.accountSetUpSecondPart(nameOfBusiness, plz)
        );
    }
}
