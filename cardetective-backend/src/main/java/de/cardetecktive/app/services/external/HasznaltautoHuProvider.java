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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Service
public final class HasznaltautoHuProvider extends AbstractService implements MakeModelProvider {

    private static final String API_VERSION = "v2/";
    private static final String CAR_MAKES_MODELS = API_VERSION + "tomb/markakSzemelyautoFilter,modellekSzemelyautoFilter";

    @Value("${hasznaltauto.hu.url}")
    private String baseUri;

    public HasznaltautoHuProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES_MODELS);

        String rawJson = response.getBody().asString().trim();

        HasznaltautoHuReader hasznaltautoHuReader = JsonParser.jsonToObject(rawJson, HasznaltautoHuProvider.HasznaltautoHuReader.class);
        Collection<HasznaltautoHuProvider.HasznaltautoHuReader.MakeValues> markakSzemelyautoFilter = hasznaltautoHuReader.getMarkakSzemelyautoFilter();

        List<Makes.Make> makeList = new ArrayList<>();
        markakSzemelyautoFilter.forEach(val -> {
            String displayName = val.v;
            if(displayName.contains("(") && displayName.endsWith(")") && displayName.contains(" ")) {
                String[] spl = displayName.split(" ");
                displayName = displayName.replace(" " + spl[spl.length-1], "");
            }
            makeList.add(new Makes.Make(val.k, displayName));
        });
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Response response = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES_MODELS);

        String rawJson = response.getBody().asString().trim();

        HasznaltautoHuModelsReader hasznaltautoHuModelsReader = JsonParser.jsonToObject(rawJson, HasznaltautoHuProvider.HasznaltautoHuModelsReader.class);
        Collection<HasznaltautoHuProvider.HasznaltautoHuModelsReader.ModelValues> modellekSzemelyautoFilter = hasznaltautoHuModelsReader.getModellekSzemelyautoFilter();

        List<Makes.Make> makeList = new ArrayList<>();
        modellekSzemelyautoFilter.forEach(val -> {
            if(val.k.equals(makeId)) {
                val.i.forEach(el -> {
                    String displayName = el.v;
                    if(displayName.contains("(") && displayName.endsWith(")") && displayName.contains(" ")) {
                        String[] spl = displayName.split(" ");
                        displayName = displayName.replace(" " + spl[spl.length-1], "");
                    }
                    makeList.add(new Makes.Make(el.k, displayName));
                });
            }
        });
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class HasznaltautoHuReader {
        private Collection<MakeValues> markakSzemelyautoFilter;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class MakeValues {
            private String k;
            private String v;
        }
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class HasznaltautoHuModelsReader {
        private Collection<ModelValues> modellekSzemelyautoFilter;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class ModelValues {
            private String k;
            private Collection<ValueObject> i;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class ValueObject {
                private String k;
                private String v;
            }
        }
    }
}
