package de.cardetecktive.app.stepdefs.login;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    public CarSearchPageDefinitionSteps() {

        Then("User is successfully authorized to application", () -> {

        });

        Then("User logs out from application after login functionality", () ->
                applicationHeader.logOutExpert()
        );
    }
}
