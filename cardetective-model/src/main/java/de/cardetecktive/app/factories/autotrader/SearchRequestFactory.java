package de.cardetecktive.app.factories.autotrader;

import de.cardetecktive.app.external.autotrader.AdvertQuery;
import de.cardetecktive.app.external.autotrader.AutotraderSearchRequest;
import de.cardetecktive.app.external.autotrader.Facet;
import de.cardetecktive.app.external.autotrader.Variables;

import java.util.Arrays;
import java.util.Collections;

public final class SearchRequestFactory {

    private SearchRequestFactory() {
    }

    public static AutotraderSearchRequest searchRequestWithQuery(final String query, final String... makes) {
        final Variables variables = Variables.builder()
                .facets(Arrays.asList(Facet.MAKE.getValue(), Facet.MODEL.getValue()))
                .advertQuery(AdvertQuery.builder()
                        .advertisingLocations(Collections.singletonList("at_cars"))
                        .make(Arrays.asList(makes))
                        .build())
                .build();

        return AutotraderSearchRequest.builder()
                .operationName("SearchFormFacetsQuery")
                .variables(variables)
                .query(query)
                .build();
    }
}
