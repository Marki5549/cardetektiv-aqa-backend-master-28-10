package de.cardetecktive.app.services.external;

import de.cardetecktive.app.constants.RequestParams;
import de.cardetecktive.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public final class AutoscoutProvider extends AbstractService implements MakeModelProvider {

    private static final String CAR_MAKES = "makes/";
    private static final String CAR_MODELS = CAR_MAKES + "{makeId}/models/";

    @Value("${autoscout.base.url}")
    private String baseUri;

    public AutoscoutProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        return JsonParser.objectToJson(given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("X-AS24-Version", "1.1")
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES)
                .jsonPath()
                .getMap("_data"));
    }

    @Override
    public String getModelsByMake(String makeId) {
        return JsonParser.objectToJson(given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("X-AS24-Version", "1.1")
                .header("User-Agent", RequestParams.USER_AGENT)
                .pathParam("makeId", makeId)
                .get(CAR_MODELS)
                .jsonPath()
                .getMap("_data"));
    }
}
