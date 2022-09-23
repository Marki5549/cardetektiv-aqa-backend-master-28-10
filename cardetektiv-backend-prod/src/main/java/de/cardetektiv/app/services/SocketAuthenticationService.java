package de.cardetektiv.app.services;

import de.cardetektiv.app.constants.Constants;
import de.cardetektiv.app.constants.RequestParams;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class SocketAuthenticationService extends AbstractService {

    private static final String SOCKET = "socket/";
    private static final String TRANSPORT_TYPE = "polling";

    @Value("${application.base.url}")
    private String baseUri;

    public SocketAuthenticationService(Supplier<RequestSpecification> requestSpecification) {
        super(requestSpecification);
    }

    @Step("Get SID for user")
    public Response getSid(Cookie cookie) {
        return given().baseUri(baseUri)
                .contentType(ContentType.JSON)
                .cookie(cookie)
                .param(RequestParams.UID, "38")
                .param(RequestParams.EIO, "3")
                .param(RequestParams.TRANSPORT, TRANSPORT_TYPE)
                .param(RequestParams.CLIENT, Constants.SOCKET_CLIENT_ID)
                .get(SOCKET);
    }

    @Step("Authenticate to Websocket using sid [{sid}]")
    public Response socketAuthenticate(Cookie cookie, String sid) {
        return given().contentType(ContentType.JSON)
                .cookie(cookie)
                .param(Constants.SID, sid)
                .param(RequestParams.UID, "38")
                .param(RequestParams.EIO, "3")
                .param(RequestParams.TRANSPORT, TRANSPORT_TYPE)
                .param(RequestParams.CLIENT, Constants.SOCKET_CLIENT_ID)
                .get(SOCKET);
    }
}
