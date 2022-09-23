package de.cardetecktive.app.stepdefs.archivesearch;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.ArchivePreview;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageArchiveSearchDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    @Autowired
    private ArchivePreview archivePreview;

    public CarSearchPageArchiveSearchDefinitionSteps() {

        Then("Car search page for archive search is displayed", () -> {

        });

        And("Open archive search and enter {word} defaultOperationNumber and select params and click on Buy report button", (String defaultOperationNumber) -> {
            defaultOperationNumber = defaultOperationNumber.replaceFirst("%random%", String.valueOf(System.currentTimeMillis()));
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openArchiveSearch();
            carSearchHeader.archiveSearchParams(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.buyReport();
        });

        And("Open archive search and enter {word} defaultOperationNumber and select params and click on Show Preview", (String defaultOperationNumber) -> {
            defaultOperationNumber = defaultOperationNumber.replaceFirst("%random%", String.valueOf(System.currentTimeMillis()));
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            applicationHeader.openArchiveSearch();
            carSearchHeader.archiveSearchParams(defaultOperationNumber);
            archivePreview.openArchiveSearchPreview();
        });

        Then("Preview for archive search should be displayed", () ->
                archivePreview.checkArchiveSearchPreviewCarCards()
        );

        And("Log out from application after archive search", () ->
                applicationHeader.logOutExpert()
        );
    }
}
