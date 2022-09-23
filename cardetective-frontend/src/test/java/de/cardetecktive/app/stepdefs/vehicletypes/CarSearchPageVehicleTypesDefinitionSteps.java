package de.cardetecktive.app.stepdefs.vehicletypes;

import de.cardetecktive.app.webpages.carsearch.CarSearchPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageVehicleTypesDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    @Autowired
    private CarSearchPage carSearchPage;

    public CarSearchPageVehicleTypesDefinitionSteps() {

        Then("Car search page for vehicle types functionality is displayed", () -> {

        });

        Then("Enter {word} defaultOperationNumber and select car params and click on Vehicles found button", (String defaultOperationNumber) -> {
            carSearchHeader.bmwFourthSeries(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        Then("Reset search for car vehicle types functionality", () -> {
            applicationHeader.openCarSearchmaskPage();
            carSearchHeader.resetSearch();
        });

        Then("Open moto searchmask and enter {word} defaultOperationNumber and select moto params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openMotoSearchmaskPage();
            carSearchHeader.bmw100Cs(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        Then("Reset search for moto vehicle types functionality", () -> {
            applicationHeader.openMotoSearchmaskPage();
            carSearchHeader.resetSearch();
        });

        Then("Open truck searchmask and enter {word} defaultOperationNumber and select truck params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openTruckSearchmaskPage();
            carSearchHeader.daf(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        Then("Open truck searchmask and switch truck vehicle types and check console logs", () -> {
            applicationHeader.openTruckSearchmaskPage();
            carSearchHeader.switchTruckVehicleTypes();
        });

        Then("Reset search for truck vehicle types functionality", () -> {
            applicationHeader.openTruckSearchmaskPage();
            carSearchHeader.resetSearch();
        });

        Then("Open camper searchmask and enter {word} defaultOperationNumber and select camper params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCamperSearchmaskPage();
            carSearchHeader.adria(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        Then("Reset search for camper vehicle types functionality", () -> {
            applicationHeader.openCamperSearchmaskPage();
            carSearchHeader.resetSearch();
        });

        Then("Log out from application after vehicle types functionality", () -> {
            applicationHeader.logOutExpert();
        });
    }
}
