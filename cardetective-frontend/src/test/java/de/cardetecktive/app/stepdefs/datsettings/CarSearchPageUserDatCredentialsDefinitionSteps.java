package de.cardetecktive.app.stepdefs.datsettings;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.SettingsSection;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageUserDatCredentialsDefinitionSteps implements En {

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private SettingsSection settingsSection;

    public CarSearchPageUserDatCredentialsDefinitionSteps() {

        Then("Car search page for dat credentials is displayed", () -> {

        });

        And("Open dat settings and save valid {word} datCustomerNumber and {word} datUsername and {word} datPassword", (String datCustomerNumber,
                                                                                                                                 String datUsername,
                                                                                                                                 String datPassword) -> {
            applicationHeader.openSettings();
            settingsSection.openDat();
            settingsSection.saveDatCredentials(datCustomerNumber, datUsername, datPassword);
        });

        And("Open dat settings and save invalid {word} invalidDatCustomerNumber and {word} invalidDatUsername and {word} invalidDatPassword", (String invalidDatCustomerNumber,
                                                                                                                                                        String invalidDatUsername,
                                                                                                                                                        String invalidDatPassword) -> {
            applicationHeader.openSettings();
            settingsSection.openDat();
            settingsSection.saveInvalidDatCredentials(invalidDatCustomerNumber, invalidDatUsername, invalidDatPassword);
        });

        Then("Dat credentials should be saved", () -> {
            Assertions.assertThat(settingsSection.getDatCredentialsSuccessfullySavedText())
                    .as("DAT Credentials are successfully saved")
                    .isEqualTo("DAT-Anmeldeinformationen werden erfolgreich gespeichert");
            settingsSection.closeDatCredentialsOverlay();
        });

        Then("Saving dat credentials should be forbidden", () -> {
            Assertions.assertThat(settingsSection.getSavingDatCredentialsForbiddenText())
                    .as("Supplied Silverdat credentials are wrong")
                    .isEqualTo("Die Benutzerinformationen für Silverdat sind fehlerhaft.");
            settingsSection.closeDatCredentialsOverlay();
        });

        And("Clear dat credentials", () -> {
            settingsSection.resetDatCredentials();
            settingsSection.closeSettings();
        });

        Then("Log out from application after dat credentials", () ->
                applicationHeader.logOutExpert()
        );
    }
}
