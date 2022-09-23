package de.cardetecktive.app.stepdefs.changepassword;

import de.cardetecktive.app.webpages.components.carsearch.ApplicationHeader;
import de.cardetecktive.app.webpages.components.carsearch.SettingsSection;
import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CarSearchPageAccountSettingsChangePasswordDefinitionSteps implements En {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private ApplicationHeader applicationHeader;

    @Autowired
    private SettingsSection settingsSection;

    public CarSearchPageAccountSettingsChangePasswordDefinitionSteps() {

        Then("Car search page for change password functionality is displayed", () -> {

        });

        And("Open settings for change password functionality", () ->
                applicationHeader.openSettings()
        );

        And("Enter {word} oldPassword {word} newPassword twice and save and close settings", (String oldPassword, String newPassword) -> {
            newPassword = newPassword.replaceFirst("%random", String.valueOf(System.currentTimeMillis()));
            settingsSection.changePassword(oldPassword, newPassword);
            settingsSection.closeSettings();
            loginPage.setNewPassword(newPassword);
        });

        Then("Log out from application after change password functionality", () ->
                applicationHeader.logOutExpert()
        );

        Then("Authorization to application with new password must be successful", () -> {

        });

        And("Log out after change password functionality", () ->
                applicationHeader.logOutExpert()
        );
    }
}
