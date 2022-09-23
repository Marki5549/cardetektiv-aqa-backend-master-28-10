package de.cardetecktive.app.stepdefs.resultlistpdf;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPagePdfGenerationDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    public CarSearchPagePdfGenerationDefinitionSteps() {

        Then("Car search page for pdf generation is displayed", () -> {

        });

        And("Enter pdf generation {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.resultListPdf(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Reset search for pdf generation", () ->
                carSearchHeader.resetSearch()
        );

        Then("Log out from application after pdf generation", () ->
                applicationHeader.logOutExpert()
        );
    }
}
