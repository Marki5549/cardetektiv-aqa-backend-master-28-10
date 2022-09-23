package de.cardetecktive.app.stepdefs.resultlistpdf;

import de.cardetecktive.app.webpages.carsearch.CarSearchPage;
import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ResultListPagePdfGenerationDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchPage carSearchPage;

    @Autowired
    private ResultListPage resultListPage;

    public ResultListPagePdfGenerationDefinitionSteps() {

        Then("Result list page for pdf generation is displayed", () -> {

        });

        Then("Create all ads list pdf and check if all ads list pdf is displayed", () -> {
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            resultListPage.createAllAdsListPdf();
        });

        Then("Create all ads details pdf and check if all ads details pdf is displayed", () ->
                resultListPage.createAllAdsDetailsPdf()
        );

        Then("Open searchmask page for pdf generation", () -> {
            applicationHeader.openCarSearchmaskPage();
            Assertions.assertThat(carSearchPage.isCurrentPage())
                    .isTrue();
        });
    }
}
