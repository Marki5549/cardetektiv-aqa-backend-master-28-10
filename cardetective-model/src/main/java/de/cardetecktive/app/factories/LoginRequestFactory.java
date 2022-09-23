package de.cardetecktive.app.factories;

import de.cardetecktive.app.authentication.request.LoginRequest;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

public final class LoginRequestFactory {

    private LoginRequestFactory() {
    }

    public static LoginRequest loginRequest(@NotNull final String email,
                                            @NotNull final String password,
                                            @NonNull final String baseUrl) {
        return LoginRequest.builder()
                .urlPattern(String.format("%sforgot-password?modelValue={0}", baseUrl))
                .username(email)
                .password(password)
                .build();
    }
}
