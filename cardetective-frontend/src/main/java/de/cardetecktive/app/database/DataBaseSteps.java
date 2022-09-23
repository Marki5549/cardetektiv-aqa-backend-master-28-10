package de.cardetecktive.app.database;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.cardetecktive.app.utils.ResourceReader;
import org.apache.http.client.utils.URIBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class DataBaseSteps {

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:sql/delete_user_session.sql")
    private Resource deleteSessionQuery;

    @Value("classpath:sql/update_registered_user.sql")
    private Resource updateRegisteredUser;

    @Value("classpath:sql/confirm_registration_params.sql")
    private Resource confirmRegistrationParams;

    @Value("classpath:sql/get_user_identity.sql")
    private Resource getUserIdentity;

    @Value("classpath:sql/change_app_client_version.sql")
    private Resource changeAppClientVersion;

    @Value("classpath:sql/set_default_password.sql")
    private Resource setDefaultPassword;

    public DataBaseSteps(@NotNull final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void clearUserSession(@NotNull final String userEmail) {
        jdbcTemplate.update(ResourceReader.asString(deleteSessionQuery), userEmail);
    }

    public void updateRegisteredUser(@NotNull final String userEmail) {
        jdbcTemplate.update(ResourceReader.asString(updateRegisteredUser), userEmail);
    }

    public void changeAppClientVersion(@NotNull final String email) {
        jdbcTemplate.update(ResourceReader.asString(changeAppClientVersion), email);
    }

    public void setDefaultPassword(@NotNull final String userName) {
        jdbcTemplate.update(ResourceReader.asString(setDefaultPassword), userName);
    }

    public URIBuilder confirmRegistrationParams(@NotNull final String email) {
        return jdbcTemplate.query(ResourceReader.asString(confirmRegistrationParams),
                (ResultSet rs) -> {
                    URIBuilder uriBuilder = new URIBuilder();
                    while (rs.next()) {
                        JsonElement jsonElement = JsonParser.parseString(rs.getString("params"));
                        JsonObject jo = jsonElement.getAsJsonObject();
                        jo.entrySet().forEach((entry) ->
                                uriBuilder.addParameter(entry.getKey(), entry.getValue().getAsString())
                        );
                    }
                    return uriBuilder;
                }, email);
    }

    public URIBuilder getUserIdentity(@NotNull final String userName) {
        return jdbcTemplate.query(ResourceReader.asString(getUserIdentity),
                (ResultSet rs) -> {
                    URIBuilder uriBuilder = new URIBuilder();
                    while (rs.next()) {
                        String requestId = rs.getString("requestId");
                        System.out.println("requestId: " + requestId);
                        uriBuilder.addParameter("id", requestId);
                    }
                    return uriBuilder;
                }, userName);
    }
}
