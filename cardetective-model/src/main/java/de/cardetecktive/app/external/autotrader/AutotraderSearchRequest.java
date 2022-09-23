package de.cardetecktive.app.external.autotrader;

import lombok.Builder;

@Builder
public class AutotraderSearchRequest {

    private String operationName;
    private Variables variables;
    private String query;

}
