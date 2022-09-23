package de.cardetecktive.app.stepdefs.vehiclesearch;

import de.cardetecktive.app.webpages.carsearch.CarSearchPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageVehiclesFoundDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    @Autowired
    private CarSearchPage carSearchPage;

    public CarSearchPageVehiclesFoundDefinitionSteps() {

        Then("Car search page for vehicles found functionality is displayed", () -> {

        });

        And("Enter {word} defaultOperationNumber and select bmw fourth series params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.bmwFourthSeries(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter {word} defaultOperationNumber and select cupra ateca params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.cupraAteca(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter {word} defaultOperationNumber and select kia stinger params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.kiaStinger(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter {word} defaultOperationNumber and select skoda octavia params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.skodaOctavia(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter {word} defaultOperationNumber and select tesla model s params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.teslaModelS(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        Then("Reset search for vehicles found functionality", () -> {
            applicationHeader.openCarSearchmaskPage();
            Assertions.assertThat(carSearchPage.isCurrentPage())
                    .isTrue();
            carSearchHeader.resetSearch();
        });

        Then("Log out from application after vehicles found functionality", () ->
                applicationHeader.logOutExpert()
        );
    }
}
