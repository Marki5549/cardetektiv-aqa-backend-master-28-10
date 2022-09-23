package de.cardetecktive.app.stepdefs.vehicletypes;

import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Slf4j
public class ResultListPageVehicleTypesDefinitionSteps implements En {

    @Autowired
    private ResultListPage resultListPage;

    public ResultListPageVehicleTypesDefinitionSteps() {

        And("Check if car result list contains response", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_BMW_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("BMW");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_FOURTH_SERIES_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("420");
        });

        And("Check if moto result list contains response", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_BMW_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect moto manufacturer")
                    .containsOnly("BMW");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_100_CS_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect moto model")
                    .containsOnly("100 CS");
        });

        And("Check if truck result list contains response", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_DAF_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect truck manufacturer")
                    .containsOnly("DAF");
        });

        And("Check if camper result list contains response", () -> {
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_ADRIA_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect camper manufacturer")
                    .containsOnly("Adria");
        });
    }
}
