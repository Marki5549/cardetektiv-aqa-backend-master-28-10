package de.cardetecktive.app.services.archive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.cardetecktive.app.constants.RequestParams;
import de.cardetecktive.app.services.AbstractService;
import de.cardetecktive.app.utils.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public final class ArchiveSearchAds extends AbstractService {
    //Test input: "2019-12-05T05:37:12.000Z"
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static final Integer MIN_YEAR = 2007;
    public static final String FUEL_TYPES = "DIESEL";
    public static final String MAKE = "BMW";
    public static final String MODEL = "114";
    public static final String VEHICLE_TYPE = "CAR";
    public static final String AD_ONLINE_TEXT_TIME = "2021-02-28T22:00:00.000Z";
    public static final long AD_ONLINE_FROM = getUnsafeMillis(AD_ONLINE_TEXT_TIME);
    public static final long AD_ONLINE_TO = getUnsafeMillis(AD_ONLINE_TEXT_TIME);

    private final String ARCHIVE_SEARCH_ADS_QUERY;

    private static long getUnsafeMillis(String time) {
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

    @Value("${archive.search.username}")
    private String archiveSearchUserName;
    @Value("${archive.search.password}")
    private String archiveSearchPassword;
    @Value("${archive.search.api.url}")
    private String archiveSearchApiUrl;

    public ArchiveSearchAds(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStreamMakes = classLoader.getResourceAsStream("graphql/archive_search_ads_query.txt");
        assert inputStreamMakes != null;
        ARCHIVE_SEARCH_ADS_QUERY = new BufferedReader(
                new InputStreamReader(inputStreamMakes, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining());
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenMinYearGreaterThanOrEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getDate() >= MIN_YEAR);
        return items;
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenFuelTypesEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getFuel().equals(FUEL_TYPES));
        return items;
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenMakeAndModelEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getMake().equals(MAKE) && el.getModel().equals(MODEL));
        return items;
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenVehicleTypeEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getVehicleType().equals(VEHICLE_TYPE));
        return items;
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenAdOnlineFromGreaterThanOrEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getRenewed() >= AD_ONLINE_FROM - 30 * 24 * 60 * 60 * 1000L);
        return items;
    }

    public static Collection<ArchiveSearchAdsReader.Items> removeItemsWhenAdOnlineToLessThanOrEqual(
            Collection<ArchiveSearchAdsReader.Items> it) {
        Collection<ArchiveSearchAdsReader.Items> items = new LinkedList<>(it);
        items.removeIf(el -> el.getCreationDate() <= AD_ONLINE_TO);
        return items;
    }

    public Collection<ArchiveSearchAdsReader.Items> getArchiveSearchAdsResponse() {
        final Response response = given().baseUri(archiveSearchApiUrl)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("Username", archiveSearchUserName)
                .header("Password", archiveSearchPassword)
                .body(ARCHIVE_SEARCH_ADS_QUERY)
                .post(archiveSearchApiUrl);

        String rawJson = response.getBody().asString().trim();

        ArchiveSearchAdsReader archiveSearchAdsReader = JsonParser.jsonToObject(rawJson, ArchiveSearchAdsReader.class);
        return archiveSearchAdsReader.getItems();
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class ArchiveSearchAdsReader {
        private Collection<Items> items;
        @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
        public static class Items {
            private String id;
            private String _info;
            private String creationDate;
            private String firstRegistrationDate;
            private String renewed;
            private String fuel;
            private String make;
            private String model;
            private String vehicleType;

            /**
             * @return year from date(String)
             */
            public int getDate() {
                return Integer.parseInt(firstRegistrationDate.split("-")[0]);
            }

            public long getRenewed() {
                return getUnsafeMillis(renewed);
            }

            public long getCreationDate() {
                return getUnsafeMillis(creationDate);
            }
        }
    }
}
