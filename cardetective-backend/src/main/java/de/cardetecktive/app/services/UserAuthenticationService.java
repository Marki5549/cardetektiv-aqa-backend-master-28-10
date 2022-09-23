package de.cardetecktive.app.services;

import de.cardetecktive.app.authentication.request.GetTokenRequest;
import de.cardetecktive.app.authentication.request.LoginRequest;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class UserAuthenticationService extends AbstractService {

    private static final String AUTHENTICATION = "api/authentication";
    private static final String LOGOUT_ENDPOINT = AUTHENTICATION + "/logout";
    private static final String GET_TOKEN = AUTHENTICATION + "/get-token";

    @Value("${application.base.url}")
    private String baseUri;

    public UserAuthenticationService(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    public Response login(final LoginRequest request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(AUTHENTICATION);
    }

    public Response logout(final Cookie cookie) {
        return given().contentType(ContentType.JSON)
                .cookie(cookie)
                .when()
                .post(LOGOUT_ENDPOINT);
    }

    public Response getToken(final GetTokenRequest request) {
        return given().contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(GET_TOKEN);
    }
}
