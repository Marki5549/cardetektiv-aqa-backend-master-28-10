package de.cardetecktive.app.services.external;

import com.fasterxml.jackson.annotation.JsonAlias;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Service
public final class LacentraleFrProvider extends AbstractService implements MakeModelProvider {

    private static final String API_VERSION = "v3/";
    private static final String X_API_KEY = "58a195bfAF434B95A93A26f71a72b0ba04e9c4a3";
    private static final String CAR_MAKES_MODELS = API_VERSION + "aggregations?aggregations=MAKE_MODEL&families=AUTO,UTILITY";

    @Value("${lacentrale.fr.url}")
    private String baseUri;

    public LacentraleFrProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("X-Api-Key", X_API_KEY)
                .get(CAR_MAKES_MODELS);

        String rawJson = response.getBody().asString().trim();
        LacentraleFrReader lacentraleFrReader = JsonParser.jsonToObject(rawJson, LacentraleFrReader.class);

        List<Makes.Make> makeList = new ArrayList<>();
        lacentraleFrReader.getAggs().getVehicleMake().forEach(val ->
                makeList.add(new Makes.Make(val.key, val.key)));

        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("X-Api-Key", X_API_KEY)
                .get(CAR_MAKES_MODELS);

        String rawJson = response.getBody().asString().trim();

        LacentraleFrModelsReader lacentraleFrModelsReader = JsonParser.jsonToObject(rawJson, LacentraleFrModelsReader.class);
        List<Makes.Make> makeList = new ArrayList<>();
        lacentraleFrModelsReader.getAggs().getVehicleMake().forEach(el -> {
            if(el.key.equals(makeId))
                el.getAgg().forEach(el1 -> makeList.add(new Makes.Make(el1.key, el1.key)));
        });
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class LacentraleFrReader {
        private Aggs aggs;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Aggs {
            @JsonAlias("vehicle.make")
            private Collection<VehicleMake> vehicleMake;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class VehicleMake {
                private String key;
            }
        }
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class LacentraleFrModelsReader {
        private Aggs aggs;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Aggs {
            @JsonAlias("vehicle.make")
            private Collection<VehicleMake> vehicleMake;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class VehicleMake {
                private String key;
                private Collection<Agg> agg;
                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                public static class Agg {
                    private String key;
                }
            }
        }
    }
}
