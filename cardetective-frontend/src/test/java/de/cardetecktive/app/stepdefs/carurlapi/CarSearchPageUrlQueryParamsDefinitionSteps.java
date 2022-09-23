package de.cardetecktive.app.stepdefs.carurlapi;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchFields;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageUrlQueryParamsDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    @Autowired
    private CarSearchFields carSearchFields;

    public CarSearchPageUrlQueryParamsDefinitionSteps() {

        Then("Car search page with selected car params is displayed", () -> {
            carSearchHeader.checkUrlQueryParamsCarSearchHeader();
            carSearchFields.checkUrlQueryParamsCarSearchFields();
        });

        Then("Car search page with selected params from dat euro code is displayed", () ->
                carSearchHeader.checkUrlDatEuroCodeParams()
        );

        Then("Reset search for query params", () ->
                carSearchHeader.resetSearch()
        );

        And("Log out from application after query params", () ->
                applicationHeader.logOutExpert()
        );
    }
}
