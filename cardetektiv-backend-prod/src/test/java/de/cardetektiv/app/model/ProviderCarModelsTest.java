package de.cardetektiv.app.model;

import com.google.common.base.Supplier;
import de.cardetektiv.app.ApplicationBaseTest;
import de.cardetecktive.app.cars.Makes;
import de.cardetecktive.app.cars.Models.Model;
import de.cardetecktive.app.enums.Provider;
import de.cardetektiv.app.ApplicationBaseTest;
import de.cardetektiv.app.steps.MakeModelSteps;
import de.cardetektiv.app.utils.CastUtils;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


@Feature("[BE] Application car makes and models by provider")
public class ProviderCarModelsTest extends ApplicationBaseTest {

    @Autowired
    private MakeModelSteps makeModelSteps;

    @DataProvider
    public Iterator<Object> carModelsFromMobileDe() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.MOBILEDEW).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromMobileDe",
            description = "Client should have the same set of car models for each make as in MOBILEDEW provider")
    public void carModelsShouldBeEqualToMobileDeProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.MOBILEDEW, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromAutoscout() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.AUTOSCOUT).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromAutoscout",
            description = "Client should have the same set of car models for each make as in AUTOSCOUT provider")
    public void carModelsShouldBeEqualToAutoscoutProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.AUTOSCOUT, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromPkw() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.PKW).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromPkw",
            description = "Client should have the same set of car models for each make as in PKW provider")
    public void carModelsShouldBeEqualToPkwProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.PKW, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromAutotrader() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.AUTOTRADER).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromAutotrader",
            description = "Client should have the same set of car models for each make as in AUTOTRADER provider")
    public void carModelsShouldBeEqualToAutotraderProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.AUTOTRADER, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromAutoscoutCh() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.AUTOSCOUT_CH).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromAutoscoutCh",
            description = "Client should have the same set of car models for each make as in AUTOSCOUT_CH provider")
    public void carModelsShouldBeEqualToAutoscoutChProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.AUTOSCOUT_CH, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromPorscheFinderDe() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.PORSCHE_FINDER_DE).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromPorscheFinderDe",
            description = "Client should have the same set of car models for each make as in PORSCHE_FINDER_DE provider")
    public void carModelsShouldBeEqualToPorscheFinderDeProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.PORSCHE_FINDER_DE, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromOtomotoPl() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.OTOMOTO).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromOtomotoPl",
            description = "Client should have the same set of car models for each make as in OTOMOTO provider")
    public void carModelsShouldBeEqualToOtomotoPlProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.OTOMOTO, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromCoches() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.COCHES).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromCoches",
            description = "Client should have the same set of car models for each make as in COCHES provider")
    public void carModelsShouldBeEqualToCochesProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.COCHES, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromHasznaltautoHu() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.HASZNALTAUTO).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromHasznaltautoHu",
            description = "Client should have the same set of car models for each make as in HASZNALTAUTO provider")
    public void carModelsShouldBeEqualToHasznaltautoHuProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.HASZNALTAUTO, make);
    }

    @DataProvider
    public Iterator<Object> carModelsFromLacentraleFr() {
        return CastUtils.cast(makeModelSteps.requestMakesFromProvider(Provider.LACENTRAL_FR).iterator(), Object.class);
    }

    @Test(dataProvider = "carModelsFromLacentraleFr",
            description = "Client should have the same set of car models for each make as in LACENTRAL_FR provider")
    public void carModelsShouldBeEqualToLacentraleFrProviderModelsList(final Makes.Make make) {
        modelsAssert(Provider.LACENTRAL_FR, make, () ->
                (make1, actualModels, expectedModels) -> {

                    Collection<Model> list1 = new ArrayList<>();
                    for(Model model : actualModels) {
                        boolean contains = false;
                        for(Model exModel : expectedModels) {
                            if(model.getModelValue().contains(exModel.getModelValue())) {
                                contains = true;
                                break;
                            }
                        }
                        if(!contains) {
                            list1.add(model);
                        }
                    }

                    Collection<Model> list2 = new ArrayList<>();
                    for(Model model : expectedModels) {
                        boolean contains = false;
                        for(Model exModel : actualModels) {
                            if(exModel.getModelValue().contains(model.getModelValue())) {
                                contains = true;
                                break;
                            }
                        }
                        if(!contains) {
                            list2.add(model);
                        }
                    }

                    SoftAssertions assertions = new SoftAssertions();
                    assertions.assertThat(list1)
                            .as(String.format("Make: [%s] - Models exist in Cardetective but not found in provider",
                                    make.getMakeKey()))
                            .isEmpty();
                    assertions.assertThat(list2)
                            .as(String.format("Make: [%s] - Models exist in provider but not found in Cardetective",
                                    make.getMakeKey()))
                            .isEmpty();
                    assertions.assertAll();

                }
        );
    }

    private void modelsAssert(Provider provider, Makes.Make make) {
        modelsAssert(provider, make, () ->
                (make1, actualModels1, expectedModels1) ->
                        makeModelSteps.applicationAndProviderModelsShouldBeEquals(
                                provider, make1, actualModels1, expectedModels1
                        ));
    }

    private void modelsAssert(Provider provider, Makes.Make make, Supplier<IModelAssert> s) {
        final Collection<Model> actualModels = makeModelSteps
                .findAllApplicationModelsByMakeAndProvider(provider, make);
        final Collection<Model> expectedModels = makeModelSteps
                .requestModelsByMakeFromProvider(provider, make);

        System.out.println("actualModels (in database): " + actualModels);
        System.out.println("expectedModels: " + expectedModels);
        s.get().run(make, actualModels, expectedModels);
    }

    public interface IModelAssert {
        void run(Makes.Make make, Collection<Model> actualModels, Collection<Model> expectedModels);
    }
}
