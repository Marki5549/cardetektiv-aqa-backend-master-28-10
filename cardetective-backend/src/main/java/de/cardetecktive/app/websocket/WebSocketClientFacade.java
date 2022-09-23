package de.cardetecktive.app.websocket;

import de.cardetecktive.app.constants.Constants;
import de.cardetecktive.app.constants.RequestParams;
import de.cardetecktive.app.exceptions.TestConfigurationException;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
//import org.awaitility.Duration;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Objects;

import static org.awaitility.Awaitility.await;

@Slf4j
public class WebSocketClientFacade {

    private static final String SOCKET_PATH = "socket/";

    private final String baseUrl;

    private CardetectiveWebSocketClient client;

    public WebSocketClientFacade(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void connect(@NotNull final String sid) {
        Objects.requireNonNull(sid);
        final URI socketUri = buildWebSocketUri(sid);

        log.info("Connect to Web Socket using URI: [{}]", socketUri.toString());

        client = new CardetectiveWebSocketClient(socketUri);
        try {
            client.connectBlocking();
        } catch (InterruptedException ex) {
            log.error("Connection was not established", ex);
        }

        log.info("Connection status is: " + client.isOpen());
    }

    public void sendMessage(final String message) {
        log.info("Send text message: [{}]", message);
        Allure.addAttachment("Outgoing message", message);

        getClient().send(message);
    }

    public String waitAndGetMessageContainsText(final String text) {
        log.info("Wait for message contains text: [{}]......", text);
        await().pollInterval(Duration.ofSeconds(1))        //////////////////////
                .until(() -> getClient().getLastMessage().contains(text));
        return getClient().getLastMessage();
    }

    public CardetectiveWebSocketClient getClient() {
        if (Objects.isNull(client)) {
           throw new TestConfigurationException("No WebSocket connection created. Connect with SID first!");
        }
        return client;
    }

    private URI buildWebSocketUri(final String sid) {
        try {
            return new URIBuilder(baseUrl)
                    .setPath(SOCKET_PATH)
                    .addParameter(RequestParams.UID, "269")
                    .addParameter(RequestParams.EIO, "3")
                    .addParameter(RequestParams.TRANSPORT, "websocket")
                    .addParameter(Constants.SID, sid)
                    .build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("An error occurred while building URI", ex);
        }
    }
}
