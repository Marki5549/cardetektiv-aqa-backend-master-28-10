package de.cardetecktive.app.authentication.response;

import de.cardetecktive.app.enums.ErrorType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ErrorResponse {

    private ErrorType error;
}
