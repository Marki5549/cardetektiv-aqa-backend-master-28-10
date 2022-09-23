package de.cardetecktive.app.external.autotrader;

import lombok.Builder;

import java.util.Collection;

@Builder
public class AdvertQuery {

    private Collection<String> advertisingLocations;
    private String distance;
    private String postcode;
    private Collection<String> make;
    private String homeDeliveryAdverts;

}

