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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Service
public final class CochesProvider extends AbstractService implements MakeModelProvider {

    private static final String X_SCHIBSTED_TENANT = "coches";
    private static final String API_VERSION = "vehicle-specs/v1/";
    private static final String CAR_MAKES = API_VERSION + "makes?section1Id=2500&includeManual=true";
    private static final String CAR_MODELS = API_VERSION + "models?section1Id=2500&makeId=%makeId%&includeManual=true";

    @Value("${coches.base.url}")
    private String baseUri;

    public CochesProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("X-Schibsted-Tenant", X_SCHIBSTED_TENANT)
                .get(CAR_MAKES);

        String rawJson = response.getBody().asString().trim();

        CochesReader cochesReader = JsonParser.jsonToObject(rawJson, CochesReader.class);
        Collection<CochesReader.Item> items = cochesReader.getItems();

        List<Makes.Make> makeList = new ArrayList<>();
        items.forEach(val -> makeList.add(new Makes.Make(val.makeId, val.name)));
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("X-Schibsted-Tenant", X_SCHIBSTED_TENANT)
                .get(CAR_MODELS.replace("%makeId%", makeId));

        String rawJson = response.getBody().asString().trim();

        CochesReaderModels cochesReaderModels = JsonParser.jsonToObject(rawJson, CochesReaderModels.class);
        Collection<CochesReaderModels.Item> items = cochesReaderModels.getItems();

        List<Makes.Make> makeList = new ArrayList<>();
        items.forEach(val -> makeList.add(new Makes.Make(val.modelId, val.name)));
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class CochesReader {
        private Collection<Item> items;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Item {
            private String makeId;
            private String name;
        }
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class CochesReaderModels {
        private Collection<Item> items;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Item {
            private String modelId;
            private String name;
        }
    }
}
