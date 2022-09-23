package de.cardetektiv.app.services.external;

import de.cardetektiv.app.constants.RequestParams;
import de.cardetektiv.app.services.AbstractService;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public final class MobileDeProvider extends AbstractService implements MakeModelProvider {

    private static final String SVC_SERVICE = "svc/r/";
    private static final String CAR_MAKES = SVC_SERVICE + "makes/Car";
    private static final String CAR_MODELS = SVC_SERVICE + "models/{makeId}";

    @Value("${mobilede.base.url}")
    private String baseUri;

    public MobileDeProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("accept-language", "en")
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES)
                .asString();
    }

    @Override
    public String getModelsByMake(String makeId) {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("accept-language", "en")
                .header("User-Agent", RequestParams.USER_AGENT)
                .pathParam("makeId", makeId)
                .get(CAR_MODELS)
                .asString();
    }
}
