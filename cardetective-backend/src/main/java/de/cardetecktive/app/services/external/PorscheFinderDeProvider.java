package de.cardetecktive.app.services.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableMap;
import de.cardetecktive.app.cars.Makes;
import de.cardetecktive.app.constants.RequestParams;
import de.cardetecktive.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public final class PorscheFinderDeProvider extends AbstractService implements MakeModelProvider {

    private static final String CAR_MAKES = "api/offer/home-view?marketPlaceKey=us";
    private final String MAKES_QUERY;

    @Value("${porsche.finder.url}")
    private String baseUri;

    public PorscheFinderDeProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStreamMakes = classLoader.getResourceAsStream("graphql/porsche_finder_makes_query.txt");
        assert inputStreamMakes != null;
        MAKES_QUERY = new BufferedReader(
                new InputStreamReader(inputStreamMakes, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining());
    }

    @Override
    public String getMakes() {
        List<Makes.Make> makeList = new ArrayList<>();
        makeList.add(new Makes.Make("PORSCHE", "PORSCHE"));
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .body(MAKES_QUERY)
                .post(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();
        PorscheFinderDeReader porscheFinderDeReader = JsonParser.jsonToObject(rawJson, PorscheFinderDeReader.class);

        PorscheFinderDeReader.Filter.ModelSeriesKey modelSeriesKey = porscheFinderDeReader
                .getFilter()
                .getModelSeriesKey();

        List<Makes.Make> makeList = new ArrayList<>();
        modelSeriesKey.getOptions().forEach(val -> makeList.add(new Makes.Make(val.value, val.displayText)));
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class PorscheFinderDeReader {
        private Filter filter;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Filter {
            private ModelSeriesKey modelSeriesKey;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class ModelSeriesKey {
                private Collection<ValueObject> options;
                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                public static class ValueObject {
                    private String value;
                    private String displayText;
                }
            }
        }
    }
}
