package de.cardetecktive.app.external.autotrader;

import lombok.Getter;

@Getter
public enum Facet {

    MAKE("make"),
    MODEL("model");

    private String value;

    Facet(String value) {
        this.value = value;
    }

}
