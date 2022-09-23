package de.cardetecktive.app.websocket.outgoing;

import de.cardetecktive.app.enums.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public final class CarEstimateOutgoingMessage {

    private Id id;
    private CarEstimateOutgoing data;

    @Builder
    @Getter
    @EqualsAndHashCode
    @ToString
    public static class CarEstimateOutgoing {

        private String searchMaskType;

        private String vehicleType;

        private Collection<Country> country;

        @Builder.Default
        private AccidentData accidentData = AccidentData.DO_NOT_SHOW_ACCIDENT_VEHICLES;

        @Builder.Default
        private VatableOption vatableOption = VatableOption.ANY;

        @Builder.Default
        private boolean sortOrder = true;

        @Builder.Default
        private boolean sortOverride = true;

        @Builder.Default
        private String sortField = "price";

        private String make;

        private String model;

        private String version;

        private Collection<Provider> providers;

        @Builder.Default
        private Id sources = Id.SEARCH;

    }

}
