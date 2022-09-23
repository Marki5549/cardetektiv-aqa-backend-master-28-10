package de.cardetecktive.app.stepdefs.makemodeldropdown;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageMakeModelDuplicatesDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    public CarSearchPageMakeModelDuplicatesDefinitionSteps() {

        Then("Car search page for make model dropdown duplicate values is displayed", () -> {

        });

        And("Check if make dropdown contains no duplicates", () ->
                carSearchHeader.resultListMakeDuplicates()
        );

        And("Check if model dropdown contains no duplicates", () ->
                carSearchHeader.resultListModelDuplicates()
        );

        Then("Reset search for make model dropdown", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.resetSearch();
        });

        Then("Log out from application after make model dropdown duplicate values", () ->
                applicationHeader.logOutExpert()
        );
    }
}
