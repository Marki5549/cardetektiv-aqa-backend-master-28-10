package de.cardetektiv.app.services.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableMap;
import de.cardetecktive.app.cars.Makes;
import de.cardetektiv.app.constants.RequestParams;
import de.cardetektiv.app.services.AbstractService;
import de.cardetektiv.app.utils.CastUtils;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public final class AutoscoutChProvider extends AbstractService implements MakeModelProvider {

    private static final List<Integer> ALL_MAKES = Arrays.asList(10, 20, 30, 70, 80);
    private static final Map<String, List<Integer>> MAKES_IDS_TO_API_ID = new HashMap<>();
    private static final String CAR_MAKES = "webapp/v13/search?vehtyp=%make_id%";
    private static final String CAR_MODELS = "webapp/v13/vehicles/models/%make%?vehtyp=%make_id%";

    @Value("${autoscout.ch.url}")
    private String baseUri;

    public AutoscoutChProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        List<Makes.Make> makeList = new ArrayList<>();
        for(int i : ALL_MAKES) {
            final Response response = given().baseUri(baseUri)
                    .contentType(ContentType.JSON)
                    .header("User-Agent", RequestParams.USER_AGENT)
                    .get(CAR_MAKES.replace("%make_id%", String.valueOf(i)));

            String rawJson = response.getBody().asString().trim();
            AutoscoutChReader autoscoutChReader = JsonParser.jsonToObject(rawJson, AutoscoutChReader.class);
            Optional<AutoscoutChReader.Search.Item> options = autoscoutChReader.getSearch().getItems()
                    .stream().filter(el -> el.id.equals("make")).findFirst();
            if (!options.isPresent())
                return "{}";

            AutoscoutChReader.Search.Item item = options.get();
            if (!(item.options instanceof ArrayList))
                return "{}";

            ArrayList<LinkedHashMap<String, ?>> list = CastUtils.cast(item.options);
            list.forEach(el -> {
                Object objVal = el.get("value");
                Object objText = el.get("text");
                if (objVal instanceof String && objText instanceof String
                        && !objVal.equals("null") && !objText.equals("Alle")) {
                    Makes.Make make = new Makes.Make((String) objVal, (String) objText);
                    if(!makeList.contains(make)) {
                        makeList.add(make);
                        List<Integer> l = MAKES_IDS_TO_API_ID.computeIfAbsent(make.getMakeValue(),
                                k -> new ArrayList<>()
                        );
                        if(!l.contains(i))
                            l.add(i);
                    }
                }
            });
        }
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    @Override
    public String getModelsByMake(String makeId) {
        List<Makes.Make> makeList = new ArrayList<>();
        for(int i : MAKES_IDS_TO_API_ID.getOrDefault(makeId, ALL_MAKES)) {
            final Response response = given().baseUri(baseUri)
                    .contentType(ContentType.JSON)
                    .header("User-Agent", RequestParams.USER_AGENT)
                    .get(CAR_MODELS.replace("%make%", makeId).replace("%make_id%", String.valueOf(i)));

            String rawJson = response.getBody().asString().trim();
            AutoscoutChReaderModels autoscoutChReader = JsonParser.jsonToObject(rawJson, AutoscoutChReaderModels.class);
            autoscoutChReader.getModels().forEach(val -> {
                Makes.Make make = new Makes.Make(val.value, val.text);
                if (!makeList.contains(make) && !make.getMakeValue().equals("null") && !make.getMakeKey().equals("Alle")) {
                    makeList.add(make);
                }
            });
        }
        System.out.println("return in provider: " + JsonParser.objectToJson(ImmutableMap.of("models", makeList)));
        if(makeList.isEmpty()) {
            return "{\"models\": []}";
        }
        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class AutoscoutChReader {
        private Search search;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Search {
            private Collection<Item> items;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class Item {
                private String id;
                private Object options;
            }
        }
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class AutoscoutChReaderModels {
        private Collection<Item> models;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Item {
            private String text;
            private String value;
        }
    }
}
