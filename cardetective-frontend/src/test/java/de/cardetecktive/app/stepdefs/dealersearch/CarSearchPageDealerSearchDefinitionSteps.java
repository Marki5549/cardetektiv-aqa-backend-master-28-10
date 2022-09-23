package de.cardetecktive.app.stepdefs.dealersearch;

import de.cardetecktive.app.webpages.carsearch.CarSearchPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import de.cardetecktive.app.webpages.components.carsearch.DealerSearchSection;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageDealerSearchDefinitionSteps implements En {

    @Autowired
    private CarSearchPage carSearchPage;

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    @Autowired
    private DealerSearchSection dealerSearchSection;

    public CarSearchPageDealerSearchDefinitionSteps() {

        Then("Car search page for dealer search functionality is displayed", () -> {

        });

        Then("Open dealer search", () ->
                carSearchHeader.openDealerSearch()
        );

        And("Enter {word} autohouse and click on search button and dealer search results should be displayed and add results to branches and groups", (String autohouse) ->
                dealerSearchSection.dealerSearchResults(autohouse)
        );

        Then("Clear dealer search", () -> {
            applicationHeader.openCarSearchmaskPage();
            carSearchHeader.openDealerSearch();
            dealerSearchSection.clearDealerSearchResults();
        });

        And("Log out from application after dealer search functionality", () ->
                applicationHeader.logOutDealer()
        );
    }
}
