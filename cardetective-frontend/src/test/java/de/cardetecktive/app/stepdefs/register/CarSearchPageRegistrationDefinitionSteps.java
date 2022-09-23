package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageRegistrationDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    public CarSearchPageRegistrationDefinitionSteps() {

        Then("User is authorized to application after successful registration", () -> {

        });

        And("User logs out from application after successful registration", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.closeJoyRideStep();
            applicationHeader.logOutDealer();
        });
    }
}
