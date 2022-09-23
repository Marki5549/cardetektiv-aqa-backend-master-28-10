package de.cardetecktive.app.enums;

public enum Provider {

    AUTOSCOUT("autoscoutKey"),
    MOBILEDEW("mobilewKey"),
    PKW("pkwKey"),
    COMPRS_CH("comprschKey"),
    LACENTRAL_FR("lacentralKey"),
    COCHES("cochesKey"),
    AUTOMOBILE("automobileKey"),
    PORSCHE_FINDER_DE("porscheFinderKey"),
    TRUCKSCOUT(null),
    AUTOTRADER("autotraderKey"),
    AUTOSCOUT_CH("autoscoutchKey"),
    OTOMOTO("otomotoKey"),
    HASZNALTAUTO(null);

    private final String modelsGroupKey;

    Provider(final String modelsGroupKey) {
        this.modelsGroupKey = modelsGroupKey;
    }

    public String getModelsGroupKey() {
        return modelsGroupKey;
    }
}
