package de.cardetecktive.app.stepdefs.vehiclesearch;

import de.cardetecktive.app.webpages.carsearch.ResultListPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Slf4j
public class ResultListPageVehiclesFoundDefinitionSteps implements En {

    @Autowired
    private ResultListPage resultListPage;

    public ResultListPageVehiclesFoundDefinitionSteps() {

        Then("Result list page for vehicles found functionality is displayed", () -> {

        });

        And("Check if result list response correspond to bmw fourth series", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_BMW_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("BMW");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_FOURTH_SERIES_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("420");
        });

        And("Check if result list response correspond to cupra ateca", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_CUPRA_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("Cupra");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_ATECA_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("Ateca");
        });

        And("Check if result list response correspond to kia stinger", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_KIA_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("Kia");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_STINGER_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("Stinger");
        });

        And("Check if result list response correspond to skoda octavia", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_SKODA_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("Skoda");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_OCTAVIA_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("Octavia");
        });

        And("Check if result list response correspond to tesla models s", () -> {
            ArrayList<String> makes = resultListPage.getCars(ResultListPage.MAKE_TESLA_CONTAINER);
            Assertions.assertThat(makes)
                    .as("Result list contains incorrect car manufacturer")
                    .containsOnly("Tesla");
            ArrayList<String> models = resultListPage.getCars(ResultListPage.MODEL_MODEL_S_CONTAINER);
            Assertions.assertThat(models)
                    .as("Result list contains incorrect car model")
                    .containsOnly("Model S");
        });
    }
}
