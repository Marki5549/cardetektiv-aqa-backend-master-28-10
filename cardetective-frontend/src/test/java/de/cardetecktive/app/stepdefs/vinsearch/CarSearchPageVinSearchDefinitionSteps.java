package de.cardetecktive.app.stepdefs.vinsearch;

import de.cardetecktive.app.webpages.carsearch.CarSearchPage;
import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.CarSearchHeader;
import de.cardetecktive.app.webpages.components.carsearch.SettingsSection;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageVinSearchDefinitionSteps implements En {

    @Autowired
    private CarSearchPage carSearchPage;

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private SettingsSection settingsSection;

    @Autowired
    private CarSearchHeader carSearchHeader;

    public CarSearchPageVinSearchDefinitionSteps() {

        Then("Car search page for vin search is displayed", () -> {

        });

        Then("Open dat settings and save {word} datCustomerNumber and {word} datUsername and {word} datPassword for vin search", (String datCustomerNumber,
                                                                                                                                           String datUsername,
                                                                                                                                           String datPassword) -> {
            applicationHeader.openSettings();
            settingsSection.openDat();
            settingsSection.saveDatCredentials(datCustomerNumber, datUsername, datPassword);
            settingsSection.closeDatCredentialsOverlay();
            settingsSection.closeSettings();
        });

        Then("Open searchmask and enter {word} defaultOperationNumber and {word} vin and {word} ez and {word} kilometer and open dat rating", (String defaultOperationNumber,
                                                                                                                                                        String vin,
                                                                                                                                                        String ez,
                                                                                                                                                        String kilometer) -> {
            applicationHeader.openCarSearchmaskPage();
            Assertions.assertThat(carSearchPage.isCurrentPage())
                    .isTrue();
            carSearchHeader.openVinQuery();
            carSearchHeader.vinSearch(defaultOperationNumber, vin, ez, kilometer);
        });

        Then("Dat rating window should contain vin", () -> {
            Assertions.assertThat(carSearchHeader.getDatRatingVinFieldText())
                    .as("VIN code is incorrect")
                    .isEqualTo("VF7DEXTESTSTUB002");
            carSearchHeader.closeDatRatingWindow();
        });

        And("Clear dat credentials and reset search for vin search", () -> {
            applicationHeader.openSettings();
            settingsSection.openDat();
            settingsSection.resetDatCredentials();
            settingsSection.closeSettings();
            carSearchHeader.resetSearch();
            carSearchHeader.openVinQuery();
        });

        And("Log out from application after vin search", () ->
                applicationHeader.logOutExpert()
        );
    }
}
