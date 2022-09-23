package de.cardetecktive.app.authentication;

import de.cardetecktive.app.ApplicationBaseTest;
import de.cardetecktive.app.steps.AuthenticationSteps;
import de.cardetecktive.app.steps.WebSocketSteps;
import io.qameta.allure.Feature;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

@Feature("[BE] Web socket authorization functionality")
public class SocketAuthenticationTest extends ApplicationBaseTest {

    @Autowired
    private AuthenticationSteps authenticationSteps;

    @Autowired
    private WebSocketSteps webSocketSteps;

    @Test(description = "Client can authenticate to web socket using existing user")
    public void authenticationToWebSocketUsingActiveUserShouldSucceed() {
        final Cookie userSession = authenticationSteps.loginAndGetCurrentSession();
        final String sid = webSocketSteps.receiveSidForSocketAuthorization(userSession);
        final Response socketAuth = webSocketSteps.requestSocketAuthStatus(userSession, sid);

        webSocketSteps.socketAuthenticationStatusIsOk(socketAuth);
        webSocketSteps.clientSuccessfullyAuthenticatedToWebSocket(socketAuth);
    }
}
