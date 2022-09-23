package de.cardetektiv.app.steps;

import de.cardetecktive.app.authentication.response.SidResponse;
import de.cardetektiv.app.constants.Constants;
import de.cardetektiv.app.constants.enums.WebSocketMessageCode;
import de.cardetecktive.app.enums.Provider;
import de.cardetecktive.app.factories.socket.CarCancelOutgoingMessageFactory;
import de.cardetecktive.app.factories.socket.CarEstimateOutgoingMessageFactory;
import de.cardetektiv.app.services.SocketAuthenticationService;
import de.cardetecktive.app.utils.JsonFormatter;
import de.cardetecktive.app.utils.JsonParser;
import de.cardetektiv.app.utils.WsMessageUtils;
import de.cardetektiv.app.websocket.WebSocketClientFacade;
import de.cardetecktive.app.websocket.incoming.CarEstimateIncomingMessage;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage.CarEstimateOutgoing;
import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import static de.cardetektiv.app.constants.enums.WebSocketMessageCode.*;
import static org.assertj.core.api.Assertions.assertThat;

@Repository
public final class WebSocketSteps extends AbstractBackendSteps {

    private final WebSocketClientFacade webSocketFacade;
    private final SocketAuthenticationService authenticationService;

    public WebSocketSteps(WebSocketClientFacade webSocketFacade,
                          SocketAuthenticationService authenticationService) {
        this.webSocketFacade = webSocketFacade;
        this.authenticationService = authenticationService;
    }

    @Step("Client perform authorization to web socket")
    public String authorizeToTheWebSocket(final Cookie cdSid) {
        final String sid = receiveSid(authenticationService.getSid(cdSid));
        assertThat(sid)
                .as("Client should receive SID first")
                .isNotNull();
        webSocketFacade.connect(sid);
        sendInitialAuthMessage();
        return sid;
    }

    @Step("Client ask for socket authorization status")
    public Response requestSocketAuthStatus(final Cookie cdSid, final String sid) {
        return authenticationService.socketAuthenticate(cdSid, sid);
    }

    @Step("Client successfully authenticated to web socket")
    public void socketAuthenticationStatusIsOk(final Response socketAuthResponse) {
        socketAuthResponse.then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Step("Client successfully authenticated to web socket")
    public void clientSuccessfullyAuthenticatedToWebSocket(final Response socketAuthResponse) {
        assertThat(socketAuthResponse.getBody().asString())
                .as("Response contains authenticated message")
                .contains(Constants.SOCKET_AUTH_SUCCESS);
    }

    @Step("Client receive SID for socket authorization")
    public String receiveSidForSocketAuthorization(final Cookie cdSid) {
        return receiveSid(authenticationService.getSid(cdSid));
    }

    public String receiveSid(final Response sidResponse) {
        sidResponse.then().statusCode(HttpStatus.SC_OK);
        final String jsonBody = JsonFormatter.getValidJson(sidResponse.getBody().asString());
        SidResponse sidDto = JsonParser.jsonToObject(jsonBody, SidResponse.class);
        return sidDto.getSid();
    }

    public String sendInitialAuthMessage() {
        webSocketFacade.sendMessage(WsMessageUtils.createWithNoBody(AUTHORIZATION_OUTGOING));
        webSocketFacade.sendMessage(WsMessageUtils
                .createMessage(CAR_CANCEL_OUTGOING, CarCancelOutgoingMessageFactory.searchMessage()));
        return webSocketFacade.waitAndGetMessageContainsText(CAR_CANCEL_INCOMING.getType());
    }

    @Step("Client search vehicles by next parameters: {estimateOutgoing}")
    public String sendCarEstimateForProvider(final @NotNull CarEstimateOutgoing estimateOutgoing) {
        final CarEstimateOutgoingMessage carEstimate = CarEstimateOutgoingMessageFactory
                .createCarAmountRequest(estimateOutgoing);

        webSocketFacade.sendMessage(WsMessageUtils.createMessage(CAR_ESTIMATE_OUTGOING, carEstimate));
        return webSocketFacade.waitAndGetMessageContainsText(WebSocketMessageCode.CAR_ESTIMATE_INCOMING
                .getCodeString());
    }

    @Step("Client can see the amount of cars for {provider} in received message")
    public void amountOfCarsIsGreaterThanZeroForProvider(final String incomingMessage, final Provider provider) {
        final CarEstimateIncomingMessage message = JsonParser
                .jsonToObject(JsonFormatter.getValidJson(incomingMessage), CarEstimateIncomingMessage.class);
        assertThat(message.getData().getTotal())
                .as("Message from Web Socket should contain amount of cars")
                .isGreaterThan(0);
        assertThat(message.getData().getTotal())
                .as("Message from Web Socket should contain provider")
                .isEqualTo(message.getData().getTotalByProvider().get(provider));
    }
}