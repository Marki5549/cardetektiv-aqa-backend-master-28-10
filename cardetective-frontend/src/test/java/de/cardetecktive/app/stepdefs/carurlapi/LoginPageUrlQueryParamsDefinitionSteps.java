package de.cardetecktive.app.stepdefs.carurlapi;

import de.cardetecktive.app.webpages.login.LoginPage;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class LoginPageUrlQueryParamsDefinitionSteps implements En {

    @Value("${application.query.params.url}")
    private String appQueryParamsUrl;

    @Value("${application.dat.euro.code.url}")
    private String appDataEuroCodeUrl;

    @Autowired
    private LoginPage loginPage;

    public LoginPageUrlQueryParamsDefinitionSteps() {

        Given("Pass car params to application base url", () ->
                open(appQueryParamsUrl)
        );

        Given("Pass dat euro code to application base url", () ->
                open(appDataEuroCodeUrl)
        );

        When("Enter {word} defaultEmail and {word} defaultPassword for query params", (String defaultEmail, String defaultPassword) ->
                loginPage.login(defaultEmail, defaultPassword)
        );
    }
}
