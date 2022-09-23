package de.cardetecktive.app.stepdefs.datsettings;

import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageUserDatCredentialsDefinitionSteps implements En {

    @Value("${application.base.url}")
    private String appUrl;

    @Autowired
    private LoginPage loginPage;

    public LoginPageUserDatCredentialsDefinitionSteps() {

        Given("Open login page for dat credentials", () ->
                open(appUrl)
        );

        When("Enter {word} defaultEmail and {word} defaultPassword for dat credentials", (String defaultEmail, String defaultPassword) ->
                loginPage.login(defaultEmail, defaultPassword)
        );
    }
}
