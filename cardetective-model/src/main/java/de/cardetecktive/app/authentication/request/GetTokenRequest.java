package de.cardetecktive.app.authentication.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class GetTokenRequest {

    private String username;
    private String password;

}