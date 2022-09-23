package de.cardetektiv.app.factories;

import de.cardetecktive.app.enums.CarSearchType;
import de.cardetecktive.app.enums.Country;
import de.cardetecktive.app.enums.Provider;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage.CarEstimateOutgoing.CarEstimateOutgoingBuilder;

import java.util.Collections;

public class CarEstimateMessageFactory {

    public static CarEstimateOutgoingMessage.CarEstimateOutgoing carEstimateByType(CarSearchType searchType,
                                                                                    Provider provider) {
        return carEstimateDefaultBuilder(searchType, provider)
                .build();
    }

    public static CarEstimateOutgoingMessage.CarEstimateOutgoing carEstimateByCountry(Country country,
                                                                                      CarSearchType searchType,
                                                                                      Provider provider) {
        return carEstimateDefaultBuilder(searchType, provider)
                .country(Collections.singletonList(country))
                .build();
    }

    public static CarEstimateOutgoingMessage.CarEstimateOutgoing carEstimateByModel(CarSearchType searchType,
                                                                                    Provider provider) {
        return carEstimateDefaultBuilder(searchType, provider)
                .make("TOYOTA")
                .model("Auris")
                .version("1")
                .build();
    }

    public static CarEstimateOutgoingBuilder carEstimateDefaultBuilder(final CarSearchType searchType,
                                                                       final Provider provider) {
        return CarEstimateOutgoingMessage.CarEstimateOutgoing
                .builder()
                .searchMaskType(searchType.name())
                .vehicleType(searchType.getVehicleType())
                .providers(Collections.singletonList(provider));
    }
}
