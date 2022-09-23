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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
public final class OtomotoPlProvider extends AbstractService implements MakeModelProvider {
    
    private static final String DEFAULT_VALUE_MAKES = "{\"makes\": []}";
    private static final String CAR_MAKES_MODELS = "api/v1/category/osobowe";

    @Value("${otomoto.pl.url}")
    private String baseUri;

    public OtomotoPlProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    private String rawJson;
    private OtomotoPlReader.Props.PageProps.Filters._Filter571.Meta.Values modelValues;
    @Override
    public String getMakes() {
        if(rawJson == null) {
            final Response response = given().baseUri(baseUri)
                    .contentType(ContentType.HTML)
                    .header("User-Agent", RequestParams.USER_AGENT)
                    .get(CAR_MAKES_MODELS);

            String html = response.getBody().asString().trim();

            Element findElement = Jsoup.parse(html).getElementById("__NEXT_DATA__");
            if (!findElement.nodeName().equals("script"))
                return DEFAULT_VALUE_MAKES;
            if (!(findElement.hasAttr("type") && findElement.attributes().get("type").equals("application/json")))
                return DEFAULT_VALUE_MAKES;

            rawJson = findElement.html();
        }

        if(modelValues == null) {
            OtomotoPlReader otomotoPlReader = JsonParser.jsonToObject(rawJson, OtomotoPlReader.class);
            modelValues = otomotoPlReader
                    .getProps()
                    .getPageProps()
                    .getFilters()
                    .getFilter571()
                    .get_meta()
                    .getValues()
                    .get(1);
        }

        if(!modelValues.group_label.equals(""))
            return "{}";

        List<Makes.Make> makeList = new ArrayList<>();

        modelValues.getGroup_values().forEach(val -> {
            String displayName = val.name;
            if(displayName.contains("(") && displayName.endsWith(")") && displayName.contains(" ")) {
                String[] spl = displayName.split(" ");
                displayName = displayName.replace(" " + spl[spl.length-1], "");
            }
            makeList.add(new Makes.Make(val.search_key, displayName));
        });
        return JsonParser.objectToJson(ImmutableMap.of("makes", makeList));
    }

    private OtomotoModelsPlReader otomotoModelsPlReader;
    @Override
    public String getModelsByMake(String makeId) {
        if(rawJson == null) {
            final Response response = given().baseUri(baseUri)
                    .contentType(ContentType.HTML)
                    .header("User-Agent", RequestParams.USER_AGENT)
                    .get(CAR_MAKES_MODELS);

            String html = response.getBody().asString().trim();

            Element findElement = Jsoup.parse(html).getElementById("__NEXT_DATA__");
            if (!findElement.nodeName().equals("script"))
                return DEFAULT_VALUE_MAKES;
            if (!(findElement.hasAttr("type") && findElement.attributes().get("type").equals("application/json")))
                return DEFAULT_VALUE_MAKES;

            rawJson = findElement.html();
        }

        if(modelValues == null) {
            OtomotoPlReader otomotoPlReader = JsonParser.jsonToObject(rawJson, OtomotoPlReader.class);
            modelValues = otomotoPlReader
                    .getProps()
                    .getPageProps()
                    .getFilters()
                    .getFilter571()
                    .get_meta()
                    .getValues()
                    .get(1);
        }

        Optional<OtomotoPlReader.Props.PageProps.Filters._Filter571.Meta.Values.ValueObject> val
                = modelValues.group_values.stream().filter(e -> e.search_key.equals(makeId)).findFirst();

        if(otomotoModelsPlReader == null)
            otomotoModelsPlReader = JsonParser.jsonToObject(rawJson, OtomotoModelsPlReader.class);

        List<Makes.Make> makeList = new ArrayList<>();
        val.ifPresent(valueObject -> {
            Map<String, Collection<OtomotoModelsPlReader.Props.PageProps.Filters>> map
                    = otomotoModelsPlReader.getProps().getPageProps().getFiltersValues();
            Optional<Map.Entry<String, Collection<OtomotoModelsPlReader.Props.PageProps.Filters>>> optional
                    = map.entrySet().stream().filter(e -> e.getKey().endsWith(":" + valueObject.value_key)).findFirst();
            optional.ifPresent(filter -> filter.getValue().forEach(make -> {
                if(make.group_label.isEmpty()) {
                    make.group_values.forEach(g -> {
                        String displayName = g.name;
                        if(displayName.contains("(") && displayName.endsWith(")") && displayName.contains(" ")) {
                            String[] spl = displayName.split(" ");
                            displayName = displayName.replace(" " + spl[spl.length-1], "");
                        }
                        makeList.add(new Makes.Make(g.search_key, displayName));
                    });
                }
            }));
        });

        if(makeList.isEmpty()) {
            System.out.println("Warning: makeList is empty!");
        }

        return JsonParser.objectToJson(ImmutableMap.of("models", makeList));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class OtomotoPlReader {
        private Props props;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Props {
            private PageProps pageProps;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class PageProps {
                private Filters filters;
                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                public static class Filters {
                    @JsonAlias("571")
                    private _Filter571 filter571;
                    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                    public static class _Filter571 {
                        private Meta _meta;
                        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                        public static class Meta {
                            private List<Values> values;
                            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                            public static class Values {
                                private String group_label;
                                private List<ValueObject> group_values;
                                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                                public static class ValueObject {
                                    private String value_key;
                                    private String search_key;
                                    private String name;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class OtomotoModelsPlReader {
        private Props props;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Props {
            private PageProps pageProps;
            @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
            public static class PageProps {
                private Map<String, Collection<Filters>> filtersValues;
                @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                public static class Filters {
                    private String group_label;
                    private List<ValueObject> group_values;
                    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
                    public static class ValueObject {
                        private String value_key;
                        private String search_key;
                        private String name;
                    }
                }
            }
        }
    }
}
