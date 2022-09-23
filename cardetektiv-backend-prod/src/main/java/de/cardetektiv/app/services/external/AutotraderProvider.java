package de.cardetektiv.app.services.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableMap;
import de.cardetecktive.app.cars.Makes;
import de.cardetektiv.app.constants.RequestParams;
import de.cardetektiv.app.services.AbstractService;
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
public final class AutotraderProvider extends AbstractService implements MakeModelProvider {

    private static final String CAR_MAKES = "at-graphql?opname=SearchFormFacetsQuery";
    private final String MAKES_QUERY;
    private final String MODEL_QUERY;

    @Value("${autotrader.co.uk.url}")
    private String baseUri;

    @Value("${autotrader.co.uk.host}")
    private String baseHost;

    public AutotraderProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStreamMakes = classLoader.getResourceAsStream("graphql/autotrader_makes_query.txt");
        assert inputStreamMakes != null;
        MAKES_QUERY = new BufferedReader(
                new InputStreamReader(inputStreamMakes, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        InputStream inputStreamModels = classLoader.getResourceAsStream("graphql/autotrader_models_query.txt");
        assert inputStreamModels != null;
        MODEL_QUERY = new BufferedReader(
                new InputStreamReader(inputStreamModels, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("Host", baseHost)
                .body(MAKES_QUERY)
                .post(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();
        String raw = rawJson.substring(1, rawJson.length()-1);

        AutoTraderReader autoTraderReader = JsonParser.jsonToObject(raw, AutoTraderReader.class);
        AutoTraderReader.Data.Search.Adverts.Facets facets = autoTraderReader
                .getData()
                .getSearch()
                .getAdverts()
                .getFacets()
                .get(3);

        if(!facets.name.equals("make"))
            return "{}";

        List<Makes.Make> makeList = new ArrayList<>();
        facets.getValues().forEach(val -> makeList.add(new Makes.Make(val.value, val.name)));
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("Host", baseHost)
                .body(MODEL_QUERY.replace("%MAKE_ID%", makeId))
                .post(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();
        String raw = rawJson.substring(1, rawJson.length()-1);

        AutoTraderReader autoTraderReader = JsonParser.jsonToObject(raw, AutoTraderReader.class);
        AutoTraderReader.Data.Search.Adverts.Facets facets = autoTraderReader
                .getData()
                .getSearch()
                .getAdverts()
                .getFacets()
                .get(0);

        if(!facets.name.equals("model"))
            return "{}";

        List<Makes.Make> makeList = new ArrayList<>();
        facets.getValues().forEach(val -> makeList.add(new Makes.Make(val.value, val.name)));
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class AutoTraderReader {
        private Data data;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Data {
            private Search search;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class Search {
                private Adverts adverts;
                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                public static class Adverts {
                    private List<Facets> facets;
                    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                    public static class Facets {
                        private String name;
                        private Collection<ValueObject> values;
                        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                        public static class ValueObject {
                            private String name;
                            private String value;
                        }
                    }
                }
            }
        }
    }
}
