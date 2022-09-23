package de.cardetecktive.app.authentication.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class LoginRequest {

    private final String username;
    private final String password;
    private final String urlPattern;
}

