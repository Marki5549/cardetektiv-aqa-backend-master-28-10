package de.cardetecktive.app.websocket.outgoing;

import de.cardetecktive.app.enums.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public final class CarCancelOutgoingMessage {

    private Id id;
}