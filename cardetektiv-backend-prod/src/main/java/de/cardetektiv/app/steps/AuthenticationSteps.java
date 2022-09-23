package de.cardetektiv.app.steps;

import de.cardetecktive.app.authentication.request.LoginRequest;
import de.cardetektiv.app.constants.Constants;
import de.cardetecktive.app.factories.LoginRequestFactory;
import de.cardetektiv.app.services.UserAuthenticationService;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

@Repository
public final class AuthenticationSteps extends AbstractBackendSteps {

    @Value("${system.username}")
    private String userName;

    @Value("${system.password}")
    private String password;

    @Value("${application.base.url}")
    private String baseUrl;

    private final UserAuthenticationService authenticationService;
    private final JdbcTemplate jdbcTemplate;

    private Cookie cdSid;
    private Boolean cdSidExists = false;

    public Cookie getCdSid() {
        return cdSid;
    }

    public Boolean getCdSidExists() {
        return cdSidExists;
    }

    public AuthenticationSteps(UserAuthenticationService authenticationService, JdbcTemplate jdbcTemplate) {
        this.authenticationService = authenticationService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Step("Client login to the system using default credentials")
    public Response loginUsingDefaultCredentials() {
        final LoginRequest request = LoginRequestFactory
                .loginRequest(this.userName, this.password, this.baseUrl);
        return authenticationService.login(request);
    }

    @Step("Client login to the system and save user session")
    public Cookie loginAndGetCurrentSession() {
        final Response loginResponse = loginUsingDefaultCredentials();
        loginResponse.then()
                .statusCode(HttpStatus.SC_OK);
        cdSid = loginResponse
                .getDetailedCookies()
                .get(Constants.SID_COOKIE);
        cdSidExists = true;

        return cdSid;
    }

    @Step("Client login to system using [{username}] login and [{password}] password")
    public Response loginWithUsernameAndPassword(final String username, final String password) {
        final LoginRequest request = LoginRequestFactory
                .loginRequest(username, password, baseUrl);
        return authenticationService.login(request);
    }

    @Step("Client successfully authorized to the system")
    public void clientSuccessfullyAuthorized(final Response loginResponse) {
        assertThat(loginResponse.statusCode())
                .as("Status code is 200 OK")
                .isEqualTo(HttpStatus.SC_OK);
    }

    @Step("Client login attempt should be forbidden")
    public void clientLoginAttemptForbidden(final Response loginResponse) {
        assertThat(loginResponse.statusCode())
                .as("Status code is 403 Forbidden")
                .isEqualTo(HttpStatus.SC_FORBIDDEN);
    }

    public Response loginAndSaveCurrentSession(@NotNull final LoginRequest request) {
        final Response loginResponse = authenticationService.login(request);
        loginResponse.then().statusCode(HttpStatus.SC_OK);
        final Cookie cdSid = loginResponse
                .getDetailedCookies()
                .get(Constants.SID_COOKIE);
        System.setProperty(cdSid.getName(), cdSid.getValue());
        return loginResponse;
    }

    @Step("Remove all active sessions for user with id [{userIdentityId}]")
    public int removeUserSession(final int userIdentityId) {
        return jdbcTemplate
                .update("DELETE FROM `cardetective-stage`.sessionData WHERE userIdentityId = ?", userIdentityId);
    }

}
