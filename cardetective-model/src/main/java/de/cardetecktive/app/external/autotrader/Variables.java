package de.cardetecktive.app.external.autotrader;

import lombok.Builder;

import java.util.Collection;

@Builder
public class Variables {

    private AdvertQuery advertQuery;
    private Collection<String> facets;
}
