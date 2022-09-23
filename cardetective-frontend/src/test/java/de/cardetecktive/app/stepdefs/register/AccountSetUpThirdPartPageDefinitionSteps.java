package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.register.AccountSetUpThirdPartPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AccountSetUpThirdPartPageDefinitionSteps implements En {

    @Autowired
    private AccountSetUpThirdPartPage accountSetUpThirdPartPage;

    public AccountSetUpThirdPartPageDefinitionSteps() {

        Then("User is displayed you almost did it page", () -> {

        });

        And("User selects honorific and enters {word} phone and {word} whereDidYouHearAboutUs", (String phone, String whereDidYouHearAboutUs) ->
                accountSetUpThirdPartPage.accountSetUpThirdPart(phone, whereDidYouHearAboutUs)
        );
    }
}
