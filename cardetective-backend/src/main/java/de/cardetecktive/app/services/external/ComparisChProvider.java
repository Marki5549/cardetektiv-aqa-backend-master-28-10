package de.cardetecktive.app.services.external;

import de.cardetecktive.app.cars.Makes;
import de.cardetecktive.app.constants.RequestParams;
import de.cardetecktive.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.xsoup.Xsoup;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public final class ComparisChProvider extends AbstractService implements MakeModelProvider {

    private static final String CAR_FINDER = "carfinder/";
    private static final String CAR_MAKES = CAR_FINDER + "default";
    private static final String CAR_MODELS = CAR_FINDER + "searchservice/GetModelGroupsForMake";
    private static final String MAKES_PATH = "//select[@id='ddlMake']/option[@value!='0']";

    @Value("${comparis.ch.url}")
    private String baseUri;

    public ComparisChProvider(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Override
    public String getMakes() {
        final String html = given().baseUri(baseUri)
                .contentType(ContentType.HTML)
                .header("User-Agent", RequestParams.USER_AGENT)
                .get(CAR_MAKES)
                .asString();

        final Makes makes = new Makes();
        final Collection<Makes.Make> makesList = Xsoup.select(html, MAKES_PATH).getElements()
                .stream()
                .map(element -> new Makes.Make(element.attr("value"), element.text()))
                .collect(Collectors.toList());
        makes.setMakes(makesList);
        return JsonParser.objectToJson(makes);
    }

    @Override
    public String getModelsByMake(String makeId) {
        final Map<String, List<LinkedHashMap>> makeAndModels = new HashMap<>();
        final List<LinkedHashMap> makeModels = given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .queryParam("makeID", makeId)
                .get(CAR_MODELS)
                .jsonPath()
                .getList("", LinkedHashMap.class);
        makeAndModels.put("models", makeModels);
        return JsonParser.objectToJson(makeAndModels);
    }
}