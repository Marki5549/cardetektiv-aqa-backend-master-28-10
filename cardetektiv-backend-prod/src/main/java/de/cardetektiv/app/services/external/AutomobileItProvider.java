package de.cardetektiv.app.services.external;

import com.google.common.collect.ImmutableMap;
import de.cardetektiv.app.constants.RequestParams;
import de.cardetektiv.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

@Service
public final class AutomobileItProvider extends AbstractService implements MakeModelProvider {

    private static final String API_VERSION = "api/v1/";
    private static final String CAR_MAKES = API_VERSION + "makes/";
    private static final String CAR_MODELS = CAR_MAKES + "{makeId}/models/";

    @Value("${automobile.it.url}")
    private String baseUri;

    public AutomobileItProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES);

        final List<LinkedHashMap> makes = response.jsonPath()
                .getList("all", LinkedHashMap.class);

        return JsonParser.objectToJson(ImmutableMap.of("makes", makes));
    }

    @Override
    public String getModelsByMake(String makeId) {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .pathParam("makeId", makeId)
                .get(CAR_MODELS)
                .asString();
    }
}
