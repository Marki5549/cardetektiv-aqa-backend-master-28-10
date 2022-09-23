package de.cardetecktive.app.stepdefs.counter;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageCounterDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private CarSearchHeader carSearchHeader;

    public CarSearchPageCounterDefinitionSteps() {

        Then("Car search page for counter functionality is displayed", () -> {

        });

        And("Enter counter requests {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCounter();
            applicationHeader.saveCounterRequests();
            applicationHeader.closeCounter();
            carSearchHeader.equalCounterParams(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter counter equal params operations {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCounter();
            applicationHeader.saveCounterOperations();
            applicationHeader.closeCounter();
            carSearchHeader.equalCounterParams(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter counter different op nums operations {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            defaultOperationNumber = defaultOperationNumber.replaceFirst("%random%", String.valueOf(System.currentTimeMillis()));
            applicationHeader.openCounter();
            applicationHeader.saveCounterOperations();
            applicationHeader.closeCounter();
            carSearchHeader.differentOpNumsCounterParams(defaultOperationNumber);
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter counter different makes operations {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCounter();
            applicationHeader.saveCounterOperations();
            applicationHeader.closeCounter();
            carSearchHeader.differentMakesCounterParams(carSearchHeader.getDefaultOperationNumber());
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter counter different models operations {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCounter();
            applicationHeader.saveCounterOperations();
            applicationHeader.closeCounter();
            carSearchHeader.differentModelsCounterParams(carSearchHeader.getDefaultOperationNumber());
            try {
                Thread.sleep(10_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Enter counter pdf created {word} defaultOperationNumber and select params and click on Vehicles found button", (String defaultOperationNumber) -> {
            applicationHeader.openCounter();
            applicationHeader.saveCounterPdfCreated();
            applicationHeader.closeCounter();
            carSearchHeader.pdfCreatedCounterParams(carSearchHeader.getDefaultOperationNumber());
            try {
                Thread.sleep(5_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            carSearchHeader.searchVehicles();
        });

        And("Reset search for counter functionality", () ->
                carSearchHeader.resetSearch()
        );

        Then("Log out from application after counter functionality", () ->
                applicationHeader.logOutExpert()
        );
    }
}
