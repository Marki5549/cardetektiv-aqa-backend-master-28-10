package de.cardetecktive.app.stepdefs.forgotpassword;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageForgotPasswordDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    public CarSearchPageForgotPasswordDefinitionSteps() {

        Then("User is displayed car search page after password recovery", () -> {

        });

        And("User logs out from application after password recovery", () ->
                applicationHeader.logOutDealer()
        );
    }
}
