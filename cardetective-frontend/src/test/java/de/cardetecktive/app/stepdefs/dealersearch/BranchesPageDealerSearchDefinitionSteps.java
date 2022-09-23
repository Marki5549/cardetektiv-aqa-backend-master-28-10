package de.cardetecktive.app.stepdefs.dealersearch;

import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BranchesPageDealerSearchDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private ResultListPage resultListPage;

    public BranchesPageDealerSearchDefinitionSteps() {

        Then("Open branches page and refresh it and check if results are available", () -> {
            applicationHeader.openBranchesPage();
            resultListPage.checkDealerResultsAviability();
        });
    }
}
