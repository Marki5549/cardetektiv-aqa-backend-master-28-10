package de.cardetecktive.app.cars;

import de.cardetecktive.app.ApplicationBaseTest;
import de.cardetecktive.app.enums.CarSearchType;
import de.cardetecktive.app.enums.Country;
import de.cardetecktive.app.enums.Provider;
import de.cardetecktive.app.factories.CarEstimateMessageFactory;
import de.cardetecktive.app.steps.AuthenticationSteps;
import de.cardetecktive.app.steps.WebSocketSteps;
import de.cardetecktive.app.websocket.outgoing.CarEstimateOutgoingMessage;
import io.qameta.allure.Feature;
import io.restassured.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Feature("[BE] Search cars amount verification")
public class CarsAmountTest extends ApplicationBaseTest {

    @Autowired
    private AuthenticationSteps authenticationSteps;

    @Autowired
    private WebSocketSteps webSocketSteps;

    @DataProvider
    public Object[][] carAmountProviderAndType() {
        return new Object[][]{
                {Provider.AUTOSCOUT, CarSearchType.CAR},
                {Provider.TRUCKSCOUT, CarSearchType.TRUCK},
                {Provider.MOBILEDEW, CarSearchType.CAR},
                {Provider.COMPRS_CH, CarSearchType.CAR},
                {Provider.LACENTRAL_FR, CarSearchType.CAR},
                {Provider.COCHES, CarSearchType.CAR},
                {Provider.AUTOMOBILE, CarSearchType.CAR},
                {Provider.PORSCHE_FINDER_DE, CarSearchType.CAR},
                {Provider.AUTOTRADER, CarSearchType.CAR},
                {Provider.AUTOSCOUT_CH, CarSearchType.CAR},
        };
    }

    @Test(dataProvider = "carAmountProviderAndType",
            description = "Client should receive amount of cars for any provider if all countries selected")
    public void searchByProviderAndAllCountriesShouldReturnValidAmount(final Provider provider,
                                                                       final CarSearchType searchType) {
        final Cookie cdSid;
        if (authenticationSteps.getCdSidExists()) {
            cdSid = authenticationSteps.getCdSid();
        } else {
            cdSid = authenticationSteps.loginAndGetCurrentSession();
        }

        webSocketSteps.authorizeToTheWebSocket(cdSid);

        CarEstimateOutgoingMessage.CarEstimateOutgoing estimateOutgoing = CarEstimateMessageFactory
                .carEstimateByType(searchType, provider);
        System.out.println("Car estimate outgoing message: " + estimateOutgoing);

        final String incomingMessage = webSocketSteps.sendCarEstimateForProvider(estimateOutgoing);
        System.out.println("Car incoming message: " + incomingMessage);

        webSocketSteps.amountOfCarsIsGreaterThanZeroForProvider(incomingMessage, provider);
    }

    @DataProvider
    public Object[][] carAmountProviderTypeAndCountry() {
        return new Object[][]{
                {Provider.AUTOSCOUT, CarSearchType.CAR, Country.DE},
                {Provider.TRUCKSCOUT, CarSearchType.TRUCK, Country.DE},
                {Provider.MOBILEDEW, CarSearchType.CAR, Country.DE},
                {Provider.COMPRS_CH, CarSearchType.CAR, Country.CH},
                {Provider.LACENTRAL_FR, CarSearchType.CAR, Country.FR},
                {Provider.COCHES, CarSearchType.CAR, Country.ES},
                {Provider.AUTOMOBILE, CarSearchType.CAR, Country.IT},
                {Provider.PORSCHE_FINDER_DE, CarSearchType.CAR, Country.DE},
                {Provider.AUTOTRADER, CarSearchType.CAR, Country.GB},
                {Provider.AUTOSCOUT_CH, CarSearchType.CAR, Country.CH},
        };
    }

    @Test(dataProvider = "carAmountProviderTypeAndCountry",
            description = "Client should receive amount of cars for provider and specific country")
    public void searchByProviderAndSpecifiedCountryShouldReturnValidAmount(final Provider provider,
                                                                           final CarSearchType searchType,
                                                                           final Country country) {
        final Cookie cdSid;
        if (authenticationSteps.getCdSidExists()) {
            cdSid = authenticationSteps.getCdSid();
        } else {
            cdSid = authenticationSteps.loginAndGetCurrentSession();
        }

        webSocketSteps.authorizeToTheWebSocket(cdSid);

        CarEstimateOutgoingMessage.CarEstimateOutgoing estimateOutgoing = CarEstimateMessageFactory
                .carEstimateByCountry(country, searchType, provider);
        System.out.println("Car estimate outgoing message: " + estimateOutgoing);

        final String incomingMessage = webSocketSteps.sendCarEstimateForProvider(estimateOutgoing);
        System.out.println("Car incoming message: " + incomingMessage);

        webSocketSteps.amountOfCarsIsGreaterThanZeroForProvider(incomingMessage, provider);
    }
}
