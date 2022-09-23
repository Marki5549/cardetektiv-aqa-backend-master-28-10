package de.cardetektiv.app.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebSocketMessageCode {

    //region: Incoming messages
    AUTHORIZATION_INCOMING(42, "authenticated"),
    CAR_CANCEL_INCOMING(431, "ok"),
    CAR_ESTIMATE_INCOMING(432, null),
    //endregion

    //region: Outgoing messages
    CAR_CANCEL_OUTGOING(421, "car-cancel"),
    AUTHORIZATION_OUTGOING(5, null),
    CAR_ESTIMATE_OUTGOING(422, "car-estimate");
    //endregion

    private Integer code;
    private String type;

    public String getCodeString() {
        return String.valueOf(this.getCode());
    }
}