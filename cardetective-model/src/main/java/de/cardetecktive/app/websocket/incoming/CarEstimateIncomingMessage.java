package de.cardetecktive.app.websocket.incoming;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.cardetecktive.app.enums.Provider;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@EqualsAndHashCode
public final class CarEstimateIncomingMessage {

    private boolean ok;
    private String id;
    private CarEstimateIncoming data;

    @Getter
    @EqualsAndHashCode
    public static class CarEstimateIncoming {

        private long total;
        private long minus;
        @JsonProperty("_total")
        private Map<Provider, Long> totalByProvider;
        @JsonProperty("_minus")
        private Map<Provider, Long> minusByProvider;

    }
}
