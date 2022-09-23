package de.cardetecktive.app.stepdefs.counter;

import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ResultListPageCounterDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private ResultListPage resultListPage;

    public ResultListPageCounterDefinitionSteps() {

        Then("Result list page for counter functionality is displayed", () -> {

        });

        Then("Create all ads list pdf", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            resultListPage.createCounterAllAdsListPdf();
        });

        And("Open counter to check if counter requests values calculated correctly", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCounter();
            applicationHeader.checkCounterRequests();
            applicationHeader.closeCounter();
        });

        And("Open counter to check if counter equal params operations values calculated correctly", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCounter();
            applicationHeader.checkCounterEqualParamsOperations();
            applicationHeader.closeCounter();
        });

        And("Open counter to check if counter different params operations values calculated correctly", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCounter();
            applicationHeader.checkCounterDifferentParamsOperations();
            applicationHeader.closeCounter();
        });

        And("Open counter to check if counter pdf created values calculated correctly", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCounter();
            applicationHeader.checkCounterPdfCreated();
            applicationHeader.closeCounter();
        });

        Then("Open searchmask page for counter functionality", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openCarSearchmaskPage();
        });
    }
}
