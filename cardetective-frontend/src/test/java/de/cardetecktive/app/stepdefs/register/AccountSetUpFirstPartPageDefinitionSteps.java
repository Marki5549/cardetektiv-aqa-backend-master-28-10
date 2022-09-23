package de.cardetecktive.app.stepdefs.register;

import de.cardetecktive.app.database.DataBaseSteps;
import de.cardetecktive.app.webpages.register.AccountSetUpFirstPartPage;
import de.cardetecktive.app.webpages.register.RegisterPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class AccountSetUpFirstPartPageDefinitionSteps implements En {

    @Value("${application.email-confirmation.url}")
    private String appEmailConfirmationUrl;

    @Autowired
    private RegisterPage registerPage;

    @Autowired
    private AccountSetUpFirstPartPage accountSetUpFirstPartPage;

    @Autowired
    private DataBaseSteps dataBaseSteps;

    public AccountSetUpFirstPartPageDefinitionSteps() {

        And("User confirms his email address", () -> {
            URIBuilder uriBuilder = dataBaseSteps.confirmRegistrationParams(registerPage.getEmail());
            open(appEmailConfirmationUrl + uriBuilder.toString());
        });

        Then("User is displayed let's set up your account page", () -> {

        });

        When("User enters {word} firstName and {word} lastName", (String firstName, String lastName) ->
                accountSetUpFirstPartPage.accountSetUpFirstPart(firstName, lastName)
        );
    }
}
