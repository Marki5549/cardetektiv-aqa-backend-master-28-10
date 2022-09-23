package de.cardetektiv.app.steps;

import de.cardetecktive.app.cars.Makes;
import de.cardetecktive.app.cars.Makes.Make;
import de.cardetecktive.app.cars.Models;
import de.cardetecktive.app.cars.Models.Model;
import de.cardetecktive.app.enums.Provider;
import de.cardetektiv.app.managers.ExternalProvidersManager;
import de.cardetecktive.app.utils.JsonParser;
import de.cardetecktive.app.utils.ResourceReader;
import io.qameta.allure.Step;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Repository
public final class MakeModelSteps extends AbstractBackendSteps {

    @Value("classpath:sql/select_makes_by_provider.sql")
    private Resource makesQuery;

    @Value("classpath:sql/select_models_by_make.sql")
    private Resource modelsQuery;

    @Value("classpath:sql/select_model_groups_by_make.sql")
    private Resource modelGroupsQuery;

    private final ExternalProvidersManager providersManager;
    private final JdbcTemplate jdbcTemplate;

    public MakeModelSteps(ExternalProvidersManager providersManager, JdbcTemplate jdbcTemplate) {
        this.providersManager = providersManager;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Step("Client receive list of car makes for provider [{provider}] from Cardetective")
    public Collection<Make> findAllApplicationMakesByProvider(final Provider provider) {
        String query = ResourceReader.asString(makesQuery);
        if(provider == Provider.TRUCKSCOUT)
            query = query.replace(" AND modelKey <> ''", "");
        System.out.println(query);
        return jdbcTemplate.query(query,
                (ResultSet rs) -> {
                    final Collection<Make> makes = new HashSet<>();
                    while (rs.next()) {
                        final Make make =
                                new Make(rs.getString("makeValue"), rs.getString("makeKey"));
                        makes.add(make);
                    }
                    return makes;
                }, provider.name());
    }

    @Step("Client obtain list of car makes from [{provider}] provider")
    public Collection<Make> requestMakesFromProvider(final Provider provider) {
        return JsonParser.jsonToObject(providersManager.getProviderService(provider).getMakes(), Makes.class)
                .getMakes();
    }

    @Step("Client obtain list of models from [{provider}] provider by [{make.makeKey}] make")
    public Collection<Model> requestModelsByMakeFromProvider(final Provider provider, final Make make) {
        Collection<Model> models = JsonParser.jsonToObject(
                providersManager.getProviderService(provider).getModelsByMake(make.getMakeValue()), Models.class)
                .getModels();
        if(models == null)
            models = new ArrayList<>();
        return models;
    }

    @Step("Compare Cardetective and Provider makes")
    public void applicationAndProviderMakesShouldBeEquals(final Collection<Make> actual,
                                                          final Collection<Make> expected) {
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(actual)
                .isNotNull();
        assertions.assertThat(expected)
                .isNotNull();
        assertions.assertThat(CollectionUtils.subtract(actual, expected))
                .as("Makes exist in Cardetective but not found in provider")
                .isEmpty();
        assertions.assertThat(CollectionUtils.subtract(expected, actual))
                .as("Makes exist in provider but not found in Cardetective")
                .isEmpty();
        assertions.assertAll();
    }

    @Step("Compare Cardetective and Provider models for [{make.makeKey}] make")
    public void applicationAndProviderModelsShouldBeEquals(final Provider provider,
                                                           final Make make,
                                                           Collection<Model> actual,
                                                           Collection<Model> expected) {
        final SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(CollectionUtils.subtract(actual, expected))
                .as(String.format("Make: [%s] - Models exist in Cardetective but not found in provider",
                        make.getMakeKey()))
                .isEmpty();

        actual = new ArrayList<>(actual);
        actual.addAll(findAllApplicationModelGroupsByMakeAndProvider(provider, make));
        assertions.assertThat(CollectionUtils.subtract(expected, actual))
                .as(String.format("Make: [%s] - Models exist in provider but not found in Cardetective",
                        make.getMakeKey()))
                .isEmpty();
        assertions.assertAll();
    }

    @Step("Get models for make [{make.makeKey}] by provider [{provider}] from Cardetective")
    public Collection<Model> findAllApplicationModelsByMakeAndProvider(final Provider provider, final Make make) {
        return jdbcTemplate.query(ResourceReader.asString(modelsQuery),
                (ResultSet rs) -> {
                    final Collection<Model> models = new HashSet<>();
                    while (rs.next()) {
                        final Model model = new Model(
                                rs.getString("modelValue"),
                                rs.getString("modelKey"));
                        models.add(model);
                    }
                    return models;
                }, provider.name(), make.getMakeValue());
    }

    @Step("Get model groups for make [{make.makeKey}] by provider [{provider}] from Cardetective")
    private Collection<Model> findAllApplicationModelGroupsByMakeAndProvider(final Provider provider, final Make make) {
        return jdbcTemplate.query(ResourceReader.asString(modelGroupsQuery),
                (ResultSet rs) -> {
                    final Collection<Model> models = new HashSet<>();
                    while (rs.next()) {
                        if (Objects.nonNull(rs.getString(provider.getModelsGroupKey()))) {
                            final Model model = new Model(
                                    rs.getString(provider.getModelsGroupKey()),
                                    rs.getString("groupKey"));
                            models.add(model);
                        }
                    }
                    return models;
                }, provider.name(), make.getMakeValue());
    }
}
