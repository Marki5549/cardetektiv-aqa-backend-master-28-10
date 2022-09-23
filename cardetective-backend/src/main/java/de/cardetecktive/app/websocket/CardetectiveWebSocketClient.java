package de.cardetecktive.app.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.LinkedList;

@Slf4j
public class CardetectiveWebSocketClient extends WebSocketClient {

    private final LinkedList<String> messages = new LinkedList<>();

    CardetectiveWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handShakeData) {
        log.info("Connection is opened.");
    }

    @Override
    public void onMessage(String message) {
        log.info("Received text message: {}", message);
        messages.add(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Session closed with reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("Connection interrupted with exception.", ex);

    }

    String getLastMessage() {
        log.info("Received messages amount: [{}]", messages.size());
        return messages.getLast();
    }
}
