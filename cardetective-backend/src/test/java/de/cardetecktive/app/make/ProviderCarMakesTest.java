package de.cardetecktive.app.make;

import de.cardetecktive.app.ApplicationBaseTest;
import de.cardetecktive.app.cars.Makes;
import de.cardetecktive.app.enums.Provider;
import de.cardetecktive.app.managers.ExternalProvidersManager;
import de.cardetecktive.app.services.external.TruckscoutDeProvider;
import de.cardetecktive.app.steps.MakeModelSteps;
import io.qameta.allure.Feature;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Feature("[BE] Application car makes and models by provider")
public class ProviderCarMakesTest extends ApplicationBaseTest {

    @Autowired
    private MakeModelSteps makeModelSteps;

    @Autowired
    private ExternalProvidersManager externalProvidersManager;

    @DataProvider
    public Object[][] carProviders() {
        return new Object[][]{
                {Provider.AUTOSCOUT},
                {Provider.MOBILEDEW},
                {Provider.PKW},
                {Provider.LACENTRAL_FR},
                {Provider.COCHES},
                {Provider.AUTOMOBILE},
                {Provider.PORSCHE_FINDER_DE},
                {Provider.OTOMOTO},
                {Provider.TRUCKSCOUT},
                {Provider.AUTOTRADER},
                {Provider.AUTOSCOUT_CH},
                {Provider.HASZNALTAUTO}
        };
    }

    @Test(dataProvider = "carProviders",
            description = "Client should have the same set of car makes as in external provider")
    public void carMakesShouldBeEqualToExternalProviderMakesList(final Provider provider) {
        if(provider == Provider.TRUCKSCOUT) {
            TruckscoutDeProvider truckscout = (TruckscoutDeProvider) externalProvidersManager.getProviderService(provider);
            HashMap<String, Set<Makes.Make>> makeList = truckscout.getMakesCollection();
            final Collection<Makes.Make> expectedMakes = makeModelSteps.requestMakesFromProvider(provider);
            final Collection<Makes.Make> actual = makeModelSteps.findAllApplicationMakesByProvider(provider);
            final SoftAssertions assertions = new SoftAssertions();
            makeList.forEach((key, expected) -> {
                System.out.println("TYPE: " + key);
                System.out.println("Provider Makes: " + expected);
                System.out.println("Actual Makes (in database): " + actual);
                assertions.assertThat(actual)
                        .isNotNull();
                assertions.assertThat(expected)
                        .isNotNull();
                assertions.assertThat(CollectionUtils.subtract(expected, actual))
                        .as("Makes exist in Truckscout provider (type " + key + ") but not found in Cardetective")
                        .isEmpty();
            });
            assertions.assertThat(CollectionUtils.subtract(actual, expectedMakes))
                    .as("Makes exist in Cardetective but not found in Truckscout provider")
                    .isEmpty();
            assertions.assertAll();
            return;
        }
        final Collection<Makes.Make> actualMakes = makeModelSteps.findAllApplicationMakesByProvider(provider);
        final Collection<Makes.Make> expectedMakes = makeModelSteps.requestMakesFromProvider(provider);
        System.out.println("Provider Makes: " + expectedMakes);
        System.out.println("Actual Makes (in database): " + actualMakes);

        makeModelSteps.applicationAndProviderMakesShouldBeEquals(actualMakes, expectedMakes);
    }
}
