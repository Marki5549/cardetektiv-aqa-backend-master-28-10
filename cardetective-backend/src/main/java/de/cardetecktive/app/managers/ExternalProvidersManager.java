package de.cardetecktive.app.managers;

import de.cardetecktive.app.enums.Provider;
import de.cardetecktive.app.exceptions.TestDataException;
import de.cardetecktive.app.services.external.*;
import org.springframework.stereotype.Repository;

@Repository
public class ExternalProvidersManager {

    private final MobileDeProvider mobileDe;
    private final AutoscoutProvider autoscout;
    private final PkwProvider pkw;
    private final ComparisChProvider comparisCh;
    private final AutomobileItProvider automobileIt;
    private final AutotraderProvider autotrader;
    private final AutoscoutChProvider autoscoutCh;
    private final OtomotoPlProvider otomotoPl;
    private final PorscheFinderDeProvider porscheFinderDe;
    private final CochesProvider cochesProvider;
    private final HasznaltautoHuProvider hasznaltautoHuProvider;
    private final TruckscoutDeProvider truckscoutDeProvider;
    private final LacentraleFrProvider lacentraleFrProvider;

    public ExternalProvidersManager(final MobileDeProvider mobileDe,
                                    final AutoscoutProvider autoscout,
                                    final PkwProvider pkw, ComparisChProvider comparisCh,
                                    final AutomobileItProvider automobileIt,
                                    final AutotraderProvider autotrader,
                                    final AutoscoutChProvider autoscoutCh,
                                    final OtomotoPlProvider otomotoPl,
                                    final PorscheFinderDeProvider porscheFinderDe,
                                    final CochesProvider cochesProvider,
                                    final HasznaltautoHuProvider hasznaltautoHuProvider,
                                    final TruckscoutDeProvider truckscoutDeProvider,
                                    final LacentraleFrProvider lacentraleFrProvider) {
        this.mobileDe = mobileDe;
        this.autoscout = autoscout;
        this.pkw = pkw;
        this.comparisCh = comparisCh;
        this.automobileIt = automobileIt;
        this.autotrader = autotrader;
        this.autoscoutCh = autoscoutCh;
        this.otomotoPl = otomotoPl;
        this.porscheFinderDe = porscheFinderDe;
        this.cochesProvider = cochesProvider;
        this.hasznaltautoHuProvider = hasznaltautoHuProvider;
        this.truckscoutDeProvider = truckscoutDeProvider;
        this.lacentraleFrProvider = lacentraleFrProvider;
    }

    public MakeModelProvider getProviderService(final Provider provider) {
        switch (provider) {
            case MOBILEDEW:
                return mobileDe;
            case AUTOSCOUT:
                return autoscout;
            case PKW:
                return pkw;
            case COMPRS_CH:
                return comparisCh;
            case AUTOMOBILE:
                return automobileIt;
            case AUTOTRADER:
                return autotrader;
            case AUTOSCOUT_CH:
                return autoscoutCh;
            case OTOMOTO:
                return otomotoPl;
            case PORSCHE_FINDER_DE:
                return porscheFinderDe;
            case COCHES:
                return cochesProvider;
            case HASZNALTAUTO:
                return hasznaltautoHuProvider;
            case TRUCKSCOUT:
                return truckscoutDeProvider;
            case LACENTRAL_FR:
                return lacentraleFrProvider;
            default:
                throw new TestDataException("Provider not implemented: " + provider);
        }
    }
}
