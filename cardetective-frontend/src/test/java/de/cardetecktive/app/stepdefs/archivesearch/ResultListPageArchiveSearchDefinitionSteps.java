package de.cardetecktive.app.stepdefs.archivesearch;

import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Slf4j
public class ResultListPageArchiveSearchDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private ResultListPage resultListPage;

    public ResultListPageArchiveSearchDefinitionSteps() {

        Then("Result list page for archive search is displayed", () -> {

        });

        And("Check if result list response correspond to selected params", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.ARCHIVE_MAKE_BMW_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("BMW");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_FOURTH_SERIES_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("420");
        });

        And("Check aviability of result list response car detailed params", () -> {
            resultListPage.resultListCarDetailedAviability();
        });

        Then("Open archive search page", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCarSearchmaskPage();
        });
    }
}
