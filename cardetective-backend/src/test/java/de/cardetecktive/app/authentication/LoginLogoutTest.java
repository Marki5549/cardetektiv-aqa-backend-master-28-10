package de.cardetecktive.app.authentication;

import de.cardetecktive.app.ApplicationBaseTest;
import de.cardetecktive.app.steps.AuthenticationSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("[BE] Client login and logout functionality")
public class LoginLogoutTest extends ApplicationBaseTest {

    @Autowired
    private AuthenticationSteps authenticationSteps;

    @Test(description = "Client can login using valid credentials")
    public void loginWithValidCredentialsShouldSucceed() {
        Response loginResponse = authenticationSteps.loginUsingDefaultCredentials();
        authenticationSteps.clientSuccessfullyAuthorized(loginResponse);
    }

    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"test_test@mail.mail", "password123"},
                {" ", "password123"},
                {"test_test@mail.mail", ""}
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Login using invalid credentials should be forbidden")
    public void loginAttemptWithInvalidCredentialsShouldFail(final String username, final String password) {
        Response loginResponse = authenticationSteps.loginWithUsernameAndPassword(username, password);
        authenticationSteps.clientLoginAttemptForbidden(loginResponse);
    }
}
