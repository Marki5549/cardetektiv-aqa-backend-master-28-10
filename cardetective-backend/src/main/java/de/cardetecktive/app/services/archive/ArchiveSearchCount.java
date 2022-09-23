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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public final class ArchiveSearchCount extends AbstractService {

    private final String FILE_LAST_TOTAL = "./temp/archive_search_count/count.txt";
    private final String ARCHIVE_SEARCH_COUNT_QUERY;
    private static final String ETAG = "0";

    @Value("${archive.search.username}")
    private String archiveSearchUserName;
    @Value("${archive.search.password}")
    private String archiveSearchPassword;
    @Value("${archive.search.api.url}")
    private String archiveSearchApiUrl;

    public ArchiveSearchCount(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStreamMakes = classLoader.getResourceAsStream("graphql/archive_search_count_query.txt");
        assert inputStreamMakes != null;
        ARCHIVE_SEARCH_COUNT_QUERY = new BufferedReader(
                new InputStreamReader(inputStreamMakes, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining());
    }

    public long getCurrentTotal() {
        final Response response = given().baseUri(archiveSearchApiUrl)
                .contentType(ContentType.JSON)
                .header("User-Agent", RequestParams.USER_AGENT)
                .header("Username", archiveSearchUserName)
                .header("Password", archiveSearchPassword)
                .header("Etag", ETAG)
                .body(ARCHIVE_SEARCH_COUNT_QUERY)
                .post(archiveSearchApiUrl);

        String rawJson = response.getBody().asString().trim();

        ArchiveSearchCountReader archiveSearchCountReader = JsonParser.jsonToObject(rawJson, ArchiveSearchCountReader.class);
        return archiveSearchCountReader.getTotal();
    }

    public long getLastTotal() {
        String content = "0";
        try {
            content = Files.lines(Paths.get(FILE_LAST_TOTAL)).findFirst().orElse("0");
        } catch (IOException ignored) {}
        return Long.parseLong(content.trim());
    }

    public void saveTotal(long total) throws IOException {
        File f = new File(FILE_LAST_TOTAL);
        File parent = f.getParentFile();
        if(!parent.exists())
            parent.mkdirs();
        if(!f.exists())
            f.createNewFile();
        Files.write(Paths.get(FILE_LAST_TOTAL), String.valueOf(total).getBytes(StandardCharsets.UTF_8));
    }

    @Getter @Setter @JsonIgnoreProperties(ignoreUnknown = true) @EqualsAndHashCode @ToString
    public static class ArchiveSearchCountReader {
        private long total;
    }
}
