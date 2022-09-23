package de.cardetektiv.app.services.external;

import de.cardetektiv.app.constants.RequestParams;
import de.cardetecktive.app.exceptions.TestDataException;
import de.cardetektiv.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public final class PkwProvider extends AbstractService implements MakeModelProvider {

    private static final String API_VERSION = "api/v1/";
    private static final String MAKES_KEY = "makes";
    private static final String CAR_MAKES_AND_MODELS = API_VERSION + "brands/models";

    @Value("${pkw.base.url}")
    private String baseUri;

    private final Map<String, List<LinkedHashMap>> makesAndModels = new HashMap<>();

    public PkwProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        saveMakeAndModels();
        return JsonParser.objectToJson(makesAndModels);
    }

    @Override
    public String getModelsByMake(String makeId) {
        if(!makesAndModels.containsKey(MAKES_KEY)) {
            saveMakeAndModels();
        }
        return JsonParser.objectToJson(makesAndModels.get(MAKES_KEY).stream()
                .filter(makes -> makeId.equals(makes.get("id").toString()))
                .findFirst()
                .orElseThrow(() -> new TestDataException("Models not found for make")));
    }

    private void saveMakeAndModels() {
        final List<LinkedHashMap> makeModels = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES_AND_MODELS)
                .jsonPath()
                .getList("", LinkedHashMap.class);
        makesAndModels.put(MAKES_KEY, makeModels);
    }
}
