package de.cardetektiv.app.services.external;

import com.fasterxml.jackson.annotation.JsonAlias;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Service
public final class TruckscoutDeProvider extends AbstractService implements MakeModelProvider {

    private static final String DEFAULT_VALUE_MODELS = "{\"models\": []}";
    private static final String CAR_MAKES = "JsonData/HomePageJsonModel";

    @Value("${truckscout.de.url}")
    private String baseUri;

    public TruckscoutDeProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    public HashMap<String, Set<Makes.Make>> getMakesCollection() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .post(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();

        TruckscoutDeReader truckscoutDeReader = JsonParser.jsonToObject(rawJson, TruckscoutDeReader.class);
        HashMap<String, Set<Makes.Make>> makes = new HashMap<>();
        truckscoutDeReader.getMakes().forEach((key, value) -> {
            Set<Makes.Make> makeList = new HashSet<>();
            value.forEach((k1, v1) -> makeList.add(new Makes.Make(k1, v1)));
            makes.put(key, makeList);
        });
        return makes;
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .post(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();

        TruckscoutDeReader truckscoutDeReader = JsonParser.jsonToObject(rawJson, TruckscoutDeReader.class);
        Set<Makes.Make> makeList = new HashSet<>();
        truckscoutDeReader.getMakes().forEach((key, value) ->
                value.forEach((k1, v1) -> makeList.add(new Makes.Make(k1, v1))));
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        return DEFAULT_VALUE_MODELS;
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class TruckscoutDeReader {
        @JsonAlias("Makes")
        private Map<String, Map<String, String>> makes;
    }
}
