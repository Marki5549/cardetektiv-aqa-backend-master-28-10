package de.cardetecktive.app.factories.socket;

import de.cardetecktive.app.enums.*;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public final class CarEstimateOutgoingMessageFactory {

    private CarEstimateOutgoingMessageFactory() {
    }

    public static CarEstimateOutgoingMessage createCarAmountRequest(
            @NotNull final CarEstimateOutgoingMessage.CarEstimateOutgoing estimateOutgoing) {
        return CarEstimateOutgoingMessage.builder()
                .id(Id.SEARCH)
                .data(estimateOutgoing)
                .build();
    }

    public static CarEstimateOutgoingMessage createCarAmountRequest(@NotNull final Provider provider,
                                                                    @NotNull final CarSearchType carSearchType,
                                                                    @NotNull final Collection<Country> countries) {
        return CarEstimateOutgoingMessage.builder()
                .id(Id.SEARCH)
                .data(CarEstimateOutgoingMessage.CarEstimateOutgoing
                        .builder()
                        .searchMaskType(carSearchType.name())
                        .vehicleType(carSearchType.getVehicleType())
                        .country(countries)
                        .accidentData(AccidentData.DO_NOT_SHOW_ACCIDENT_VEHICLES)
                        .vatableOption(VatableOption.ANY)
                        .sortOrder(true)
                        .sortOverride(true)
                        .sortField("price")
                        .providers(Collections.singletonList(provider))
                        .sources(Id.SEARCH)
                        .build())
                .build();
    }
}
