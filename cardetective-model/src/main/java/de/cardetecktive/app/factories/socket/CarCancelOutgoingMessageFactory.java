package de.cardetecktive.app.factories.socket;

import de.cardetecktive.app.enums.Id;
import de.cardetecktive.app.websocket.outgoing.CarCancelOutgoingMessage;

public final class CarCancelOutgoingMessageFactory {

    public static CarCancelOutgoingMessage searchMessage() {
        return CarCancelOutgoingMessage.builder()
                .id(Id.SEARCH)
                .build();
    }
}